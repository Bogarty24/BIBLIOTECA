package controlador;

import vistas.GestionEmpleadosVista;
import vistas.TablaEmpleadosVista;
import dao.EmpleadoDAO;
import modelo.EmpleadoModelo;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.JComboBox;

public class GestionEmpleadosController {

    private GestionEmpleadosVista empleadosVista;
    private EmpleadoDAO empleadoDAO;
    private boolean modificando = false;

    public GestionEmpleadosController(GestionEmpleadosVista empleadosVista) {
        this.empleadosVista = empleadosVista;
        this.empleadoDAO = new EmpleadoDAO();

        // Asociar acciones a los botones
        this.empleadosVista.getBtn_registrarEmpleado().addActionListener(e -> registrarEmpleado());
        this.empleadosVista.getBtn_modificarEmpleado().addActionListener(e -> modificarEmpleado());
        this.empleadosVista.getBtn_buscarEmpleado().addActionListener(e -> buscarEmpleado());
        this.empleadosVista.getBtn_eliminarEmpleado().addActionListener(e -> eliminarEmpleado());
        this.empleadosVista.getBtn_verEmpleados().addActionListener(e -> verListaEmpleados());
        
        this.empleadosVista.setVisible(true);
    }

    private void registrarEmpleado() {
        String nombre = empleadosVista.getTxt_nombre().getText().trim();
        String apellidos = empleadosVista.getTxt_apellidos().getText().trim();
        String telefono = empleadosVista.getTxt_telefono().getText().trim();
        String email = empleadosVista.getTxt_email().getText().trim();
        String departamento = (String) empleadosVista.getBox_departamento().getSelectedItem(); // Obtener del ComboBox

        // Validar que los campos no estén vacíos
        if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(empleadosVista, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el departamento no sea la opción predeterminada
        if ("--selecionar una opcion--".equals(departamento)) {
            JOptionPane.showMessageDialog(empleadosVista, "Por favor selecciona un departamento válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear el objeto empleado y registrarlo
        EmpleadoModelo empleado = new EmpleadoModelo(nombre, apellidos, telefono, email, departamento);
        boolean registrado = empleadoDAO.registrarEmpleado(empleado);

        // Confirmar el resultado
        if (registrado) {
            JOptionPane.showMessageDialog(empleadosVista, "Empleado registrado con éxito.", "Registrar", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos(); // Limpia los campos después de registrar
        } else {
            JOptionPane.showMessageDialog(empleadosVista, "Error al registrar el empleado.", "Registrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarEmpleado() {
        if (!modificando) {
            String nombre = JOptionPane.showInputDialog(empleadosVista, "Ingrese el nombre del empleado:");
            String apellidos = JOptionPane.showInputDialog(empleadosVista, "Ingrese los apellidos del empleado:");
            String departamento = JOptionPane.showInputDialog(empleadosVista, "Ingrese el departamento del empleado:");

            EmpleadoModelo empleado = empleadoDAO.buscarPorNombreApellidosDepto(nombre, apellidos, departamento);
            if (empleado != null) {
                empleadosVista.getTxt_nombre().setText(empleado.getNombre());
                empleadosVista.getTxt_apellidos().setText(empleado.getApellidos());
                empleadosVista.getTxt_telefono().setText(empleado.getTelefono());
                empleadosVista.getTxt_email().setText(empleado.getEmail());
                empleadosVista.getBox_departamento().setSelectedItem(empleado.getDepartamento()); // Establecer en el ComboBox
                empleadosVista.getTxt_nombre().setEditable(false);
                empleadosVista.getTxt_apellidos().setEditable(false);
                empleadosVista.getBox_departamento().setEnabled(false);
                modificando = true;
            } else {
                JOptionPane.showMessageDialog(empleadosVista, "Empleado no encontrado.", "Modificar", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String telefono = empleadosVista.getTxt_telefono().getText();
            String email = empleadosVista.getTxt_email().getText();
            String departamento = (String) empleadosVista.getBox_departamento().getSelectedItem();

            boolean modificado = empleadoDAO.modificarEmpleado(new EmpleadoModelo(
                    empleadosVista.getTxt_nombre().getText(),
                    empleadosVista.getTxt_apellidos().getText(),
                    telefono,
                    email,
                    departamento
            ));

            if (modificado) {
                JOptionPane.showMessageDialog(empleadosVista, "Empleado modificado con éxito.", "Modificar", JOptionPane.INFORMATION_MESSAGE);
                empleadosVista.getTxt_nombre().setEditable(true);
                empleadosVista.getTxt_apellidos().setEditable(true);
                empleadosVista.getBox_departamento().setEnabled(true);
                modificando = false;
            } else {
                JOptionPane.showMessageDialog(empleadosVista, "Error al modificar el empleado.", "Modificar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarEmpleado() {
        String id = JOptionPane.showInputDialog(empleadosVista, "Ingrese el ID del empleado:");
        if (id == null || id.isEmpty()) {
            return;
        }

        EmpleadoModelo empleado = empleadoDAO.buscarPorId(Integer.parseInt(id));
        if (empleado != null) {
            JOptionPane.showMessageDialog(empleadosVista,
                    "Empleado encontrado:\n"
                    + "Nombre: " + empleado.getNombre() + "\n"
                    + "Apellidos: " + empleado.getApellidos() + "\n"
                    + "Departamento: " + empleado.getDepartamento(),
                    "Buscar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(empleadosVista, "Empleado no encontrado.", "Buscar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEmpleado() {
        String id = JOptionPane.showInputDialog(empleadosVista, "Ingrese el ID del empleado a eliminar:");
        if (id == null || id.isEmpty()) {
            return;
        }

        boolean eliminado = empleadoDAO.eliminarEmpleado(Integer.parseInt(id));
        if (eliminado) {
            JOptionPane.showMessageDialog(empleadosVista, "Empleado eliminado con éxito.", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(empleadosVista, "Error al eliminar el empleado.", "Eliminar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verListaEmpleados() {
        TablaEmpleadosVista tablaVista = new TablaEmpleadosVista();
        cargarTablaEmpleados(tablaVista);
        tablaVista.setVisible(true);
    }

    private void cargarTablaEmpleados(TablaEmpleadosVista tablaVista) {
        List<EmpleadoModelo> empleados = empleadoDAO.obtenerTodos();
        DefaultTableModel modelo = (DefaultTableModel) tablaVista.getTb_empleados().getModel();
        modelo.setRowCount(0);

        for (EmpleadoModelo empleado : empleados) {
            modelo.addRow(new Object[]{
                empleado.getIdTrabajador(),
                empleado.getNombre(),
                empleado.getApellidos(),
                empleado.getTelefono(),
                empleado.getEmail(),
                empleado.getDepartamento()
            });
        }
    }

    private void limpiarCampos() {
        empleadosVista.getTxt_nombre().setText("");
        empleadosVista.getTxt_apellidos().setText("");
        empleadosVista.getTxt_telefono().setText("");
        empleadosVista.getTxt_email().setText("");
        empleadosVista.getBox_departamento().setSelectedIndex(0); // Volver a la opción predeterminada
    }

    

}
