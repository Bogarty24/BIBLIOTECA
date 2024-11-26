package controlador.vistaspecifica;

import dao.EmpleadoDAO;
import modelo.EmpleadoModelo;
import vistas.GestionEmpleadosVista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import vistas.TablaEmpleadosVista;

public class GestionEmpleadosController implements ActionListener {

    private GestionEmpleadosVista vistaEmpleados;
    private EmpleadoDAO empleadoDAO;
    private EmpleadoModelo empleadoModelo;

    public GestionEmpleadosController(EmpleadoModelo empleadoModelo, GestionEmpleadosVista vistaEmpleados) {
        this.vistaEmpleados = vistaEmpleados;
        this.empleadoDAO = new EmpleadoDAO();
        this.empleadoModelo = empleadoModelo;

        // Agregar ActionListener a los botones
        this.vistaEmpleados.getBtn_registrar_trab().addActionListener(this);
        this.vistaEmpleados.getBtn_modificar_trab().addActionListener(this);
        this.vistaEmpleados.getBtn_buscar_trab().addActionListener(this);
        this.vistaEmpleados.getBtn_eliminar_trab().addActionListener(this);
        this.vistaEmpleados.getBtn_ver_empleado().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == vistaEmpleados.getBtn_registrar_trab()) {
            agregarEmpleado();
        } else if (boton == vistaEmpleados.getBtn_modificar_trab()) {
            modificarEmpleado();
        } else if (boton == vistaEmpleados.getBtn_buscar_trab()) {
            buscarEmpleado();
        } else if (boton == vistaEmpleados.getBtn_eliminar_trab()) {
            eliminarEmpleado();
        } else if (boton == vistaEmpleados.getBtn_ver_empleado()) {
            verListaEmpleados();
        }
    }

    private void agregarEmpleado() {
        String nombre = vistaEmpleados.getTxt_nombre_trab().getText();
        String apellidos = vistaEmpleados.getTxt_apellidos_trab().getText();
        String telefono = vistaEmpleados.getTxt_telefono_trab().getText();
        String email = vistaEmpleados.getTxt_email_trab().getText();
        String departamento = vistaEmpleados.getBox_departamento_trab().getSelectedItem().toString();

        // Validar que los campos no estén vacíos
        if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || email.isEmpty() || departamento.isEmpty()) {
            JOptionPane.showMessageDialog(vistaEmpleados, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EmpleadoModelo nuevoEmpleado = new EmpleadoModelo(0, nombre, apellidos, telefono, email, departamento);
        if (empleadoDAO.agregarEmpleado(nuevoEmpleado)) {
            JOptionPane.showMessageDialog(vistaEmpleados, "Empleado registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(vistaEmpleados, "Error al registrar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarEmpleado() {
        try {
            String idStr = JOptionPane.showInputDialog(vistaEmpleados, "Ingrese la ID del empleado a modificar:");
            if (idStr != null) {
                int idTrabajador = Integer.parseInt(idStr);
                EmpleadoModelo empleado = empleadoDAO.buscarEmpleadoPorId(idTrabajador);
                if (empleado != null) {
                    // Llenar los campos del formulario con los datos del empleado
                    vistaEmpleados.getTxt_id_trab().setText(String.valueOf(empleado.getIdTrabajador()));
                    vistaEmpleados.getTxt_nombre_trab().setText(empleado.getNombreTrabajador());
                    vistaEmpleados.getTxt_apellidos_trab().setText(empleado.getApellidoTrabajador());
                    vistaEmpleados.getTxt_telefono_trab().setText(empleado.getTelefonoTrabajador());
                    vistaEmpleados.getTxt_email_trab().setText(empleado.getEmailTrabajador());
                    vistaEmpleados.getBox_departamento_trab().setSelectedItem(empleado.getDepartamentoTrabajador());
                } else {
                    JOptionPane.showMessageDialog(vistaEmpleados, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaEmpleados, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarEmpleado() {
        try {
            int idTrabajador = Integer.parseInt(vistaEmpleados.getTxt_id_trab().getText());
            EmpleadoModelo empleado = empleadoDAO.buscarEmpleadoPorId(idTrabajador);
            if (empleado != null) {
                vistaEmpleados.getTxt_nombre_trab().setText(empleado.getNombreTrabajador());
                vistaEmpleados.getTxt_apellidos_trab().setText(empleado.getApellidoTrabajador());
                vistaEmpleados.getTxt_telefono_trab().setText(empleado.getTelefonoTrabajador());
                vistaEmpleados.getTxt_email_trab().setText(empleado.getEmailTrabajador());
                vistaEmpleados.getBox_departamento_trab().setSelectedItem(empleado.getDepartamentoTrabajador());
            } else {
                JOptionPane.showMessageDialog(vistaEmpleados, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaEmpleados, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEmpleado() {
        try {
            int idTrabajador = Integer.parseInt(vistaEmpleados.getTxt_id_trab().getText());
            int confirmacion = JOptionPane.showConfirmDialog(vistaEmpleados, "¿Está seguro de que desea eliminar este empleado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (empleadoDAO.eliminarEmpleado(idTrabajador)) {
                    JOptionPane.showMessageDialog(vistaEmpleados, "Empleado eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(vistaEmpleados, "Error al eliminar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaEmpleados, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verListaEmpleados() {
        List<EmpleadoModelo> empleados = empleadoDAO.obtenerEmpleados();
        if (!empleados.isEmpty()) {
            TablaEmpleadosVista vistaTabla = new TablaEmpleadosVista();
            DefaultTableModel modeloTabla = (DefaultTableModel) vistaTabla.getTb_empleados().getModel();
            modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenar

            for (EmpleadoModelo empleado : empleados) {
                Object[] fila = {
                    empleado.getIdTrabajador(),
                    empleado.getNombreTrabajador(),
                    empleado.getApellidoTrabajador(),
                    empleado.getTelefonoTrabajador(),
                    empleado.getEmailTrabajador(),
                    empleado.getDepartamentoTrabajador()
                };
                modeloTabla.addRow(fila);
            }

            vistaTabla.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(vistaEmpleados, "No hay empleados registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
