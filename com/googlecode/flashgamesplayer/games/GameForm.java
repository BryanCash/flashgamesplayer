/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GameForm.java
 *
 * Created on 18 Ιουν 2010, 8:09:51 μμ
 */
package com.googlecode.flashgamesplayer.games;

import com.googlecode.svalidators.formcomponents.ValidationGroup;
import com.googlecode.svalidators.validators.FileValidator;
import com.googlecode.svalidators.validators.RequiredValidator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Game;
import com.googlecode.flashgamesplayer.database.Genre;
import com.googlecode.flashgamesplayer.database.Options;
import com.googlecode.flashgamesplayer.tools.MyDraggable;
import com.googlecode.flashgamesplayer.tools.MyFunctions;
import com.googlecode.flashgamesplayer.tools.MyMessages;

/**
 *
 * @author lordovol
 */
public class GameForm extends MyDraggable {

  private ArrayList<JComponent> components = new ArrayList<JComponent>();
  DefaultComboBoxModel genresModel;
  Game game;
  String newFileName= "";

  public GameForm(File file) {
     this(new Game(), file);
  }

  /** Creates new form GameForm */
  public GameForm() {
    this(new Game(),null);
  }

  public GameForm(Game game, File file) {
    this.game = game;
    genresModel = new DefaultComboBoxModel(Genre.getAll());
    if(file != null){
      try {
        newFileName = file.getCanonicalPath();
      } catch (IOException ex) {
        FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      }
    }
    initComponents();
    tf_file.addValidator(new FileValidator(newFileName, FileValidator.Type.FILE, false));
    tf_title.addValidator(new RequiredValidator());
    combo_genre.addValidator(new RequiredValidator());
    components.add(tf_file);
    components.add(combo_genre);
    components.add(tf_title);
    Genre genre = Genre.getGenreById(game.getGenre_id());
    combo_genre.setSelectedItem(genre);
    addPropertyChangeListener(new GamesChangeListener());
    setLocationRelativeTo(null);
    setVisible(true);
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
    bt_add = new javax.swing.JButton();
    bt_cancel = new javax.swing.JButton();
    label_title = new javax.swing.JLabel();
    label_genre = new javax.swing.JLabel();
    label_file = new javax.swing.JLabel();
    tf_title = new com.googlecode.svalidators.formcomponents.STextField();
    combo_genre = new com.googlecode.svalidators.formcomponents.SComboBox();
    tf_file = new com.googlecode.svalidators.formcomponents.STextField();
    bt_browse = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    cb_internet = new javax.swing.JCheckBox();
    jLabel3 = new javax.swing.JLabel();
    cb_deleted = new javax.swing.JCheckBox();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

    jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+2));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText(game.isNewGame() ? "Add new game" : "Edit game");

    bt_add.setText(game.isNewGame() ? "Add" : "Edit");
    bt_add.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_addActionPerformed(evt);
      }
    });

    bt_cancel.setText("Cancel");
    bt_cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_cancelActionPerformed(evt);
      }
    });

    label_title.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    label_title.setText("Title:");

    label_genre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    label_genre.setText("Genre:");

    label_file.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    label_file.setText("Swf File:");

    tf_title.setText(game.getTitle());
    tf_title.setName("Title"); // NOI18N

    combo_genre.setEditable(true);
    combo_genre.setModel(genresModel);
    combo_genre.setName("Genre"); // NOI18N

    tf_file.setEditable(game.getId() == 0);
    tf_file.setText(game.isNewGame() ? newFileName :Options.USER_DIR + Options.GAMES_DIR +game.getFilename());
    tf_file.setName("Swf file"); // NOI18N

    bt_browse.setText("Browse");
    bt_browse.setEnabled(game.getId()==0);
    bt_browse.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_browseActionPerformed(evt);
      }
    });

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText("Needs Internet :");

    cb_internet.setSelected(game.isInternet() ==1);

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText("Deleted :");

    cb_deleted.setSelected(game.getDeleted()==1);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(bt_add)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(bt_cancel))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addComponent(label_genre, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_genre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addComponent(label_title, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_title, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                  .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(label_file, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                  .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(cb_deleted)
                  .addComponent(cb_internet)
                  .addComponent(tf_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(bt_browse)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(label_title)
          .addComponent(tf_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(label_genre)
          .addComponent(combo_genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(label_file)
          .addComponent(tf_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(bt_browse))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
          .addComponent(jLabel2)
          .addComponent(cb_internet))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
          .addComponent(jLabel3)
          .addComponent(cb_deleted))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(bt_add)
          .addComponent(bt_cancel))
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
      ValidationGroup group = new ValidationGroup(components);
      Genre genre = new Genre();
      int genreId = 0;
      if (!group.validate()) {
        group.errorMessage(true);
      } else {
        Object obj = combo_genre.getSelectedItem();
        if (obj instanceof Genre) {
          genre = (Genre) obj;
          genreId = genre.getId();
        } else {
          genre.setGenre(obj.toString().trim());

          try {
            genreId = genre.save();
          } catch (SQLException ex) {
            Logger.getLogger(GameForm.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        String title = tf_title.getText().trim();
        String swfFile = tf_file.getText().trim();
        String filename = new File(tf_file.getText().trim()).getName();
        game.setGenre_id(genreId);
        game.setTitle(title);
        if (game.isNewGame()) {
          game.setFilename(filename);
        }
        game.setInternet(cb_internet.isSelected() ? 1 : 0);
        game.setDeleted(cb_deleted.isSelected() ? 1 : 0);
        try {
          MyFunctions.copyfile(swfFile, Options.USER_DIR + Options.GAMES_DIR + filename);
        } catch (FileNotFoundException ex) {
          FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
        }
        try {
          if (game.save() > -1) {
            MyMessages.message("Game " + (game.isNewGame() ? " added " : " updated"), "The game was " + (game.isNewGame() ? " added " : " updated"));
            firePropertyChange(GamesChangeListener.GAME_ADDED, null, null);
          }
        } catch (SQLException ex) {
          new File(Options.USER_DIR + Options.GAMES_DIR + filename).delete();
          FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
        }
      }
      dispose();
    }//GEN-LAST:event_bt_addActionPerformed

    private void bt_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_browseActionPerformed
      JFileChooser fc = new JFileChooser(Options.USER_DIR);
      fc.setApproveButtonText("Import");
      fc.setDialogTitle("Import a flash game");
      fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fc.setDialogType(JFileChooser.OPEN_DIALOG);
      fc.setFileFilter(new FileFilter() {

        @Override
        public boolean accept(File f) {
          if (f.getName().endsWith(".swf") || f.isDirectory()) {
            return true;
          }
          return false;
        }

        @Override
        public String getDescription() {
          return "*.swf(Flash file)";
        }
      });
      fc.showOpenDialog(this);
      File f = fc.getSelectedFile();
      if (f != null) {
        try {
          tf_file.setText(f.getCanonicalPath());
        } catch (IOException ex) {
          Logger.getLogger(GameForm.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }//GEN-LAST:event_bt_browseActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton bt_add;
  private javax.swing.JButton bt_browse;
  private javax.swing.JButton bt_cancel;
  private javax.swing.JCheckBox cb_deleted;
  private javax.swing.JCheckBox cb_internet;
  private com.googlecode.svalidators.formcomponents.SComboBox combo_genre;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JLabel label_file;
  private javax.swing.JLabel label_genre;
  private javax.swing.JLabel label_title;
  private com.googlecode.svalidators.formcomponents.STextField tf_file;
  private com.googlecode.svalidators.formcomponents.STextField tf_title;
  // End of variables declaration//GEN-END:variables
}
