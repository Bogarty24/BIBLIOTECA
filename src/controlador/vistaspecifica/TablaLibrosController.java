package controlador.vistaspecifica;

import modelo.LibroModelo;
import vistas.TablaLibrosVista;
import dao.LibroDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class TablaLibrosController {
    private LibroModelo modeloLibro;
    private TablaLibrosVista vistaTabla;
    private LibroDAO libroDAO;

    public TablaLibrosController(LibroModelo modelo, TablaLibrosVista vista) {
        this.modeloLibro = modelo;
        this.vistaTabla = vista;
        this.libroDAO = new LibroDAO();
        cargarLibrosEnTabla();
        this.vistaTabla.setVisible(true);
    }

    // Método para cargar los datos en la tabla
    private void cargarLibrosEnTabla() {
        List<LibroModelo> listaLibros = libroDAO.obtenerLibros();
        DefaultTableModel modeloTabla = (DefaultTableModel) vistaTabla.getTb_libros().getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla

        for (LibroModelo libro : listaLibros) {
            Object[] fila = {
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.getGenero()
            };
            modeloTabla.addRow(fila);
        }
    }
}
