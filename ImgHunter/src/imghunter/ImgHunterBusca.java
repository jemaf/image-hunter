/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on 26/04/2010, 18:07:08
 */

package imghunter;

/**
 *
 * @author eduardo
 */
public class ImgHunterBusca extends javax.swing.JFrame {

    /** Creates new form NewJFrame */
    public ImgHunterBusca() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jscpMetricas = new javax.swing.JScrollPane();
        jlistMetricas = new javax.swing.JList();
        lblTitulo = new javax.swing.JLabel();
        jbtnCancelar = new javax.swing.JButton();
        jbtnBuscar = new javax.swing.JButton();
        jscpBusca = new javax.swing.JScrollPane();
        jlistBuscas = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jscpMetricas.setName("jscpMetricas"); // NOI18N

        jlistMetricas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Histograma (RGB)", "Amostragem", "HSV", "YUV" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlistMetricas.setName("jlistMetricas"); // NOI18N
        jscpMetricas.setViewportView(jlistMetricas);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(imghunter.ImgHunterApp.class).getContext().getResourceMap(ImgHunterBusca.class);
        lblTitulo.setText(resourceMap.getString("lblTitulo.text")); // NOI18N
        lblTitulo.setName("lblTitulo"); // NOI18N

        jbtnCancelar.setText(resourceMap.getString("jbtnCancelar.text")); // NOI18N
        jbtnCancelar.setName("jbtnCancelar"); // NOI18N
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jbtnBuscar.setText(resourceMap.getString("jbtnBuscar.text")); // NOI18N
        jbtnBuscar.setName("jbtnBuscar"); // NOI18N
        jbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarActionPerformed(evt);
            }
        });

        jscpBusca.setName("jscpBusca"); // NOI18N

        jlistBuscas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "City Block", "Cosseno", "Euclidiana", "Xadrez" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlistBuscas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistBuscas.setName("jlistBuscas"); // NOI18N
        jscpBusca.setViewportView(jlistBuscas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbtnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jscpBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jscpMetricas, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(lblTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jscpMetricas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnCancelar)
                            .addComponent(jbtnBuscar)))
                    .addComponent(jscpBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtnBuscarActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_jbtnCancelarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImgHunterBusca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JList jlistBuscas;
    private javax.swing.JList jlistMetricas;
    private javax.swing.JScrollPane jscpBusca;
    private javax.swing.JScrollPane jscpMetricas;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables

}