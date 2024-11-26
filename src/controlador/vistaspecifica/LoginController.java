package controlador.vistaspecifica;

import controlador.generales.MenuPrincipalController;
import vistas.LoginVista;
import vistas.MenuPrincipalVista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginVista loginVista;

    public LoginController(LoginVista loginVista) {
        this.loginVista = loginVista;
        this.loginVista.getBtn_iniciar_sesion().addActionListener(this);
        this.loginVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == loginVista.getBtn_iniciar_sesion()) {
            iniciarSesion();
        }
    }

    private void iniciarSesion() {
        String usuario = loginVista.getTxt_usuario().getText();
        String contrasena = new String(loginVista.getTxt_pass().getPassword());
        String tipoUsuarioSeleccionado = loginVista.getComboLogin().getSelectedItem().toString();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(loginVista, "Por favor, complete todos los campos.", "Campos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de credenciales (este es un ejemplo básico, puedes agregar lógica más compleja)
        if (usuario.equals("admin") && contrasena.equals("admin123") && tipoUsuarioSeleccionado.equals("Administrador")) {
            abrirMenuPrincipal("Administrador");
        } else if (usuario.equals("user") && contrasena.equals("user123") && tipoUsuarioSeleccionado.equals("Usuario")) {
            abrirMenuPrincipal("Usuario");
        } else {
            JOptionPane.showMessageDialog(loginVista, "Usuario o contraseña incorrectos.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirMenuPrincipal(String tipoUsuario) {
        // Ocultar la vista de login
        loginVista.setVisible(false);

        // Crear una nueva instancia de la vista del menú principal y su controlador
        MenuPrincipalVista menuVista = new MenuPrincipalVista();
        new MenuPrincipalController(menuVista, tipoUsuario);
        menuVista.setVisible(true);

    }
}
