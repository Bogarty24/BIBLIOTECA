package controlador.vistaspecifica;

import dao.UsuarioDAO;
import vistas.RegistrarUsuarioVista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.UsuarioModelo;

public class RegistrarUsuarioController implements ActionListener {

    private RegistrarUsuarioVista vistaRegistrarUsuario;

    public RegistrarUsuarioController(RegistrarUsuarioVista vistaRegistrarUsuario) {
        this.vistaRegistrarUsuario = vistaRegistrarUsuario;

        // Agregar ActionListener a los botones
        this.vistaRegistrarUsuario.getBtn_registrar().addActionListener(this);
        this.vistaRegistrarUsuario.getBtn_cancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == vistaRegistrarUsuario.getBtn_registrar()) {
            registrarUsuario();
        } else if (boton == vistaRegistrarUsuario.getBtn_cancelar()) {
            cancelarRegistro();
        }
    }

    private void registrarUsuario() {
        try {
            // Obtener los valores de los campos de la vista
            String nombreUsuario = vistaRegistrarUsuario.getTxt_usuario_registro().getText();
            String contrasena = new String(vistaRegistrarUsuario.getTxt_contra_registro().getPassword());
            String confirmarContrasena = new String(vistaRegistrarUsuario.getTxt_contra_confirmar_registro().getPassword());
            String tipoUsuario = (String) vistaRegistrarUsuario.getBox_tipo_usuario().getSelectedItem();
            String email = vistaRegistrarUsuario.getTxt_email_registro().getText();
            String telefono = vistaRegistrarUsuario.getTxt_telefono_registro().getText();

            // Validar que todos los campos estén completos
            if (nombreUsuario.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()
                    || tipoUsuario.equals("--seleccionar una opcion--") || email.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(vistaRegistrarUsuario, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que las contraseñas coincidan
            if (!contrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(vistaRegistrarUsuario, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear el objeto UsuarioModelo
            UsuarioModelo usuario = new UsuarioModelo(nombreUsuario, contrasena, tipoUsuario, email, telefono);

            // Intentar registrar al usuario en la base de datos
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.registrarUsuario(usuario)) {
                JOptionPane.showMessageDialog(vistaRegistrarUsuario, "Usuario registrado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vistaRegistrarUsuario, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaRegistrarUsuario, "Error al registrar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void cancelarRegistro() {
        // Ocultar la vista de registro de usuario
        vistaRegistrarUsuario.setVisible(false);
    }

    private void limpiarCampos() {
        vistaRegistrarUsuario.getTxt_usuario_registro().setText("");
        vistaRegistrarUsuario.getTxt_contra_registro().setText("");
        vistaRegistrarUsuario.getTxt_contra_confirmar_registro().setText("");
        vistaRegistrarUsuario.getBox_tipo_usuario().setSelectedIndex(0);
        vistaRegistrarUsuario.getTxt_email_registro().setText("");
        vistaRegistrarUsuario.getTxt_telefono_registro().setText("");
    }
}
