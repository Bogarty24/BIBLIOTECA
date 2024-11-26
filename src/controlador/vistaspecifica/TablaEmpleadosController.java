package controlador.vistaspecifica;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.EmpleadoModelo;
import dao.EmpleadoDAO;
import vistas.TablaEmpleadosVista;

public class TablaEmpleadosController {
    private EmpleadoModelo modeloEmpleado;
    private TablaEmpleadosVista vistaEmpleadosTabla;

    public TablaEmpleadosController(EmpleadoModelo modelo, TablaEmpleadosVista vista) {
        this.modeloEmpleado = modelo;
        this.vistaEmpleadosTabla = vista;

        // Cargar los datos en la tabla
        cargarEmpleadosEnTabla();

        // Mostrar la vista de la tabla de empleados
        this.vistaEmpleadosTabla.setVisible(true);
    }

    private void cargarEmpleadosEnTabla() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<EmpleadoModelo> listaEmpleados = empleadoDAO.obtenerEmpleados();
        DefaultTableModel modeloTabla = (DefaultTableModel) vistaEmpleadosTabla.getTb_empleados().getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla

        for (EmpleadoModelo empleado : listaEmpleados) {
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
    }
}
