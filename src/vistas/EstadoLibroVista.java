package vistas;

import javax.swing.*;

public class EstadoLibroVista extends JFrame {
    private JComboBox<String> comboEstado;
    private JButton btnConfirmar;
    
    public EstadoLibroVista() {
        setTitle("Estado del Libro al Devolver");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblEstado = new JLabel("Estado del Libro:");
        lblEstado.setBounds(30, 30, 120, 25);
        add(lblEstado);

        comboEstado = new JComboBox<>(new String[]{"Nuevo", "Leve", "Roto"});
        comboEstado.setBounds(150, 30, 100, 25);
        add(comboEstado);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(90, 100, 120, 30);
        add(btnConfirmar);
    }

    public JComboBox<String> getComboEstado() {
        return comboEstado;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }
}
