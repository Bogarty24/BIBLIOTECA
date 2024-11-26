package controlador.vistaspecifica;

import dao.LibroDAO;
import modelo.LibroModelo;
import vistas.GestionLibrosVista;
import vistas.TablaLibrosVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionLibrosController implements ActionListener {

    private GestionLibrosVista vistaLibros;
    private LibroDAO libroDAO;
    private TablaLibrosVista tablaLibrosVista;

    public GestionLibrosController(GestionLibrosVista vistaLibros) {
        this.vistaLibros = vistaLibros;
        this.libroDAO = new LibroDAO();
        this.tablaLibrosVista = new TablaLibrosVista();

        // Agregar ActionListener a los botones
        this.vistaLibros.getBtn_añadir().addActionListener(this);
        this.vistaLibros.getBtn_modificar().addActionListener(this);
        this.vistaLibros.getBtn_buscar().addActionListener(this);
        this.vistaLibros.getBtn_eliminar().addActionListener(this);
        this.vistaLibros.getBtn_ver_libros().addActionListener(this); // Botón para ver lista de libros
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == vistaLibros.getBtn_añadir()) {
            agregarLibro();
        } else if (boton == vistaLibros.getBtn_modificar()) {
            modificarLibro();
        } else if (boton == vistaLibros.getBtn_buscar()) {
            buscarLibro();
        } else if (boton == vistaLibros.getBtn_eliminar()) {
            eliminarLibro();
        } else if (boton == vistaLibros.getBtn_ver_libros()) {
            verListaLibros();
        }
    }

    private void agregarLibro() {
        try {
            String titulo = vistaLibros.getTxt_titulo().getText();
            String autor = vistaLibros.getTxt_autor().getText();
            String isbn = vistaLibros.getTxt_isbn().getText();
            String genero = vistaLibros.getTxt_genero().getText();
            String editorial = (String) vistaLibros.getComboEditorial().getSelectedItem();
            String provincia = (String) vistaLibros.getComboProvincias().getSelectedItem();

            // Crear una nueva instancia de LibroModelo con todos los campos
            LibroModelo nuevoLibro = new LibroModelo(0, titulo, autor, isbn, genero, editorial, provincia);

            if (libroDAO.agregarLibro(nuevoLibro)) {
                JOptionPane.showMessageDialog(vistaLibros, "Libro agregado correctamente.");
                actualizarTabla(); // Actualizar la tabla después de agregar el libro
            } else {
                JOptionPane.showMessageDialog(vistaLibros, "Error al agregar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaLibros, "Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarLibro() {
        try {
            String isbn = vistaLibros.getTxt_isbn().getText();
            if (isbn != null && !isbn.isEmpty()) {
                // Obtener los valores actualizados desde los campos de la vista
                String titulo = vistaLibros.getTxt_titulo().getText();
                String autor = vistaLibros.getTxt_autor().getText();
                String genero = vistaLibros.getTxt_genero().getText();
                String editorial = (String) vistaLibros.getComboEditorial().getSelectedItem();
                String provincia = (String) vistaLibros.getComboProvincias().getSelectedItem();

                // Crear instancia de LibroModelo con todos los datos actualizados
                LibroModelo libroActualizado = new LibroModelo(0, titulo, autor, isbn, genero, editorial, provincia);

                // Actualizar en la base de datos
                if (libroDAO.modificarLibro(libroActualizado)) {
                    JOptionPane.showMessageDialog(vistaLibros, "Libro modificado correctamente.");
                    actualizarTabla(); // Actualizar la tabla después de modificar el libro
                } else {
                    JOptionPane.showMessageDialog(vistaLibros, "Error al modificar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vistaLibros, "Debe buscar un libro antes de modificar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaLibros, "Error al modificar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarLibro() {
        try {
            // Pedir el ISBN del libro a buscar
            String isbn = JOptionPane.showInputDialog(vistaLibros, "Ingrese el ISBN del libro a buscar:");

            if (isbn != null && !isbn.trim().isEmpty()) {
                LibroModelo libro = libroDAO.buscarLibroPorIsbn(isbn);

                if (libro != null) {
                    // Llenar los campos del formulario con los datos del libro
                    vistaLibros.getTxt_isbn().setText(libro.getIsbn());
                    vistaLibros.getTxt_titulo().setText(libro.getTitulo());
                    vistaLibros.getTxt_autor().setText(libro.getAutor());
                    vistaLibros.getTxt_genero().setText(libro.getGenero());
                    vistaLibros.getComboEditorial().setSelectedItem(libro.getEditorial());
                    vistaLibros.getComboProvincias().setSelectedItem(libro.getProvincia());
                } else {
                    JOptionPane.showMessageDialog(vistaLibros, "Libro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vistaLibros, "El ISBN no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaLibros, "Error al buscar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLibro() {
        try {
            // Pedir el ISBN del libro a eliminar
            String isbn = JOptionPane.showInputDialog(vistaLibros, "Ingrese el ISBN del libro a eliminar:");

            if (isbn != null && !isbn.trim().isEmpty()) {
                // Confirmación para proceder con la eliminación
                int confirmacion = JOptionPane.showConfirmDialog(vistaLibros, "¿Está seguro de que desea eliminar el libro con ISBN: " + isbn + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Intentar eliminar el libro
                    if (libroDAO.eliminarLibro(isbn)) {
                        JOptionPane.showMessageDialog(vistaLibros, "Libro eliminado correctamente.");
                        actualizarTabla(); // Actualizar la tabla después de eliminar el libro
                    } else {
                        JOptionPane.showMessageDialog(vistaLibros, "Error al eliminar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(vistaLibros, "El ISBN no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaLibros, "Error al eliminar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verListaLibros() {
        actualizarTabla(); // Llamar a actualizar tabla antes de mostrar la lista
        tablaLibrosVista.setVisible(true);
    }

    private void actualizarTabla() {
        List<LibroModelo> libros = libroDAO.obtenerLibros();
        DefaultTableModel model = (DefaultTableModel) tablaLibrosVista.getTb_libros().getModel();
        model.setRowCount(0); // Limpiar la tabla

        for (LibroModelo libro : libros) {
            model.addRow(new Object[]{
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
