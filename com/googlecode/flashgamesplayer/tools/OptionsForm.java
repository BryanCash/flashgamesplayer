/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OptionsForm.java
 *
 * Created on 23 Ιουν 2010, 7:07:43 πμ
 */
package com.googlecode.flashgamesplayer.tools;

import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Options;
import com.googlecode.flashgamesplayer.games.tree.GamesTree;
import com.googlecode.svalidators.formcomponents.ValidationGroup;
import com.googlecode.svalidators.validators.PositiveNumberValidator;
import com.googlecode.svalidators.validators.RequiredValidator;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssoldatos
 */
public class OptionsForm extends MyDraggable {

  /** Creates new form OptionsForm */
  public OptionsForm() {
    initComponents();
    cb_useProxyActionPerformed(null);
    setLocationRelativeTo(null);
    tf_proxy.addValidator(new RequiredValidator(tf_proxy.getText()));
    tf_port.addValidator(new PositiveNumberValidator(tf_port.getText(), false, false));
    tf_treeRowHeight.addValidator(new PositiveNumberValidator(tf_treeRowHeight.getText(), false, false));
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
    jLabel1 = new javax.swing.JLabel();
    cb_useProxy = new javax.swing.JCheckBox();
    tf_proxy = new com.googlecode.svalidators.formcomponents.STextField();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    tf_port = new com.googlecode.svalidators.formcomponents.STextField();
    button_ok = new javax.swing.JButton();
    button_cancel = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    tf_treeRowHeight = new com.googlecode.svalidators.formcomponents.STextField();
    cb_title = new javax.swing.JCheckBox();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

    jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+2));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Options");

    cb_useProxy.setSelected((Boolean)FlashGamesPlayer.options.get(Options.USE_PROXY));
    cb_useProxy.setText("use proxy");
    cb_useProxy.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cb_useProxyActionPerformed(evt);
      }
    });

    tf_proxy.setText((String)FlashGamesPlayer.options.get(Options.PROXY));
    tf_proxy.setName("Proxy"); // NOI18N

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText("Proxy:");

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText("Port:");

    tf_port.setText(String.valueOf(FlashGamesPlayer.options.get(Options.PORT)));
    tf_port.setName("Port"); // NOI18N

    button_ok.setText("Save");
    button_ok.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        button_okActionPerformed(evt);
      }
    });

    button_cancel.setText("Cancel");
    button_cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        button_cancelActionPerformed(evt);
      }
    });

    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel4.setText("Tree row height :");

    tf_treeRowHeight.setText(String.valueOf(FlashGamesPlayer.options.get(Options.TREE_ROW_HEIGHT)));
    tf_treeRowHeight.setName("Tree row height"); // NOI18N

    cb_title.setSelected((Boolean)FlashGamesPlayer.options.get(Options.DISPLAY_GAME_TITLE));
    cb_title.setText("Display Game title");
    cb_title.setToolTipText("Display the game title in the tree");
    cb_title.setName("Display Game Title"); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(cb_useProxy)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
              .addComponent(cb_title))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(tf_port, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tf_proxy, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
              .addComponent(tf_treeRowHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(button_ok)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(button_cancel)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(cb_useProxy)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(tf_proxy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(tf_treeRowHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_title)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(button_ok)
          .addComponent(button_cancel))
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

    private void button_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cancelActionPerformed
      dispose();
    }//GEN-LAST:event_button_cancelActionPerformed

    private void button_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_okActionPerformed
      if (cb_useProxy.isSelected()) {
        ValidationGroup group = new ValidationGroup();
        group.addComponent(tf_proxy);
        group.addComponent(tf_port);
        group.addComponent(tf_treeRowHeight);
        if (!group.validate()) {
          group.errorMessage(true);
          return;
        }
      }
      try {
        new Options(Options.USE_PROXY, Options.BOOLEAN, String.valueOf(cb_useProxy.isSelected())).save();
        new Options(Options.PROXY, Options.STRING, String.valueOf(tf_proxy.getText().trim())).save();
        new Options(Options.PORT, Options.INTEGER, String.valueOf(tf_port.getText().trim())).save();
        new Options(Options.TREE_ROW_HEIGHT, Options.INTEGER, String.valueOf(tf_treeRowHeight.getText().trim())).save();
        new Options(Options.DISPLAY_GAME_TITLE, Options.BOOLEAN, String.valueOf(cb_title.isSelected())).save();
        FlashGamesPlayer.setOptions(Options.getOptions());

      } catch (SQLException ex) {
        Logger.getLogger(OptionsForm.class.getName()).log(Level.SEVERE, null, ex);
      }
      dispose();
    }//GEN-LAST:event_button_okActionPerformed

    private void cb_useProxyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_useProxyActionPerformed
      tf_port.setEnabled(cb_useProxy.isSelected());
      tf_proxy.setEnabled(cb_useProxy.isSelected());
    }//GEN-LAST:event_cb_useProxyActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new OptionsForm().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton button_cancel;
  private javax.swing.JButton button_ok;
  private javax.swing.JCheckBox cb_title;
  private javax.swing.JCheckBox cb_useProxy;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private com.googlecode.svalidators.formcomponents.STextField tf_port;
  private com.googlecode.svalidators.formcomponents.STextField tf_proxy;
  private com.googlecode.svalidators.formcomponents.STextField tf_treeRowHeight;
  // End of variables declaration//GEN-END:variables
}
