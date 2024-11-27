package controlador;

import vistas.PagosMultasVista;
import dao.PagoMultaDAO;
import modelo.PagoMultaModelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PagosMultasController {

    private PagosMultasVista pagosMultasVista;
    private PagoMultaDAO pagoMultaDAO;

    public PagosMultasController(PagosMultasVista pagosMultasVista) {
        this.pagosMultasVista = pagosMultasVista;
        this.pagoMultaDAO = new PagoMultaDAO();

        // Asociar botones y tabla con eventos
        this.pagosMultasVista.getBtn_pagar().addActionListener(e -> pagarMulta());
        this.pagosMultasVista.getBtn_eliminar_multa().addActionListener(e -> eliminarMulta());
        this.pagosMultasVista.getBtn_buscar().addActionListener(e -> buscarMulta());

        this.pagosMultasVista.getTabla_pagos_multas().getSelectionModel().addListSelectionListener(e -> habilitarBotones());

        // Inicializar la tabla
        cargarTabla(pagoMultaDAO.obtenerTodos());

        this.pagosMultasVista.setVisible(true);
    }

    private void cargarTabla(List<PagoMultaModelo> multas) {
        DefaultTableModel modelo = (DefaultTableModel) pagosMultasVista.getTabla_pagos_multas().getModel();
        modelo.setRowCount(0);

        for (PagoMultaModelo multa : multas) {
            modelo.addRow(new Object[]{
                    multa.getIdRecibo(),
                    multa.getIdSocio(),
                    multa.getMonto(),
                    multa.getEstado()
            });
        }
    }

    private void habilitarBotones() {
        int filaSeleccionada = pagosMultasVista.getTabla_pagos_multas().getSelectedRow();
        boolean habilitar = filaSeleccionada != -1;
        pagosMultasVista.getBtn_pagar().setEnabled(habilitar);
        pagosMultasVista.getBtn_eliminar_multa().setEnabled(habilitar);
    }

    private void pagarMulta() {
        int filaSeleccionada = pagosMultasVista.getTabla_pagos_multas().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(pagosMultasVista, "Seleccione una multa para marcarla como pagada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idRecibo = (int) pagosMultasVista.getTabla_pagos_multas().getValueAt(filaSeleccionada, 0);
        boolean pagado = pagoMultaDAO.actualizarEstado(idRecibo, "Pagado");

        if (pagado) {
            JOptionPane.showMessageDialog(pagosMultasVista, "Multa marcada como pagada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla(pagoMultaDAO.obtenerTodos());
        } else {
            JOptionPane.showMessageDialog(pagosMultasVista, "Error al marcar la multa como pagada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarMulta() {
        int filaSeleccionada = pagosMultasVista.getTabla_pagos_multas().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(pagosMultasVista, "Seleccione una multa para eliminarla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idRecibo = (int) pagosMultasVista.getTabla_pagos_multas().getValueAt(filaSeleccionada, 0);
        boolean eliminado = pagoMultaDAO.eliminarMulta(idRecibo);

        if (eliminado) {
            JOptionPane.showMessageDialog(pagosMultasVista, "Multa eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla(pagoMultaDAO.obtenerTodos());
        } else {
            JOptionPane.showMessageDialog(pagosMultasVista, "Error al eliminar la multa.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarMulta() {
        String textoBusqueda = pagosMultasVista.getTxt_buscar().getText();
        String metodoBusqueda = (String) pagosMultasVista.getBox_metodoBusqueda().getSelectedItem();

        if (textoBusqueda.isEmpty() || metodoBusqueda.equals("--Metodo de busqueda--")) {
            JOptionPane.showMessageDialog(pagosMultasVista, "Ingrese un criterio de búsqueda y seleccione un método.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<PagoMultaModelo> resultados;
        if (metodoBusqueda.equals("id recibo")) {
            resultados = pagoMultaDAO.buscarPorIdRecibo(Integer.parseInt(textoBusqueda));
        } else {
            resultados = pagoMultaDAO.buscarPorIdSocio(Integer.parseInt(textoBusqueda));
        }

        cargarTabla(resultados);
    }
}
