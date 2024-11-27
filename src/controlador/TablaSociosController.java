package controlador;

import vistas.TablaSociosVista;
import dao.SocioDAO;
import modelo.SocioModelo;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TablaSociosController {

    private TablaSociosVista tablaSociosVista;
    private SocioDAO socioDAO;

    public TablaSociosController(TablaSociosVista tablaSociosVista) {
        this.tablaSociosVista = tablaSociosVista;
        this.socioDAO = new SocioDAO();

        cargarTablaSocios(); // Cargar los datos en la tabla
        this.tablaSociosVista.setVisible(true);
    }

    private void cargarTablaSocios() {
        List<SocioModelo> socios = socioDAO.obtenerTodos(); // Obtener la lista de socios desde la base de datos
        DefaultTableModel modelo = (DefaultTableModel) tablaSociosVista.getTb_socios().getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        // Recorrer la lista de socios y agregar cada uno como una fila en la tabla
        for (SocioModelo socio : socios) {
            modelo.addRow(new Object[]{
                    socio.getIdSocio(),
                    socio.getNombre(),
                    socio.getCorreo(),
                    socio.getDireccion(),
                    socio.getTelefono()
            });
        }
    }
}
