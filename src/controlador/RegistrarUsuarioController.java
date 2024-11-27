package controlador;

import vistas.RegistrarUsuarioVista;
import dao.UsuarioDAO;
import modelo.UsuarioModelo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class RegistrarUsuarioController {

    private RegistrarUsuarioVista registrarUsuarioVista;
    private UsuarioDAO usuarioDAO;

    public RegistrarUsuarioController(RegistrarUsuarioVista registrarUsuarioVista) {
        this.registrarUsuarioVista = registrarUsuarioVista;
        this.usuarioDAO = new UsuarioDAO();

        // Configurar eventos de botones
        this.registrarUsuarioVista.getBtn_registrar().addActionListener(e -> registrarUsuario());
        this.registrarUsuarioVista.getBtn_cancelar().addActionListener(e -> cancelarRegistro());

        // Configurar combo box
        configurarComboBox(this.registrarUsuarioVista.getBox_tipo_usuario());

        // Hacer visible la vista
        this.registrarUsuarioVista.setVisible(true);
    }

    private void registrarUsuario() {
        String usuario = registrarUsuarioVista.getTxt_usuario_registro().getText().trim();
        String contraseña = String.valueOf(registrarUsuarioVista.getTxt_contra_registro().getPassword()).trim();
        String confirmarContraseña = String.valueOf(registrarUsuarioVista.getTxt_contra_confirmar_registro().getPassword()).trim();
        String email = registrarUsuarioVista.getTxt_email_registro().getText().trim();
        String telefono = registrarUsuarioVista.getTxt_telefono_registro().getText().trim();
        String tipoUsuario = (String) registrarUsuarioVista.getBox_tipo_usuario().getSelectedItem();

        if (usuario.isEmpty() || contraseña.isEmpty() || email.isEmpty() || telefono.isEmpty() || tipoUsuario.equals("--selecionar una opcion--")) {
            JOptionPane.showMessageDialog(registrarUsuarioVista, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(registrarUsuarioVista, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hash de la contraseña
        String contraseñaHasheada = hashPassword(contraseña);

        UsuarioModelo nuevoUsuario = new UsuarioModelo(usuario, contraseñaHasheada, tipoUsuario, email, telefono);

        if (usuarioDAO.registrarUsuario(nuevoUsuario)) {
            JOptionPane.showMessageDialog(registrarUsuarioVista, "Usuario registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            registrarUsuarioVista.dispose(); // Cerrar la vista de registro
        } else {
            JOptionPane.showMessageDialog(registrarUsuarioVista, "Error al registrar el usuario. Verifica los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña: " + e.getMessage());
        }
    }

    private void cancelarRegistro() {
        registrarUsuarioVista.dispose(); // Cierra la ventana de registro
    }

    private void configurarComboBox(JComboBox<String> comboBox) {
        comboBox.setSelectedIndex(0); // Selecciona la primera opción por defecto
        comboBox.addActionListener(e -> {
            if (comboBox.getSelectedIndex() == 0) {
                comboBox.setSelectedIndex(0); // Vuelve a la primera opción si intentan seleccionarla
                JOptionPane.showMessageDialog(null, "Por favor selecciona una opción válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

}
