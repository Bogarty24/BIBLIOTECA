package controlador;

import vistas.TablaLibrosVista;
import dao.LibroDAO;
import modelo.LibroModelo;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TablaLibrosController {

    private TablaLibrosVista tablaLibrosVista;
    private LibroDAO libroDAO;

    public TablaLibrosController(TablaLibrosVista tablaLibrosVista) {
        this.tablaLibrosVista = tablaLibrosVista;
        this.libroDAO = new LibroDAO();

        cargarTablaLibros();
        this.tablaLibrosVista.setVisible(true);
    }

    private void cargarTablaLibros() {
        List<LibroModelo> libros = libroDAO.obtenerTodos();
        DefaultTableModel modelo = (DefaultTableModel) tablaLibrosVista.getTb_libros().getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        for (LibroModelo libro : libros) {
            modelo.addRow(new Object[]{
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.getGenero(),
                libro.getEditorial(),
                libro.getProvincia()
            });
        }
    }
}
