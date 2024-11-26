package controlador.generales;

import controlador.vistaspecifica.LoginController;
import vistas.LoginVista;
import vistas.MenuPrincipalVista;

public class MainController {
    public static void main(String[] args) {
        // Crear la vista del login
        LoginVista loginVista = new LoginVista();
        
        // Crear el controlador del login
        LoginController loginController = new LoginController(loginVista);
        
        // Hacer visible la vista de login
        loginVista.setVisible(true);
    }
}
