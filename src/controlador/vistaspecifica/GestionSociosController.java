package controlador.vistaspecifica;

import dao.SocioDAO;
import modelo.SocioModelo;
import vistas.GestionSociosVista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import vistas.TablaSociosVista;

public class GestionSociosController implements ActionListener {

    private GestionSociosVista vistaSocios;
    private SocioDAO socioDAO;
    private TablaSociosVista tablaSociosVista;

    public GestionSociosController(GestionSociosVista vistaSocios) {
        this.vistaSocios = vistaSocios;
        this.socioDAO = new SocioDAO();
        this.tablaSociosVista = new TablaSociosVista();

        // Agregar ActionListener a los botones
        this.vistaSocios.getBtn_registrarSocio().addActionListener(this);
        this.vistaSocios.getBtn_modificarSocio().addActionListener(this);
        this.vistaSocios.getBtn_buscarSocio().addActionListener(this);
        this.vistaSocios.getBtn_eliminarSocio().addActionListener(this);
        this.vistaSocios.getBtn_ver_socios().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == vistaSocios.getBtn_registrarSocio()) {
            agregarSocio();
        } else if (boton == vistaSocios.getBtn_modificarSocio()) {
            modificarSocio();
        } else if (boton == vistaSocios.getBtn_buscarSocio()) {
            buscarSocio();
        } else if (boton == vistaSocios.getBtn_eliminarSocio()) {
            eliminarSocio();
        } else if (boton == vistaSocios.getBtn_ver_socios()) {
            verListaSocios();
        }
    }

    private void agregarSocio() {
        String nombre = vistaSocios.getTxt_nombreSocios().getText();
        String direccion = vistaSocios.getTxt_direccionSocios().getText();
        String telefono = vistaSocios.getTxt_telefonoSocios().getText();
        String correo = vistaSocios.getTxt_correoSocios().getText();

        SocioModelo nuevoSocio = new SocioModelo(0, nombre, direccion, telefono, correo);
        if (socioDAO.agregarSocio(nuevoSocio)) {
            JOptionPane.showMessageDialog(vistaSocios, "Socio registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(vistaSocios, "Error al registrar el socio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarSocio() {
        try {
            String idStr = JOptionPane.showInputDialog(vistaSocios, "Ingrese la ID del socio a modificar:");
            if (idStr != null) {
                int idSocio = Integer.parseInt(idStr);
                SocioModelo socio = socioDAO.buscarSocioPorId(idSocio);
                if (socio != null) {
                    // Llenar los campos del formulario con los datos del socio
                    vistaSocios.getTxt_nombreSocios().setText(socio.getNombre());
                    vistaSocios.getTxt_direccionSocios().setText(socio.getDireccion());
                    vistaSocios.getTxt_telefonoSocios().setText(socio.getTelefono());
                    vistaSocios.getTxt_correoSocios().setText(socio.getCorreo());
                } else {
                    JOptionPane.showMessageDialog(vistaSocios, "Socio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaSocios, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarSocio() {
        try {
            String idStr = JOptionPane.showInputDialog(vistaSocios, "Ingrese la ID del socio a buscar:");
            if (idStr != null) {
                int idSocio = Integer.parseInt(idStr);
                SocioModelo socio = socioDAO.buscarSocioPorId(idSocio);
                if (socio != null) {
                    vistaSocios.getTxt_nombreSocios().setText(socio.getNombre());
                    vistaSocios.getTxt_direccionSocios().setText(socio.getDireccion());
                    vistaSocios.getTxt_telefonoSocios().setText(socio.getTelefono());
                    vistaSocios.getTxt_correoSocios().setText(socio.getCorreo());
                } else {
                    JOptionPane.showMessageDialog(vistaSocios, "Socio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaSocios, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSocio() {
        try {
            String idStr = JOptionPane.showInputDialog(vistaSocios, "Ingrese la ID del socio a eliminar:");
            if (idStr != null) {
                int idSocio = Integer.parseInt(idStr);
                int confirmacion = JOptionPane.showConfirmDialog(vistaSocios, "¿Está seguro de que desea eliminar este socio?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    if (socioDAO.eliminarSocio(idSocio)) {
                        JOptionPane.showMessageDialog(vistaSocios, "Socio eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(vistaSocios, "Error al eliminar el socio.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaSocios, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verListaSocios() {
        List<SocioModelo> socios = socioDAO.obtenerSocios();
        DefaultTableModel model = (DefaultTableModel) tablaSociosVista.getTb_socios().getModel();
        model.setRowCount(0); // Limpiar la tabla

        for (SocioModelo socio : socios) {
            model.addRow(new Object[]{socio.getIdSocio(), socio.getNombre(), socio.getDireccion(), socio.getTelefono(), socio.getCorreo()});
        }

        tablaSociosVista.setVisible(true);
    }
}
