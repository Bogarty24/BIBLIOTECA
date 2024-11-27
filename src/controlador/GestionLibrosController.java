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
        String titulo = librosVista.getTxt_titulo().getText().trim();
        String autor = librosVista.getTxt_autor().getText().trim();
        String isbn = librosVista.getTxt_isbn().getText().trim();
        String genero = librosVista.getTxt_genero().getText().trim();
        String editorial = (String) librosVista.getComboEditorial().getSelectedItem();
        String provincia = (String) librosVista.getComboProvincias().getSelectedItem();

        // Validar campos vacíos
        if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || genero.isEmpty()) {
            JOptionPane.showMessageDialog(librosVista, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar combo box de editorial
        if (editorial.equals("--Seleccione una editorial--")) {
            JOptionPane.showMessageDialog(librosVista, "Por favor selecciona una editorial válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar combo box de provincias
        if (provincia.equals("--Seleccione una Provincia--")) {
            JOptionPane.showMessageDialog(librosVista, "Por favor selecciona una provincia válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear el objeto libro
        LibroModelo libro = new LibroModelo(titulo, autor, isbn, genero, editorial, provincia);

        // Llamar al DAO para registrar el libro
        boolean registrado = libroDAO.agregar(libro);

        if (registrado) {
            JOptionPane.showMessageDialog(librosVista, "Libro agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCamposLibro(); // Limpia los campos después de agregar
        } else {
            JOptionPane.showMessageDialog(librosVista, "Error al agregar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void limpiarCamposLibro() {
        librosVista.getTxt_titulo().setText("");
        librosVista.getTxt_autor().setText("");
        librosVista.getTxt_isbn().setText("");
        librosVista.getTxt_genero().setText("");
        librosVista.getComboEditorial().setSelectedIndex(0); // Volver a la opción predeterminada
        librosVista.getComboProvincias().setSelectedIndex(0); // Volver a la opción predeterminada
    }

}
