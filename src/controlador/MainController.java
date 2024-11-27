package controlador;

import vistas.LoginVista;
import vistas.MenuPrincipalVista;


public class MainController {
    public static void main(String[] args) {
        // Iniciar la vista de Login como punto de entrada
        LoginVista loginVista = new LoginVista();
        LoginController loginController = new LoginController(loginVista);

        // La lógica del LoginController se encargará de abrir el MenuPrincipalVista tras un inicio de sesión exitoso.
    }
}
