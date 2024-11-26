package controlador.vistaspecifica;

import dao.PagoMultaDAO;
import modelo.PagosMultaModelo;
import vistas.PagosMultasVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PagosMultasController implements ActionListener {

    private PagosMultasVista vistaPagosMultas;
    private PagoMultaDAO pagoMultaDAO;

    public PagosMultasController(PagosMultasVista vistaPagosMultas) {
        this.vistaPagosMultas = vistaPagosMultas;
        this.pagoMultaDAO = new PagoMultaDAO();
        
        // Cargar los datos al abrir la vista
        cargarMultasEnTabla();
        
        // Agregar ActionListener a los botones
        this.vistaPagosMultas.getBtn_buscar().addActionListener(this);
        this.vistaPagosMultas.getBtn_eliminar_multa().addActionListener(this);
        this.vistaPagosMultas.getBtn_pagar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();

        if (boton == vistaPagosMultas.getBtn_buscar()) {
            buscarMulta();
        } else if (boton == vistaPagosMultas.getBtn_eliminar_multa()) {
            eliminarMulta();
        } else if (boton == vistaPagosMultas.getBtn_pagar()) {
            pagarMulta();
        }
    }

    private void cargarMultasEnTabla() {
        DefaultTableModel model = (DefaultTableModel) vistaPagosMultas.getTabla_pagos_multas().getModel();
        model.setRowCount(0); // Limpiar la tabla

        List<PagosMultaModelo> listaMultas = pagoMultaDAO.obtenerTodasLasMultas();

        for (PagosMultaModelo multa : listaMultas) {
            model.addRow(new Object[]{
                multa.getIdRecibo(),
                multa.getIdSocio(),
                multa.getMonto(),
                multa.getEstado()
            });
        }
    }

    private void buscarMulta() {
        try {
            int idRecibo = Integer.parseInt(vistaPagosMultas.getTxt_buscar().getText());
            PagosMultaModelo multa = pagoMultaDAO.buscarMultaPorId(idRecibo);
            if (multa != null) {
                DefaultTableModel model = (DefaultTableModel) vistaPagosMultas.getTabla_pagos_multas().getModel();
                model.setRowCount(0); // Limpiar la tabla
                model.addRow(new Object[]{multa.getIdRecibo(), multa.getIdSocio(), multa.getMonto(), multa.getEstado()});
            } else {
                JOptionPane.showMessageDialog(vistaPagosMultas, "Multa no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaPagosMultas, "ID de recibo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarMulta() {
        try {
            int idRecibo = Integer.parseInt(vistaPagosMultas.getTxt_buscar().getText());
            int confirmacion = JOptionPane.showConfirmDialog(vistaPagosMultas, "¿Está seguro de que desea eliminar esta multa?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (pagoMultaDAO.eliminarMulta(idRecibo)) {
                    JOptionPane.showMessageDialog(vistaPagosMultas, "Multa eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(vistaPagosMultas, "Error al eliminar la multa.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaPagosMultas, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pagarMulta() {
        try {
            int idRecibo = Integer.parseInt(vistaPagosMultas.getTxt_buscar().getText());
            if (pagoMultaDAO.pagarMulta(idRecibo)) {
                JOptionPane.showMessageDialog(vistaPagosMultas, "Multa pagada correctamente.");
            } else {
                JOptionPane.showMessageDialog(vistaPagosMultas, "Error al pagar la multa.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaPagosMultas, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
