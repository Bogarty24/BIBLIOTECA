package controlador;

import vistas.LoginVista;
import vistas.MenuPrincipalVista;
import dao.UsuarioDAO;

import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {

    private LoginVista loginVista;
    private UsuarioDAO usuarioDAO;

    public LoginController(LoginVista loginVista) {
        this.loginVista = loginVista;
        this.usuarioDAO = new UsuarioDAO();

        // Asociar eventos al botón de inicio de sesión
        this.loginVista.getBtn_iniciar_sesion().addActionListener(e -> iniciarSesion());

        // Hacer visible la vista de login
        this.loginVista.setVisible(true);
    }

    private void iniciarSesion() {
        String usuario = loginVista.getTxt_usuario().getText().trim();
        String contraseña = String.valueOf(loginVista.getTxt_pass().getPassword()).trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(loginVista, "Por favor ingresa el usuario y la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hashear la contraseña ingresada
        String contraseñaHasheada = hashPassword(contraseña);

        // Verificar credenciales
        String rol = usuarioDAO.verificarCredenciales(usuario, contraseñaHasheada);

        if (rol != null) {
            // Credenciales válidas, abrir menú principal
            JOptionPane.showMessageDialog(loginVista, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            loginVista.dispose(); // Cerrar la ventana de login
            MenuPrincipalVista menuVista = new MenuPrincipalVista();
            new MenuPrincipalController(menuVista, rol); // Pasar el rol al menú principal
        } else {
            // Credenciales inválidas
            JOptionPane.showMessageDialog(loginVista, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña: " + e.getMessage());
        }
    }
}
