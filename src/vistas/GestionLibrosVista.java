/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author souls
 */
public class GestionLibrosVista extends javax.swing.JFrame {

    /**
     * Creates new form GestionLibrosVista
     */
    public GestionLibrosVista() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_gestion_libros = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_autor = new javax.swing.JTextField();
        txt_isbn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_añadir = new javax.swing.JButton();
        btn_ver_libros = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        txt_titulo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_genero = new javax.swing.JTextField();
        comboProvincias = new javax.swing.JComboBox<>();
        comboEditorial = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        btn_gestion_libros.setBackground(new java.awt.Color(65, 237, 206));
        btn_gestion_libros.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_gestion_libros.setText("Gestión de Libros");
        btn_gestion_libros.setBorder(new javax.swing.border.MatteBorder(null));
        btn_gestion_libros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gestion_librosActionPerformed(evt);
            }
        });

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de libros");

        jPanel1.setBackground(new java.awt.Color(116, 223, 190));

        txt_autor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_autor.setToolTipText("");
        txt_autor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 176, 155), 3));
        txt_autor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txt_isbn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_isbn.setToolTipText("");
        txt_isbn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 176, 155), 3));
        txt_isbn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ISBN:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Titulo:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Autor:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Provincias:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Gestión de Libros");

        btn_buscar.setBackground(new java.awt.Color(65, 237, 206));
        btn_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_buscar.setText("Buscar");
        btn_buscar.setBorder(new javax.swing.border.MatteBorder(null));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(65, 237, 206));
        btn_eliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setBorder(new javax.swing.border.MatteBorder(null));
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_añadir.setBackground(new java.awt.Color(65, 237, 206));
        btn_añadir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_añadir.setText("Añadir");
        btn_añadir.setBorder(new javax.swing.border.MatteBorder(null));
        btn_añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_añadirActionPerformed(evt);
            }
        });

        btn_ver_libros.setBackground(new java.awt.Color(65, 237, 206));
        btn_ver_libros.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_ver_libros.setText("Ver Lista de Libros");
        btn_ver_libros.setBorder(new javax.swing.border.MatteBorder(null));
        btn_ver_libros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ver_librosActionPerformed(evt);
            }
        });

        btn_modificar.setBackground(new java.awt.Color(65, 237, 206));
        btn_modificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setBorder(new javax.swing.border.MatteBorder(null));
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        txt_titulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_titulo.setToolTipText("");
        txt_titulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 176, 155), 3));
        txt_titulo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Genero:");

        txt_genero.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_genero.setToolTipText("");
        txt_genero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 176, 155), 3));
        txt_genero.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        comboProvincias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione una Provincia--", "Alicante", "Almería", "Ávila", "Badajoz", "Baleares", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Castellón", "Ciudad Real", "Córdoba", "Coruña (A)", "Cuenca", "Girona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Jaén", "León", "Lleida", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Ourense", "Palencia", "Pontevedra", "Rioja (La)", "Salamanca", "Segovia", "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza" }));
        comboProvincias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 176, 155), 3));

        comboEditorial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione una editorial --", "Penguin Random House", "HarperCollins", "Simon & Schuster", "Hachette Livre", "Grupo Planeta", "Macmillan Publishers", "Pearson", "Bloomsbury Publishing", "Anagrama", "Alfaguara", "Alianza Editorial", "Ediciones SM", "Ediciones B", "Roca Editorial", "Salamandra", "Editorial Norma", "Santillana", "Susaeta Ediciones", "Ediciones Siruela", "Editorial Acantilado" }));
        comboEditorial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 176, 155), 3));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Editorial:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(94, 94, 94))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btn_añadir, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(44, 44, 44)
                                    .addComponent(jLabel4)
                                    .addGap(8, 8, 8))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_isbn, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_autor, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_titulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                                .addComponent(txt_genero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                                .addComponent(comboEditorial, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(btn_ver_libros, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_autor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_genero, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_añadir, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_ver_libros, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_gestion_librosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gestion_librosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_gestion_librosActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_añadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_añadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_añadirActionPerformed

    private void btn_ver_librosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_librosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ver_librosActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    public JButton getBtn_añadir() {
        return btn_añadir;
    }

    public void setBtn_añadir(JButton btn_añadir) {
        this.btn_añadir = btn_añadir;
    }

    public JButton getBtn_buscar() {
        return btn_buscar;
    }

    public void setBtn_buscar(JButton btn_buscar) {
        this.btn_buscar = btn_buscar;
    }

    public JButton getBtn_eliminar() {
        return btn_eliminar;
    }

    public void setBtn_eliminar(JButton btn_eliminar) {
        this.btn_eliminar = btn_eliminar;
    }

    public JButton getBtn_gestion_libros() {
        return btn_gestion_libros;
    }

    public void setBtn_gestion_libros(JButton btn_gestion_libros) {
        this.btn_gestion_libros = btn_gestion_libros;
    }

    public JButton getBtn_modificar() {
        return btn_modificar;
    }

    public void setBtn_modificar(JButton btn_modificar) {
        this.btn_modificar = btn_modificar;
    }

    public JButton getBtn_ver_libros() {
        return btn_ver_libros;
    }

    public void setBtn_ver_libros(JButton btn_ver_libros) {
        this.btn_ver_libros = btn_ver_libros;
    }

    public JTextField getTxt_autor() {
        return txt_autor;
    }

    public void setTxt_autor(JTextField txt_autor) {
        this.txt_autor = txt_autor;
    }

    public JTextField getTxt_isbn() {
        return txt_isbn;
    }

    public void setTxt_isbn(JTextField txt_isbn) {
        this.txt_isbn = txt_isbn;
    }

    public JTextField getTxt_titulo() {
        return txt_titulo;
    }

    public void setTxt_titulo(JTextField txt_titulo) {
        this.txt_titulo = txt_titulo;
    }

    public JComboBox<String> getComboEditorial() {
        return comboEditorial;
    }

    public void setComboEditorial(JComboBox<String> comboEditorial) {
        this.comboEditorial = comboEditorial;
    }

    public JComboBox<String> getComboProvincias() {
        return comboProvincias;
    }

    public void setComboProvincias(JComboBox<String> comboProvincias) {
        this.comboProvincias = comboProvincias;
    }

    public JTextField getTxt_genero() {
        return txt_genero;
    }

    public void setTxt_genero(JTextField txt_genero) {
        this.txt_genero = txt_genero;
    }
    
    
    

  

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_añadir;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_gestion_libros;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_ver_libros;
    private javax.swing.JComboBox<String> comboEditorial;
    private javax.swing.JComboBox<String> comboProvincias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_autor;
    private javax.swing.JTextField txt_genero;
    private javax.swing.JTextField txt_isbn;
    private javax.swing.JTextField txt_titulo;
    // End of variables declaration//GEN-END:variables
}