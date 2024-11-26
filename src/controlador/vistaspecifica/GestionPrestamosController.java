package controlador.vistaspecifica;

import dao.LibroDAO;
import dao.PagoMultaDAO;
import dao.PrestamoDAO;
import dao.SocioDAO;
import modelo.PrestamoModelo;
import vistas.GestionPrestamosVista;
import vistas.PagosMultasVista;
import vistas.EstadoLibroVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionPrestamosController implements ActionListener {

    private GestionPrestamosVista vistaPrestamos;
    private PrestamoDAO prestamoDAO;
    private PagoMultaDAO pagosMultasDAO;
    private LibroDAO libroDAO;
    private SocioDAO socioDAO;

    public GestionPrestamosController(GestionPrestamosVista vistaPrestamos) {
        this.vistaPrestamos = vistaPrestamos;
        this.prestamoDAO = new PrestamoDAO();
        this.pagosMultasDAO = new PagoMultaDAO();
        this.libroDAO = new LibroDAO();    // Instanciar LibroDAO
        this.socioDAO = new SocioDAO();    // Instanciar SocioDAO

        // Agregar ActionListener a los botones
        this.vistaPrestamos.getBtn_registrar().addActionListener(this);
        this.vistaPrestamos.getBtn_devolver().addActionListener(this);
        this.vistaPrestamos.getBtn_pagos_multas().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == vistaPrestamos.getBtn_registrar()) {
            registrarPrestamo();
        } else if (boton == vistaPrestamos.getBtn_devolver()) {
            devolverPrestamo();
        } else if (boton == vistaPrestamos.getBtn_pagos_multas()) {
            abrirPagosMultasVista();
        }
    }

    private void registrarPrestamo() {
        try {
            int idLibro = Integer.parseInt(vistaPrestamos.getTxt_id_libro().getText());
            int idSocio = Integer.parseInt(vistaPrestamos.getTxt_id_socio().getText());
            String fechaPrestamoStr = vistaPrestamos.getTxt_fecha_prestamo().getText();

            // Verificar si el libro y el socio existen
            if (!libroDAO.existeLibro(idLibro)) {
                JOptionPane.showMessageDialog(vistaPrestamos, "El ID del libro no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!socioDAO.existeSocio(idSocio)) {
                JOptionPane.showMessageDialog(vistaPrestamos, "El ID del socio no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Convertir la fecha al formato adecuado
                String fechaPrestamo = convertirFecha(fechaPrestamoStr);

                PrestamoModelo nuevoPrestamo = new PrestamoModelo(0, idLibro, idSocio, fechaPrestamo, null);
                if (prestamoDAO.agregarPrestamo(nuevoPrestamo)) {
                    JOptionPane.showMessageDialog(vistaPrestamos, "Préstamo registrado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(vistaPrestamos, "Error al registrar el préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(vistaPrestamos, "Formato de fecha inválido. Debe ser dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaPrestamos, "ID de Libro o Socio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String convertirFecha(String fechaStr) throws ParseException {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = formatoEntrada.parse(fechaStr);
        return formatoSalida.format(fecha);
    }

    private void devolverPrestamo() {
        try {
            int idPrestamo = Integer.parseInt(JOptionPane.showInputDialog(vistaPrestamos, "Ingrese el ID del préstamo a devolver:"));
            PrestamoModelo prestamo = prestamoDAO.buscarPrestamoPorId(idPrestamo);
            if (prestamo != null) {
                // Mostrar la ventana para elegir el estado del libro
                String[] opcionesEstado = {"Roto", "Leve", "Nuevo"};
                String estadoLibro = (String) JOptionPane.showInputDialog(vistaPrestamos, "Estado del libro:", "Estado del libro", JOptionPane.QUESTION_MESSAGE, null, opcionesEstado, opcionesEstado[0]);

                double montoAdicional = 0.0;
                if (estadoLibro.equals("Roto")) {
                    montoAdicional = 20.0; // Cobrar el libro completo
                } else if (estadoLibro.equals("Leve")) {
                    montoAdicional = 3.0; // Cobrar por daños leves
                }

                // Actualizar la multa con el monto adicional
                pagosMultasDAO.actualizarMulta(prestamo.getIdPrestamo(), montoAdicional);

                if (prestamoDAO.devolverPrestamo(idPrestamo)) {
                    JOptionPane.showMessageDialog(vistaPrestamos, "Préstamo devuelto correctamente.");
                } else {
                    JOptionPane.showMessageDialog(vistaPrestamos, "Error al devolver el préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vistaPrestamos, "Préstamo no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaPrestamos, "ID de Préstamo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirPagosMultasVista() {
        PagosMultasVista vistaPagos = new PagosMultasVista();
        new PagosMultasController(vistaPagos);
        vistaPagos.setVisible(true);
    }
}
