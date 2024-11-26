package controlador.generales;

import controlador.vistaspecifica.GestionEmpleadosController;
import controlador.vistaspecifica.GestionLibrosController;
import controlador.vistaspecifica.GestionPrestamosController;
import controlador.vistaspecifica.GestionSociosController;
import controlador.vistaspecifica.PagosMultasController;
import controlador.vistaspecifica.LoginController;
import controlador.vistaspecifica.RegistrarUsuarioController;
import modelo.EmpleadoModelo; // <--- Importar el modelo de empleado
import modelo.LibroModelo;    // <--- Importar el modelo de libro si no estaba importado
import vistas.GestionEmpleadosVista;
import vistas.GestionLibrosVista;
import vistas.GestionPrestamosVista;
import vistas.GestionSociosVista;
import vistas.PagosMultasVista;
import vistas.MenuPrincipalVista;
import vistas.LoginVista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.RegistrarUsuarioVista;

public class MenuPrincipalController implements ActionListener {

    private MenuPrincipalVista menuVista;
    private String tipoUsuario;

    public MenuPrincipalController(MenuPrincipalVista menuVista, String tipoUsuario) {
        this.menuVista = menuVista;
        this.tipoUsuario = tipoUsuario;

        // Añadir los action listeners a los botones del menú principal
        this.menuVista.getBtn_gestion_libros().addActionListener(this);
        this.menuVista.getBtn_gestion_empleados().addActionListener(this);
        this.menuVista.getBtn_gestion_prestamos().addActionListener(this);
        this.menuVista.getBtn_gestion_socios().addActionListener(this);
        this.menuVista.getBtn_gestion_pagos().addActionListener(this);
        this.menuVista.getBtn_cerrar_sesion().addActionListener(this);
        this.menuVista.getBtn_registrar_usuario().addActionListener(this);

        // Configurar permisos basados en el tipo de usuario
        configurarPermisos();

        // Mostrar la vista del menú principal
        this.menuVista.setVisible(true);
    }

    private void configurarPermisos() {
        if (tipoUsuario.equals("Usuario")) {
            // Deshabilitar botones que un usuario normal no debería ver
            this.menuVista.getBtn_gestion_empleados().setEnabled(false);
            this.menuVista.getBtn_gestion_pagos().setEnabled(false);
            this.menuVista.getBtn_registrar_usuario().setEnabled(false);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == this.menuVista.getBtn_gestion_empleados()) {
            GestionEmpleadosVista vistaEmpleados = new GestionEmpleadosVista();
            EmpleadoModelo modeloEmpleado = new EmpleadoModelo(); // Crear una instancia del modelo
            new GestionEmpleadosController(modeloEmpleado, vistaEmpleados); // Pasar el modelo y la vista al controlador
            vistaEmpleados.setVisible(true);
        } else if (boton == this.menuVista.getBtn_gestion_prestamos()) {
            GestionPrestamosVista vistaPrestamos = new GestionPrestamosVista();
            new GestionPrestamosController(vistaPrestamos);
            vistaPrestamos.setVisible(true);
        } else if (boton == this.menuVista.getBtn_gestion_socios()) {
            GestionSociosVista vistaSocios = new GestionSociosVista();
            new GestionSociosController(vistaSocios);
            vistaSocios.setVisible(true);
        } else if (boton == this.menuVista.getBtn_gestion_pagos()) {
            PagosMultasVista vistaPagos = new PagosMultasVista();
            new PagosMultasController(vistaPagos);
            vistaPagos.setVisible(true);
        } else if (boton == this.menuVista.getBtn_gestion_libros()) {
            GestionLibrosVista vistaLibros = new GestionLibrosVista();
            new GestionLibrosController(vistaLibros);
            vistaLibros.setVisible(true);
        } else if (boton == this.menuVista.getBtn_cerrar_sesion()) {
            cerrarSesion();
        } else if (boton == this.menuVista.getBtn_registrar_usuario()) {
            RegistrarUsuarioVista vistaRegistrarUsuario = new RegistrarUsuarioVista();
            new RegistrarUsuarioController(vistaRegistrarUsuario);
            vistaRegistrarUsuario.setVisible(true);
        }
    }

    private void cerrarSesion() {
        // Ocultar la vista actual del menú principal
        menuVista.setVisible(false);
        // Mostrar la vista de login
        LoginVista loginVista = new LoginVista();
        new LoginController(loginVista);
        loginVista.setVisible(true);
    }
}
