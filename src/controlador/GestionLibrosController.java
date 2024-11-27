package controlador;

import vistas.GestionLibrosVista;
import dao.LibroDAO;
import modelo.LibroModelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import vistas.TablaLibrosVista;

public class GestionLibrosController implements ActionListener {

    private GestionLibrosVista librosVista;
    private LibroDAO libroDAO;

    public GestionLibrosController(GestionLibrosVista librosVista) {
        this.librosVista = librosVista;
        this.libroDAO = new LibroDAO();

        // Agregar ActionListener a los botones
        this.librosVista.getBtn_buscar().addActionListener(this);
        this.librosVista.getBtn_eliminar().addActionListener(this);
        this.librosVista.getBtn_añadir().addActionListener(this);
        this.librosVista.getBtn_ver_libros().addActionListener(this);
        this.librosVista.getBtn_modificar().addActionListener(this);

        this.librosVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == librosVista.getBtn_buscar()) {
            buscarLibro();
        } else if (boton == librosVista.getBtn_eliminar()) {
            eliminarLibro();
        } else if (boton == librosVista.getBtn_añadir()) {
            añadirLibro();
        } else if (boton == librosVista.getBtn_ver_libros()) {
            verListaLibros();
        } else if (boton == librosVista.getBtn_modificar()) {
            modificarLibro();
        } else {
            System.out.println("Evento desconocido detectado.");
        }
    }

    private void buscarLibro() {
        // Mostrar un cuadro de diálogo para seleccionar la opción de búsqueda
        String[] opciones = {"Buscar por ID", "Buscar por Título", "Buscar por ISBN"};
        String opcion = (String) JOptionPane.showInputDialog(
                librosVista,
                "Selecciona una opción de búsqueda:",
                "Buscar Libro",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (opcion != null) {
            switch (opcion) {
                case "Buscar por ID":
                    String id = JOptionPane.showInputDialog(librosVista, "Ingrese el ID del libro:");
                    buscarPorID(id);
                    break;
                case "Buscar por Título":
                    String titulo = JOptionPane.showInputDialog(librosVista, "Ingrese el Título del libro:");
                    buscarPorTitulo(titulo);
                    break;
                case "Buscar por ISBN":
                    String isbn = JOptionPane.showInputDialog(librosVista, "Ingrese el ISBN del libro:");
                    buscarPorISBN(isbn);
                    break;
            }
        }
    }

    private void buscarPorID(String id) {
        LibroModelo libro = libroDAO.buscarPorId(Integer.parseInt(id));
        if (libro != null) {
            JOptionPane.showMessageDialog(librosVista, "Libro encontrado: " + libro.getTitulo(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(librosVista, "Libro no encontrado.", "Resultado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPorTitulo(String titulo) {
        List<LibroModelo> libros = libroDAO.buscarPorTitulo(titulo);
        if (!libros.isEmpty()) {
            JOptionPane.showMessageDialog(librosVista, "Libros encontrados: " + libros.size(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(librosVista, "No se encontraron libros.", "Resultado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPorISBN(String isbn) {
        LibroModelo libro = libroDAO.buscarPorIsbn(isbn);
        if (libro != null) {
            JOptionPane.showMessageDialog(librosVista, "Libro encontrado: " + libro.getTitulo(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(librosVista, "Libro no encontrado.", "Resultado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLibro() {
        String id = JOptionPane.showInputDialog(librosVista, "Ingrese el ID del libro:");
        String nombre = JOptionPane.showInputDialog(librosVista, "Ingrese el Nombre del libro:");

        boolean eliminado = libroDAO.eliminar(Integer.parseInt(id), nombre);
        if (eliminado) {
            JOptionPane.showMessageDialog(librosVista, "Libro eliminado con éxito.", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(librosVista, "No se pudo eliminar el libro.", "Eliminar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void añadirLibro() {
        String titulo = librosVista.getTxt_titulo().getText();
        String autor = librosVista.getTxt_autor().getText();
        String isbn = librosVista.getTxt_isbn().getText();
        String genero = librosVista.getTxt_genero().getText();

        boolean agregado = libroDAO.agregar(new LibroModelo(titulo, autor, isbn, genero));
        if (agregado) {
            JOptionPane.showMessageDialog(librosVista, "Libro añadido con éxito.", "Añadir", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(librosVista, "No se pudo añadir el libro.", "Añadir", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean modificando = false; // Flag para rastrear el estado de modificación

    private void modificarLibro() {
        if (modificando) {
            // Si estamos en el proceso de modificación, guardar los cambios
            int confirmacion = JOptionPane.showConfirmDialog(librosVista, "¿Desea guardar los cambios?", "Modificar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Crear un modelo con los datos modificados
                LibroModelo libro = new LibroModelo(
                        librosVista.getTxt_titulo().getText(),
                        librosVista.getTxt_autor().getText(),
                        librosVista.getTxt_isbn().getText(),
                        librosVista.getTxt_genero().getText(),
                        librosVista.getComboEditorial().getSelectedItem().toString(),
                        librosVista.getComboProvincias().getSelectedItem().toString()
                );

                boolean modificado = libroDAO.modificarPorISBN(libro);
                if (modificado) {
                    JOptionPane.showMessageDialog(librosVista, "Libro modificado con éxito.", "Modificar", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(librosVista, "No se pudo modificar el libro.", "Modificar", JOptionPane.ERROR_MESSAGE);
                }

                // Deshabilitar los campos tras guardar y restablecer el estado
                setCamposEditable(false);
                modificando = false;
            }
        } else {
            // Solicitar el ISBN del libro
            String isbn = JOptionPane.showInputDialog(librosVista, "Ingrese el ISBN del libro para modificar:");
            if (isbn != null && !isbn.isEmpty()) {
                LibroModelo libro = libroDAO.buscarPorIsbn(isbn);
                if (libro != null) {
                    // Cargar datos en los campos de texto y combobox
                    librosVista.getTxt_titulo().setText(libro.getTitulo());
                    librosVista.getTxt_autor().setText(libro.getAutor());
                    librosVista.getTxt_isbn().setText(libro.getIsbn());
                    librosVista.getTxt_genero().setText(libro.getGenero());
                    librosVista.getComboEditorial().setSelectedItem(libro.getEditorial());
                    librosVista.getComboProvincias().setSelectedItem(libro.getProvincia());

                    // Hacer los campos editables y establecer el estado
                    setCamposEditable(true);
                    modificando = true;
                } else {
                    JOptionPane.showMessageDialog(librosVista, "Libro no encontrado.", "Modificar", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void setCamposEditable(boolean editable) {
        librosVista.getTxt_titulo().setEditable(editable);
        librosVista.getTxt_autor().setEditable(editable);
        librosVista.getTxt_isbn().setEditable(editable);
        librosVista.getTxt_genero().setEditable(editable);
        librosVista.getComboEditorial().setEnabled(editable);
        librosVista.getComboProvincias().setEnabled(editable);
    }

    private void verListaLibros() {
        TablaLibrosVista tablaVista = new TablaLibrosVista();
        new TablaLibrosController(tablaVista);
    }

}
