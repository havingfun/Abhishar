/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewsDisplay.java
 *
 * Created on Sep 29, 2013, 4:32:36 PM
 */

package qaiiitm;

import org.jdesktop.application.Action;
import qaiiitm.QAiiiTMView.*;
/**
 *
 * @author FoUkat
 */
public class NewsDisplay extends javax.swing.JFrame {

    /** Creates new form NewsDisplay */
    public NewsDisplay() {
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

        jLabel2 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        news1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        news2 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        news3 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        news4 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qaiiitm.QAiiiTMApp.class).getContext().getResourceMap(NewsDisplay.class);
        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setForeground(resourceMap.getColor("Form.foreground")); // NOI18N
        setName("Form"); // NOI18N

        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        news1.setColumns(20);
        news1.setRows(5);
        news1.setText(resourceMap.getString("news1.text")); // NOI18N
        news1.setName("news1"); // NOI18N
        jScrollPane2.setViewportView(news1);

        jScrollPane2.setBounds(350, 30, 560, 100);
        jLayeredPane1.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        news2.setColumns(20);
        news2.setRows(5);
        news2.setName("news2"); // NOI18N
        jScrollPane4.setViewportView(news2);

        jScrollPane4.setBounds(30, 150, 570, 100);
        jLayeredPane1.add(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        news3.setColumns(20);
        news3.setRows(5);
        news3.setName("news3"); // NOI18N
        jScrollPane7.setViewportView(news3);

        jScrollPane7.setBounds(350, 270, 560, 100);
        jLayeredPane1.add(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane8.setName("jScrollPane8"); // NOI18N

        news4.setColumns(20);
        news4.setRows(5);
        news4.setName("news4"); // NOI18N
        jScrollPane8.setViewportView(news4);

        jScrollPane8.setBounds(30, 400, 570, 100);
        jLayeredPane1.add(jScrollPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(30, 310, 230, 30);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(640, 440, 230, 30);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setBounds(650, 190, 230, 30);
        jLayeredPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(650, 190, 230, 30);
        jLayeredPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setBounds(50, 60, 280, 30);
        jLayeredPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBackground(resourceMap.getColor("jLayeredPane2.background")); // NOI18N
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLayeredPane2.setForeground(resourceMap.getColor("jLayeredPane2.foreground")); // NOI18N
        jLayeredPane2.setName("jLayeredPane2"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setBounds(410, 20, 110, 60);
        jLayeredPane2.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(qaiiitm.QAiiiTMApp.class).getContext().getActionMap(NewsDisplay.class, this);
        jButton1.setAction(actionMap.get("listenGoogle")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setBounds(50, 50, 90, 40);
        jLayeredPane2.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setAction(actionMap.get("hideForm")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setBounds(800, 50, 90, 40);
        jLayeredPane2.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewsDisplay().setVisible(true);
            }
        });
    }

    @Action
    public void hideForm() {
        this.setVisible(false);
    }

    @Action
    public void listenGoogle() {
        QAiiiTMView qvw = new QAiiiTMView(null);
        String listen = qvw.googleListen();
        processNews(listen);

    }

    public String processNews(String newsxml)
    {
        String sml=null;



        return sml;

    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextArea news1;
    private javax.swing.JTextArea news2;
    private javax.swing.JTextArea news3;
    private javax.swing.JTextArea news4;
    // End of variables declaration//GEN-END:variables

}
