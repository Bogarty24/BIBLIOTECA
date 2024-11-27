package controlador;

import vistas.MenuPrincipalVista;
import vistas.LoginVista;
import vistas.GestionLibrosVista;
import vistas.GestionSociosVista;
import vistas.GestionEmpleadosVista;
import vistas.PagosMultasVista;
import vistas.GestionPrestamosVista;
import vistas.RegistrarUsuarioVista;

import javax.swing.JComboBox;
import utils.VentanaUtils;

public class MenuPrincipalController {

    private MenuPrincipalVista menuVista;
    private String rol;

    public MenuPrincipalController(MenuPrincipalVista menuVista, String rol) {
        this.menuVista = menuVista;
        this.rol = rol;

        // Configurar la vista según el rol del usuario
        configurarVistaPorRol();

        // Asociar botones con acciones
        this.menuVista.getBtn_gestion_libros().addActionListener(e -> abrirGestionLibros());
        this.menuVista.getBtn_gestion_socios().addActionListener(e -> abrirGestionSocios());
        this.menuVista.getBtn_gestion_empleados().addActionListener(e -> abrirGestionEmpleados());
        this.menuVista.getBtn_gestion_pagos().addActionListener(e -> abrirGestionPagosMultas());
        this.menuVista.getBtn_gestion_prestamos().addActionListener(e -> abrirGestionPrestamos());
        this.menuVista.getBtn_registrar_usuario().addActionListener(e -> abrirRegistrarUsuario());
        this.menuVista.getBtn_cerrar_sesion().addActionListener(e -> cerrarSesion());

        // Configurar combo boxes si los hay en esta vista (opcional)
        configurarComboBoxes();

        // Hacer visible el menú principal
        this.menuVista.setVisible(true);
    }

    private void configurarVistaPorRol() {
        System.out.println("Configurando vista para el rol: " + rol);
        if ("Usuario".equalsIgnoreCase(rol)) {
            // Restringir acceso a ciertas funcionalidades para usuarios normales
            menuVista.getBtn_gestion_empleados().setEnabled(false);
            menuVista.getBtn_gestion_pagos().setEnabled(false);
        } else if ("Administrador".equalsIgnoreCase(rol)) {
            // Administrador tiene acceso completo
            System.out.println("Acceso completo habilitado para Administrador.");
        } else {
            // Rol desconocido, configuración por defecto
            System.out.println("Rol desconocido. Aplicando configuración predeterminada.");
        }
    }

    private void configurarComboBoxes() {
        // Configura los combo boxes de la vista para prevenir seleccionar la primera opción
        // Ejemplo: Si hay un combo box en esta vista que necesita configuración
        // configurarComboBox(menuVista.getBox_tipo_usuario());
    }

    private void configurarComboBox(JComboBox<String> comboBox) {
        comboBox.setSelectedIndex(0); // Selecciona la primera opción por defecto
        comboBox.addActionListener(e -> {
            if (comboBox.getSelectedIndex() == 0) {
                comboBox.setSelectedIndex(0); // Evita cambiar la selección si la primera opción está seleccionada
                System.out.println("Por favor selecciona una opción válida.");
            }
        });
    }

    private void abrirGestionLibros() {
        System.out.println("Abriendo gestión de libros...");
        GestionLibrosVista librosVista = new GestionLibrosVista();
     
        new GestionLibrosController(librosVista); // Instanciar el controlador correspondiente
    }

    private void abrirGestionSocios() {
        System.out.println("Abriendo gestión de socios...");
        GestionSociosVista sociosVista = new GestionSociosVista();
        
        new GestionSociosController(sociosVista); // Instanciar el controlador correspondiente
    }

    private void abrirGestionEmpleados() {
        System.out.println("Abriendo gestión de empleados...");
        GestionEmpleadosVista empleadosVista = new GestionEmpleadosVista();
        new GestionEmpleadosController(empleadosVista); // Instanciar el controlador correspondiente
    }

    private void abrirGestionPagosMultas() {
        System.out.println("Abriendo gestión de pagos y multas...");
        PagosMultasVista pagosMultasVista = new PagosMultasVista();
        new PagosMultasController(pagosMultasVista); // Instanciar el controlador correspondiente
    }

    private void abrirGestionPrestamos() {
        System.out.println("Abriendo gestión de préstamos...");
        GestionPrestamosVista prestamosVista = new GestionPrestamosVista();
        new GestionPrestamosController(prestamosVista); // Instanciar el controlador correspondiente
    }

    private void abrirRegistrarUsuario() {
        System.out.println("Abriendo registro de usuario...");
        RegistrarUsuarioVista registrarVista = new RegistrarUsuarioVista();
        new RegistrarUsuarioController(registrarVista); // Instanciar el controlador correspondiente
    }

    private void cerrarSesion() {
        System.out.println("Cerrando sesión...");
        menuVista.dispose(); // Cierra el menú principal
        LoginVista loginVista = new LoginVista(); // Instanciar la vista del login
        new LoginController(loginVista); // Asociar el controlador del login
    }
}
