/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * flashplayer.java
 *
 * Created on 18 Ιουν 2010, 1:51:35 μμ
 */
package com.googlecode.flashgamesplayer;

import java.beans.PropertyChangeEvent;
import java.sql.SQLException;
import com.googlecode.flashgamesplayer.games.GamePanel;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import com.googlecode.starrating.StarRating;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.basic.BasicComboBoxUI.PropertyChangeHandler;
import com.googlecode.flashgamesplayer.database.Database;
import com.googlecode.flashgamesplayer.database.Game;
import com.googlecode.flashgamesplayer.games.GameForm;
import com.googlecode.flashgamesplayer.tools.GamesChangeListener;
import com.googlecode.flashgamesplayer.tools.Options;
import com.googlecode.flashgamesplayer.tools.myLogger;

/**
 *
 * @author ssoldatos
 */
public class FlashGamesPlayer extends javax.swing.JFrame{

  private static final long serialVersionUID = 3425346456L;
  public static Logger logger;
  public static Database database;
  public static GamePanel gamePanel;

  /** Creates new form flashplayer */
  public FlashGamesPlayer() {
    createLogger();
    createDatabase();
    createFolder(Options.USER_DIR + Options.GAMES_DIR);
    logger.log(Level.INFO, "Creating the GUI");
    initComponents();
    rating.addPropertyChangeListener(new PropertyChangeListener() {

      public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(StarRating.RATE_CHANGED)) {
          double rate = (Double) evt.getNewValue();
          Game game = gamePanel.getGame();
          try {
            Game.updateRate(game.getId(), rate);
          } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
          }
        }
      }
    });
    gamePanel = new GamePanel();
    panelMain.add(gamePanel);
    setSize(800, 600);
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

    splitpane = new javax.swing.JSplitPane();
    left = new javax.swing.JPanel();
    gamesTree = new com.googlecode.flashgamesplayer.games.GamesTree();
    right = new javax.swing.JPanel();
    panelMain = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    label_gameTitle = new javax.swing.JLabel();
    rating = new com.googlecode.starrating.StarRating();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    tf_plays = new javax.swing.JTextField();
    menuBar = new javax.swing.JMenuBar();
    gamesMenu = new javax.swing.JMenu();
    menuItem_addGame = new javax.swing.JMenuItem();
    menuItem_exit = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Flash games player v0.1");
    setMinimumSize(new java.awt.Dimension(600, 400));

    splitpane.setDividerLocation(200);

    javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
    left.setLayout(leftLayout);
    leftLayout.setHorizontalGroup(
      leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(leftLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(gamesTree, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        .addContainerGap())
    );
    leftLayout.setVerticalGroup(
      leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(leftLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(gamesTree, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        .addContainerGap())
    );

    gamesTree.populateTree();

    splitpane.setLeftComponent(left);

    panelMain.setBackground(new java.awt.Color(255, 255, 255));
    panelMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    panelMain.setLayout(new javax.swing.BoxLayout(panelMain, javax.swing.BoxLayout.LINE_AXIS));

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));
    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    label_gameTitle.setFont(label_gameTitle.getFont().deriveFont(label_gameTitle.getFont().getStyle() | java.awt.Font.BOLD, label_gameTitle.getFont().getSize()+3));
    label_gameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    label_gameTitle.setText("  ");

    rating.setRatingEnabled(false);
    rating.setValueLabelVisible(true);

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel1.setText("Rating:");

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText("Plays:");

    tf_plays.setEditable(false);
    tf_plays.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(label_gameTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tf_plays, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(label_gameTitle)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
          .addComponent(jLabel1)
          .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2)
          .addComponent(tf_plays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout rightLayout = new javax.swing.GroupLayout(right);
    right.setLayout(rightLayout);
    rightLayout.setHorizontalGroup(
      rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(panelMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
          .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    rightLayout.setVerticalGroup(
      rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(rightLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        .addContainerGap())
    );

    splitpane.setRightComponent(right);

    gamesMenu.setText("Games");

    menuItem_addGame.setText("Add Game");
    menuItem_addGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_addGameActionPerformed(evt);
      }
    });
    gamesMenu.add(menuItem_addGame);

    menuItem_exit.setText("Exit");
    menuItem_exit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_exitActionPerformed(evt);
      }
    });
    gamesMenu.add(menuItem_exit);

    menuBar.add(gamesMenu);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(splitpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(splitpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void menuItem_addGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_addGameActionPerformed
    GameForm gameform = new GameForm();
  }//GEN-LAST:event_menuItem_addGameActionPerformed

  private void menuItem_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_exitActionPerformed
    System.exit(0);
  }//GEN-LAST:event_menuItem_exitActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    UIUtils.setPreferredLookAndFeel();
    NativeInterface.open();
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new FlashGamesPlayer();
      }
    });
    NativeInterface.runEventPump();

  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenu gamesMenu;
  public static com.googlecode.flashgamesplayer.games.GamesTree gamesTree;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  public static javax.swing.JLabel label_gameTitle;
  private javax.swing.JPanel left;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem menuItem_addGame;
  private javax.swing.JMenuItem menuItem_exit;
  private javax.swing.JPanel panelMain;
  public static com.googlecode.starrating.StarRating rating;
  private javax.swing.JPanel right;
  private javax.swing.JSplitPane splitpane;
  public static javax.swing.JTextField tf_plays;
  // End of variables declaration//GEN-END:variables

  private void createDatabase() {
    database = new Database();
  }

  private void createLogger() {
    logger = myLogger.createHtmlLogger("FLASHGAMESPLAYER", Options.USER_DIR + "FlashGamesPlayer", 262144, true, 1);
    logger.setLevel(Level.ALL);
  }

  private void createFolder(String dirPath) {
    if (!new File(dirPath).isDirectory()) {
      if (new File(dirPath).mkdir()) {
        logger.log(Level.INFO, "Created directory " + dirPath);
      } else {
        logger.log(Level.SEVERE, "Could not create directory " + dirPath);
      }

    }
  }
}
