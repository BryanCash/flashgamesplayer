/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddInternetGame.java
 *
 * Created on 14 Ιουλ 2010, 8:56:36 πμ
 */
package com.googlecode.flashgamesplayer.games.internet;

import com.googlecode.flashgamesplayer.tools.MyDraggable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ssoldatos
 */
public class AddInternetGame extends MyDraggable {

  DefaultComboBoxModel foundModel = new DefaultComboBoxModel();
  private final String url;

  public AddInternetGame(String url) {
    this.url = url;
    initComponents();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void find(){
    Finder finder = new Finder(this);
    Thread t = new Thread(finder);
    t.start();
  }

    /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    lb_title = new javax.swing.JLabel();
    bt_ok = new javax.swing.JButton();
    bt_cancel = new javax.swing.JButton();
    lb_found = new javax.swing.JLabel();
    comb_swf = new javax.swing.JComboBox();
    progress = new javax.swing.JProgressBar();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

    lb_title.setFont(lb_title.getFont().deriveFont(lb_title.getFont().getStyle() | java.awt.Font.BOLD, lb_title.getFont().getSize()+2));
    lb_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lb_title.setText("Add Game From Internet");

    bt_ok.setText("OK");
    bt_ok.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_okActionPerformed(evt);
      }
    });

    bt_cancel.setText("Cancel");
    bt_cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_cancelActionPerformed(evt);
      }
    });

    lb_found.setText("Swf files found in page :");

    comb_swf.setModel(foundModel);

    progress.setIndeterminate(true);
    progress.setString("Searchin in : " + url);
    progress.setStringPainted(true);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(comb_swf, javax.swing.GroupLayout.Alignment.LEADING, 0, 281, Short.MAX_VALUE)
          .addComponent(lb_title, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
          .addComponent(progress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
          .addComponent(lb_found, javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
            .addComponent(bt_ok)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(bt_cancel)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lb_title)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(lb_found)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(comb_swf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(bt_ok)
          .addComponent(bt_cancel))
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
      dispose();
    }//GEN-LAST:event_bt_cancelActionPerformed

    private void bt_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_okActionPerformed
      dispose();
    }//GEN-LAST:event_bt_okActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton bt_cancel;
  private javax.swing.JButton bt_ok;
  private javax.swing.JComboBox comb_swf;
  private javax.swing.JPanel jPanel1;
  javax.swing.JLabel lb_found;
  private javax.swing.JLabel lb_title;
  javax.swing.JProgressBar progress;
  // End of variables declaration//GEN-END:variables
}
