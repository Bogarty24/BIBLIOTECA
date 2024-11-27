package controlador;

import vistas.GestionSociosVista;
import vistas.TablaSociosVista;
import dao.SocioDAO;
import modelo.SocioModelo;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionSociosController implements ActionListener {

    private GestionSociosVista sociosVista;
    private SocioDAO socioDAO;
    private boolean modificando = false; // Flag para rastrear el estado de modificación

    public GestionSociosController(GestionSociosVista sociosVista) {
        this.sociosVista = sociosVista;
        this.socioDAO = new SocioDAO();

        // Agregar ActionListener a los botones
        this.sociosVista.getBtn_registrarSocio().addActionListener(this);
        this.sociosVista.getBtn_modificarSocio().addActionListener(this);
        this.sociosVista.getBtn_eliminarSocio().addActionListener(this);
        this.sociosVista.getBtn_buscarSocio().addActionListener(this);
        this.sociosVista.getBtn_ver_socios().addActionListener(this);

        this.sociosVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == sociosVista.getBtn_registrarSocio()) {
            registrarSocio();
        } else if (boton == sociosVista.getBtn_modificarSocio()) {
            modificarSocio();
        } else if (boton == sociosVista.getBtn_eliminarSocio()) {
            eliminarSocio();
        } else if (boton == sociosVista.getBtn_buscarSocio()) {
            buscarSocio();
        } else if (boton == sociosVista.getBtn_ver_socios()) {
            verListaSocios();
        }
    }

    private void registrarSocio() {
        String nombre = sociosVista.getTxt_nombreSocios().getText();
        String correo = sociosVista.getTxt_correoSocios().getText();
        String direccion = sociosVista.getTxt_direccionSocios().getText();
        String telefono = sociosVista.getTxt_telefonoSocios().getText();

        if (nombre.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(sociosVista, "Nombre y correo son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SocioModelo socio = new SocioModelo(nombre, correo, direccion, telefono);
        boolean registrado = socioDAO.registrarSocio(socio);
        

        if (registrado) {
            JOptionPane.showMessageDialog(sociosVista, "Socio registrado con éxito.", "Registrar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(sociosVista, "Error al registrar socio.", "Registrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarSocio() {
        if (modificando) {
            // Guardar los cambios
            String nombre = sociosVista.getTxt_nombreSocios().getText();
            String correo = sociosVista.getTxt_correoSocios().getText();
            String direccion = sociosVista.getTxt_direccionSocios().getText();
            String telefono = sociosVista.getTxt_telefonoSocios().getText();

            SocioModelo socio = new SocioModelo(nombre, correo, direccion, telefono);
            boolean modificado = socioDAO.modificarSocio(socio);

            if (modificado) {
                JOptionPane.showMessageDialog(sociosVista, "Socio modificado con éxito.", "Modificar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(sociosVista, "Error al modificar socio.", "Modificar", JOptionPane.ERROR_MESSAGE);
            }

            modificando = false;
        } else {
            // Pedir el nombre y correo
            String nombre = JOptionPane.showInputDialog(sociosVista, "Ingrese el nombre del socio:");
            String correo = JOptionPane.showInputDialog(sociosVista, "Ingrese el correo del socio:");

            SocioModelo socio = socioDAO.buscarPorNombreYCorreo(nombre, correo);
            if (socio != null) {
                sociosVista.getTxt_nombreSocios().setText(socio.getNombre());
                sociosVista.getTxt_correoSocios().setText(socio.getCorreo());
                sociosVista.getTxt_direccionSocios().setText(socio.getDireccion());
                sociosVista.getTxt_telefonoSocios().setText(socio.getTelefono());

                modificando = true;
            } else {
                JOptionPane.showMessageDialog(sociosVista, "Socio no encontrado.", "Modificar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarSocio() {
        String nombre = JOptionPane.showInputDialog(sociosVista, "Ingrese el nombre del socio:");
        String correo = JOptionPane.showInputDialog(sociosVista, "Ingrese el correo del socio:");

        boolean eliminado = socioDAO.eliminarSocio(nombre, correo);

        if (eliminado) {
            JOptionPane.showMessageDialog(sociosVista, "Socio eliminado con éxito.", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(sociosVista, "Error al eliminar socio.", "Eliminar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarSocio() {
        String id = JOptionPane.showInputDialog(sociosVista, "Ingrese el ID del socio:");
        SocioModelo socio = socioDAO.buscarPorId(Integer.parseInt(id));

        if (socio != null) {
            JOptionPane.showMessageDialog(sociosVista, "Socio encontrado: " + socio.getNombre() + ", " + socio.getCorreo(), "Buscar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(sociosVista, "Socio no encontrado.", "Buscar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verListaSocios() {
        TablaSociosVista tablaVista = new TablaSociosVista();
        new TablaSociosController(tablaVista);
    }
}
