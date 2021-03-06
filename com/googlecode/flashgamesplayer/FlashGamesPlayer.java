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

import java.awt.AWTException;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import com.googlecode.flashgamesplayer.games.GamePanel;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import com.googlecode.starrating.StarRating;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.googlecode.flashgamesplayer.database.Database;
import com.googlecode.flashgamesplayer.database.Game;
import com.googlecode.flashgamesplayer.database.Options;
import com.googlecode.flashgamesplayer.games.GameForm;
import com.googlecode.flashgamesplayer.games.internet.AddInternetGame;
import com.googlecode.flashgamesplayer.tools.MySortComboRenderer;
import com.googlecode.flashgamesplayer.games.tree.GamesCellRenderer;
import com.googlecode.flashgamesplayer.games.tree.GamesTree;
import com.googlecode.flashgamesplayer.genres.GenresForm;
import com.googlecode.flashgamesplayer.myEvents.MyEvent;
import com.googlecode.flashgamesplayer.myEvents.MyEventHandler;
import com.googlecode.flashgamesplayer.myEvents.MyEventsClass;
import com.googlecode.flashgamesplayer.tools.GamesLogger;
import com.googlecode.flashgamesplayer.tools.MyFileDropListener;
import com.googlecode.flashgamesplayer.tools.MyFunctions;
import com.googlecode.flashgamesplayer.tools.MyMessages;
import com.googlecode.flashgamesplayer.tools.OptionsForm;
import com.googlecode.svalidators.formcomponents.ValidationGroup;
import com.googlecode.svalidators.validators.UrlValidator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.iharder.dnd.FileDrop;

/**
 *
 * @author ssoldatos
 */
public class FlashGamesPlayer extends javax.swing.JFrame {

  private static final long serialVersionUID = 3425346456L;
  public static Logger logger;
  public static Database database;
  public static GamePanel gamePanel;
  public static boolean isInternet = false;
  public static HashMap<String, Object> options;
  public static String version = "0.9";
  private final File tmpFile;
  public static MyEventsClass evClass = new MyEventsClass();

  /** Creates new form flashplayer */
  public FlashGamesPlayer() {
    setIconImage(new ImageIcon(FlashGamesPlayer.class.getResource(
        "/com/googlecode/flashgamesplayer/images/icon.png")).getImage());
    tmpFile = new File(Options.USER_DIR + ".tmp");
    if (tmpFile.exists()) {
      MyMessages.message("Application open", "The application is already open");
      System.exit(0);
    } else {
      try {
        tmpFile.createNewFile();
        tmpFile.deleteOnExit();
      } catch (IOException ex) {
        logger.log(Level.SEVERE, null, ex);
      }
    }

    UIManager.put("Tree.expandedIcon",
        new ImageIcon(""));
    UIManager.put("Tree.collapsedIcon",
        new ImageIcon(""));

    createLogger();
    createDatabase();
    createFolder(Options.USER_DIR + Options.GAMES_DIR);
    createFolder(Options.USER_DIR + Options.SCREENSHOT_DIR);
    createFolder(Options.USER_DIR + Options.CATEGORIES_DIR);
    logger.log(Level.INFO, "Creating the GUI");
    options = Options.getOptions();
    gamePanel = new GamePanel();
    initComponents();
    //setSize(800, 600);
    setLocationRelativeTo(null);
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(true);
    gamesTree.populateTree();
    tf_addInternetGame.addValidator(new UrlValidator("", false));
    combo_sort.setRenderer(new MySortComboRenderer());
    setTitle("Flash games player version " + version + " ( " + MyFunctions.getTotalGames() + " available games )");
    splitpane.setDividerLocation((Integer) options.get(Options.TREE_ROW_HEIGHT)
        + ((Boolean) options.get(Options.DISPLAY_GAME_TITLE) ? 180 : 0) + 120);
    bt_internetActionPerformed(null);
    rating.addPropertyChangeListener(new PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(StarRating.RATE_CHANGED)) {
          double rate = (Double) evt.getNewValue();
          Game game = gamePanel.getGame();
          game.setRate(rate);
        }
      }
    });

    panelMain.add(gamePanel);
    gamesTree.tree.setRowHeight((Integer) options.get(Options.TREE_ROW_HEIGHT) + 8);
    gamesTree.tree.setCellRenderer(new GamesCellRenderer());

    new FileDrop(splitpane, BorderFactory.createLineBorder(Color.RED, 2), new MyFileDropListener());

  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    toolbar = new javax.swing.JToolBar();
    bt_addGame = new javax.swing.JButton();
    bt_editGame = new javax.swing.JButton();
    bt_stop = new javax.swing.JButton();
    bt_delete = new javax.swing.JButton();
    bt_restore = new javax.swing.JButton();
    bt_screenshot = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JToolBar.Separator();
    bt_internet = new javax.swing.JButton();
    bt_options = new javax.swing.JButton();
    bt_exit = new javax.swing.JButton();
    addInternetGamePanel = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    tf_addInternetGame = new com.googlecode.svalidators.formcomponents.STextField();
    bt_addInternetGame = new javax.swing.JButton();
    splitpane = new javax.swing.JSplitPane();
    left = new javax.swing.JPanel();
    gamesTree = new com.googlecode.flashgamesplayer.games.tree.GamesTree();
    combo_sort = new javax.swing.JComboBox();
    right = new javax.swing.JPanel();
    panelMain = new javax.swing.JPanel();
    panel_header = new javax.swing.JPanel();
    label_gameTitle = new javax.swing.JLabel();
    rating = new com.googlecode.starrating.StarRating();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    tf_plays = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    tf_password = new javax.swing.JTextField();
    bt_savePassword = new javax.swing.JButton();
    menuBar = new javax.swing.JMenuBar();
    gamesMenu = new javax.swing.JMenu();
    menuItem_addGame = new javax.swing.JMenuItem();
    menuItem_editGame = new javax.swing.JMenuItem();
    menuItem_stopGame = new javax.swing.JMenuItem();
    menuItem_deleteGame = new javax.swing.JMenuItem();
    menuItem_restoreGame = new javax.swing.JMenuItem();
    jSeparator3 = new javax.swing.JPopupMenu.Separator();
    menuItem_exit = new javax.swing.JMenuItem();
    toolsMenu = new javax.swing.JMenu();
    menuItem_screenshot = new javax.swing.JMenuItem();
    menuItem_clearGames = new javax.swing.JMenuItem();
    menuItem_checkScreenshots = new javax.swing.JMenuItem();
    menuItem_editGenres = new javax.swing.JMenuItem();
    jSeparator2 = new javax.swing.JPopupMenu.Separator();
    menuItem_options = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Flash games player ");
    setMinimumSize(new java.awt.Dimension(780, 580));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    toolbar.setRollover(true);

    bt_addGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/addGame.png"))); // NOI18N
    bt_addGame.setToolTipText("Add Game");
    bt_addGame.setFocusable(false);
    bt_addGame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_addGame.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_addGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_addGameActionPerformed(evt);
      }
    });
    toolbar.add(bt_addGame);

    bt_editGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/edit.png"))); // NOI18N
    bt_editGame.setToolTipText("Edit game");
    bt_editGame.setEnabled(false);
    bt_editGame.setFocusable(false);
    bt_editGame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_editGame.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_editGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_editGameActionPerformed(evt);
      }
    });
    toolbar.add(bt_editGame);

    bt_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/stop.png"))); // NOI18N
    bt_stop.setEnabled(false);
    bt_stop.setFocusable(false);
    bt_stop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_stop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_stop.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_stopActionPerformed(evt);
      }
    });
    toolbar.add(bt_stop);

    bt_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/delete.png"))); // NOI18N
    bt_delete.setToolTipText("Delete Game");
    bt_delete.setEnabled(false);
    bt_delete.setFocusable(false);
    bt_delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_delete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_delete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_deleteActionPerformed(evt);
      }
    });
    toolbar.add(bt_delete);

    bt_restore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/restore.png"))); // NOI18N
    bt_restore.setEnabled(false);
    bt_restore.setFocusable(false);
    bt_restore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_restore.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_restore.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_restoreActionPerformed(evt);
      }
    });
    toolbar.add(bt_restore);

    bt_screenshot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/screenshot.png"))); // NOI18N
    bt_screenshot.setToolTipText("Take a screenshot");
    bt_screenshot.setEnabled(false);
    bt_screenshot.setFocusable(false);
    bt_screenshot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_screenshot.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_screenshot.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_screenshotActionPerformed(evt);
      }
    });
    toolbar.add(bt_screenshot);
    toolbar.add(jSeparator1);

    bt_internet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/internet.png"))); // NOI18N
    bt_internet.setToolTipText("Internet Connection");
    bt_internet.setFocusable(false);
    bt_internet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_internet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_internet.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_internetActionPerformed(evt);
      }
    });
    toolbar.add(bt_internet);

    bt_options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/options.png"))); // NOI18N
    bt_options.setToolTipText("Options");
    bt_options.setFocusable(false);
    bt_options.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_options.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_options.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_optionsActionPerformed(evt);
      }
    });
    toolbar.add(bt_options);

    bt_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/exit.png"))); // NOI18N
    bt_exit.setToolTipText("Exit");
    bt_exit.setFocusable(false);
    bt_exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_exit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_exit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_exitActionPerformed(evt);
      }
    });
    toolbar.add(bt_exit);

    getContentPane().add(toolbar, java.awt.BorderLayout.NORTH);

    addInternetGamePanel.setPreferredSize(new java.awt.Dimension(810, 30));

    jLabel4.setText("Add game from Internet :");

    tf_addInternetGame.setPreferredSize(new java.awt.Dimension(360, 20));

    bt_addInternetGame.setText("Add");
    bt_addInternetGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_addInternetGameActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout addInternetGamePanelLayout = new javax.swing.GroupLayout(addInternetGamePanel);
    addInternetGamePanel.setLayout(addInternetGamePanelLayout);
    addInternetGamePanelLayout.setHorizontalGroup(
      addInternetGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(addInternetGamePanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tf_addInternetGame, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(bt_addInternetGame)
        .addContainerGap(352, Short.MAX_VALUE))
    );
    addInternetGamePanelLayout.setVerticalGroup(
      addInternetGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(addInternetGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(tf_addInternetGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(bt_addInternetGame))
    );

    getContentPane().add(addInternetGamePanel, java.awt.BorderLayout.SOUTH);

    splitpane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
    splitpane.setDividerLocation(250);
    splitpane.setMinimumSize(new java.awt.Dimension(700, 500));
    splitpane.setOneTouchExpandable(true);
    splitpane.setPreferredSize(new java.awt.Dimension(789, 544));

    gamesTree.setPreferredSize(new java.awt.Dimension(179, 489));

    combo_sort.setModel(new DefaultComboBoxModel(GamesTree.SORTS));
    combo_sort.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        combo_sortActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
    left.setLayout(leftLayout);
    leftLayout.setHorizontalGroup(
      leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(gamesTree, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
          .addComponent(combo_sort, javax.swing.GroupLayout.Alignment.LEADING, 0, 228, Short.MAX_VALUE))
        .addContainerGap())
    );
    leftLayout.setVerticalGroup(
      leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(leftLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(combo_sort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(gamesTree, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        .addContainerGap())
    );

    splitpane.setLeftComponent(left);

    panelMain.setBackground(new java.awt.Color(255, 255, 255));
    panelMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    panelMain.setPreferredSize(new java.awt.Dimension(563, 458));
    panelMain.setLayout(new javax.swing.BoxLayout(panelMain, javax.swing.BoxLayout.LINE_AXIS));

    panel_header.setBackground(new java.awt.Color(255, 255, 255));
    panel_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
    tf_plays.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    tf_plays.setOpaque(false);

    jLabel3.setText("Password :");

    bt_savePassword.setText("Save");
    bt_savePassword.setEnabled(false);
    bt_savePassword.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_savePasswordActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panel_headerLayout = new javax.swing.GroupLayout(panel_header);
    panel_header.setLayout(panel_headerLayout);
    panel_headerLayout.setHorizontalGroup(
      panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panel_headerLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(label_gameTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(bt_savePassword)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tf_plays, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    panel_headerLayout.setVerticalGroup(
      panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panel_headerLayout.createSequentialGroup()
        .addGroup(panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panel_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(label_gameTitle)
            .addComponent(jLabel1)
            .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel3)
            .addComponent(tf_plays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel2)
            .addComponent(bt_savePassword))
          .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(1, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout rightLayout = new javax.swing.GroupLayout(right);
    right.setLayout(rightLayout);
    rightLayout.setHorizontalGroup(
      rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(panelMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
          .addComponent(panel_header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    rightLayout.setVerticalGroup(
      rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(panel_header, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        .addContainerGap())
    );

    splitpane.setRightComponent(right);

    getContentPane().add(splitpane, java.awt.BorderLayout.CENTER);

    gamesMenu.setText("Games");

    menuItem_addGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
    menuItem_addGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/addGame.png"))); // NOI18N
    menuItem_addGame.setText("Add Game");
    menuItem_addGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_addGameActionPerformed(evt);
      }
    });
    gamesMenu.add(menuItem_addGame);

    menuItem_editGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
    menuItem_editGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/edit.png"))); // NOI18N
    menuItem_editGame.setText("Edit Game");
    menuItem_editGame.setEnabled(false);
    gamesMenu.add(menuItem_editGame);

    menuItem_stopGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/stop.png"))); // NOI18N
    menuItem_stopGame.setText("Unload Game");
    menuItem_stopGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_stopGameActionPerformed(evt);
      }
    });
    gamesMenu.add(menuItem_stopGame);

    menuItem_deleteGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
    menuItem_deleteGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/delete.png"))); // NOI18N
    menuItem_deleteGame.setText("Delete Game");
    menuItem_deleteGame.setEnabled(false);
    menuItem_deleteGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_deleteGameActionPerformed(evt);
      }
    });
    gamesMenu.add(menuItem_deleteGame);

    menuItem_restoreGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
    menuItem_restoreGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/restore.png"))); // NOI18N
    menuItem_restoreGame.setText("Restore Game");
    menuItem_restoreGame.setEnabled(false);
    menuItem_restoreGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_restoreGameActionPerformed(evt);
      }
    });
    gamesMenu.add(menuItem_restoreGame);
    gamesMenu.add(jSeparator3);

    menuItem_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
    menuItem_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/exit.png"))); // NOI18N
    menuItem_exit.setText("Exit");
    menuItem_exit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_exitActionPerformed(evt);
      }
    });
    gamesMenu.add(menuItem_exit);

    menuBar.add(gamesMenu);

    toolsMenu.setText("Tools");

    menuItem_screenshot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/screenshot.png"))); // NOI18N
    menuItem_screenshot.setText("Take Screenshot");
    menuItem_screenshot.setEnabled(false);
    menuItem_screenshot.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_screenshotActionPerformed(evt);
      }
    });
    toolsMenu.add(menuItem_screenshot);

    menuItem_clearGames.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/clear.png"))); // NOI18N
    menuItem_clearGames.setText("Clear Games");
    menuItem_clearGames.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_clearGamesActionPerformed(evt);
      }
    });
    toolsMenu.add(menuItem_clearGames);

    menuItem_checkScreenshots.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/checkScreenshots.png"))); // NOI18N
    menuItem_checkScreenshots.setText("Check Screenshots");
    menuItem_checkScreenshots.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_checkScreenshotsActionPerformed(evt);
      }
    });
    toolsMenu.add(menuItem_checkScreenshots);

    menuItem_editGenres.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
    menuItem_editGenres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/editGenres.png"))); // NOI18N
    menuItem_editGenres.setText("Edit Genres");
    menuItem_editGenres.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_editGenresActionPerformed(evt);
      }
    });
    toolsMenu.add(menuItem_editGenres);
    toolsMenu.add(jSeparator2);

    menuItem_options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/options.png"))); // NOI18N
    menuItem_options.setText("Options");
    menuItem_options.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuItem_optionsActionPerformed(evt);
      }
    });
    toolsMenu.add(menuItem_options);

    menuBar.add(toolsMenu);

    setJMenuBar(menuBar);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void menuItem_addGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_addGameActionPerformed
    GameForm gameform = new GameForm();
  }//GEN-LAST:event_menuItem_addGameActionPerformed

  private void menuItem_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_exitActionPerformed
    if (MyMessages.question("Exit", "Really exit the application?") == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }//GEN-LAST:event_menuItem_exitActionPerformed

  private void combo_sortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_sortActionPerformed
    int sort = combo_sort.getSelectedIndex();
    gamesTree.populateTree(sort);
  }//GEN-LAST:event_combo_sortActionPerformed

  private void menuItem_optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_optionsActionPerformed
    OptionsForm.main(null);
  }//GEN-LAST:event_menuItem_optionsActionPerformed

  private void bt_addGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addGameActionPerformed
    new GameForm();
  }//GEN-LAST:event_bt_addGameActionPerformed

  private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
    gamesTree.deleteGame(gamesTree.getSelectedGame());
  }//GEN-LAST:event_bt_deleteActionPerformed

  private void menuItem_deleteGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_deleteGameActionPerformed
    gamesTree.deleteGame(gamesTree.getSelectedGame());
  }//GEN-LAST:event_menuItem_deleteGameActionPerformed

  private void menuItem_screenshotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_screenshotActionPerformed
    try {
      Game game = gamesTree.getSelectedGame();
      JComponent component = gamePanel.getFlashPlayer();
      Robot robot = new Robot();
      Point point = component.getLocationOnScreen();
      Dimension dim = new Dimension(component.getWidth(), component.getHeight());
      Rectangle captureSize = new Rectangle(point, dim);
      BufferedImage image = robot.createScreenCapture(captureSize);
      Image icon = image.getScaledInstance(Options.SCREENSHOT_WIDTH, Options.SCREENSHOT_HEIGHT, Image.SCALE_SMOOTH);
      File file = new File(Options.USER_DIR + Options.SCREENSHOT_DIR
          + game.getId() + ".png");
      BufferedImage bimg = new BufferedImage(Options.SCREENSHOT_WIDTH, Options.SCREENSHOT_HEIGHT, BufferedImage.TYPE_INT_ARGB);
      bimg.getGraphics().drawImage(icon, 0, 0, this);
      ImageIO.write(bimg, "png", file);
      game.setScreenshot(1);
    } catch (AWTException ex) {
      logger.log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      logger.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_menuItem_screenshotActionPerformed

  private void bt_screenshotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_screenshotActionPerformed
    menuItem_screenshotActionPerformed(evt);
  }//GEN-LAST:event_bt_screenshotActionPerformed

  private void bt_optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_optionsActionPerformed
    menuItem_optionsActionPerformed(evt);
  }//GEN-LAST:event_bt_optionsActionPerformed

  private void bt_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exitActionPerformed
    menuItem_exitActionPerformed(evt);
  }//GEN-LAST:event_bt_exitActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    menuItem_exitActionPerformed(null);

  }//GEN-LAST:event_formWindowClosing

  private void bt_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_stopActionPerformed
    MyEvent event = new MyEvent(this, MyEventHandler.UNLOAD_GAME);
    evClass.fireMyEvent(event);
  }//GEN-LAST:event_bt_stopActionPerformed

  private void bt_savePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_savePasswordActionPerformed
    if (gamePanel.getGame() != null) {
      Game game = gamePanel.getGame();
      if (game.setPassword(tf_password.getText().trim())) {
        tf_password.setForeground(Color.green);
        tf_password.setFont(FlashGamesPlayer.tf_password.getFont().deriveFont(Font.BOLD));
      } else {
        tf_password.setForeground(Color.red);
        tf_password.setFont(FlashGamesPlayer.tf_password.getFont().deriveFont(Font.BOLD));
      }
    }
  }//GEN-LAST:event_bt_savePasswordActionPerformed

  private void bt_restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_restoreActionPerformed
    gamesTree.restoreGame();
  }//GEN-LAST:event_bt_restoreActionPerformed

  private void bt_internetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_internetActionPerformed
    isInternet = MyFunctions.checkInternetConnection("http://www.google.com");
    MyEvent event = new MyEvent(this, MyEventHandler.INTERNET_CONNECTION);
    evClass.fireMyEvent(event);
  }//GEN-LAST:event_bt_internetActionPerformed

  private void bt_addInternetGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addInternetGameActionPerformed
    ValidationGroup group = new ValidationGroup();
    group.addComponent(tf_addInternetGame);
    if (!group.validate()) {
      group.errorMessage(true);
    } else {
      AddInternetGame add = new AddInternetGame(tf_addInternetGame.getText().trim());
      add.find();
    }
  }//GEN-LAST:event_bt_addInternetGameActionPerformed

  private void bt_editGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editGameActionPerformed
    new GameForm(gamePanel.getGame(), null);
  }//GEN-LAST:event_bt_editGameActionPerformed

  private void menuItem_clearGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_clearGamesActionPerformed
    MyEvent event = new MyEvent(this, MyEventHandler.CLEAR_GAMES);
    evClass.fireMyEvent(event);
  }//GEN-LAST:event_menuItem_clearGamesActionPerformed

  private void menuItem_checkScreenshotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_checkScreenshotsActionPerformed
    MyEvent event = new MyEvent(this, MyEventHandler.CHECK_SCREENSHOTS);
    evClass.fireMyEvent(event);
  }//GEN-LAST:event_menuItem_checkScreenshotsActionPerformed

  private void menuItem_stopGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_stopGameActionPerformed
    MyEvent event = new MyEvent(this, MyEventHandler.UNLOAD_GAME);
    evClass.fireMyEvent(event);
  }//GEN-LAST:event_menuItem_stopGameActionPerformed

  private void menuItem_restoreGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_restoreGameActionPerformed
    gamesTree.restoreGame();
  }//GEN-LAST:event_menuItem_restoreGameActionPerformed

  private void menuItem_editGenresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_editGenresActionPerformed
    new GenresForm().main(null);
  }//GEN-LAST:event_menuItem_editGenresActionPerformed

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
  public static javax.swing.JPanel addInternetGamePanel;
  public static javax.swing.JButton bt_addGame;
  public static javax.swing.JButton bt_addInternetGame;
  public static javax.swing.JButton bt_delete;
  public static javax.swing.JButton bt_editGame;
  public static javax.swing.JButton bt_exit;
  public static javax.swing.JButton bt_internet;
  public static javax.swing.JButton bt_options;
  public static javax.swing.JButton bt_restore;
  public static javax.swing.JButton bt_savePassword;
  public static javax.swing.JButton bt_screenshot;
  public static javax.swing.JButton bt_stop;
  public static javax.swing.JComboBox combo_sort;
  public static javax.swing.JMenu gamesMenu;
  public static com.googlecode.flashgamesplayer.games.tree.GamesTree gamesTree;
  public static javax.swing.JLabel jLabel1;
  public static javax.swing.JLabel jLabel2;
  public static javax.swing.JLabel jLabel3;
  public static javax.swing.JLabel jLabel4;
  public static javax.swing.JToolBar.Separator jSeparator1;
  public static javax.swing.JPopupMenu.Separator jSeparator2;
  public static javax.swing.JPopupMenu.Separator jSeparator3;
  public static javax.swing.JLabel label_gameTitle;
  public static javax.swing.JPanel left;
  public static javax.swing.JMenuBar menuBar;
  public static javax.swing.JMenuItem menuItem_addGame;
  public static javax.swing.JMenuItem menuItem_checkScreenshots;
  public static javax.swing.JMenuItem menuItem_clearGames;
  public static javax.swing.JMenuItem menuItem_deleteGame;
  public static javax.swing.JMenuItem menuItem_editGame;
  public static javax.swing.JMenuItem menuItem_editGenres;
  public static javax.swing.JMenuItem menuItem_exit;
  public static javax.swing.JMenuItem menuItem_options;
  public static javax.swing.JMenuItem menuItem_restoreGame;
  public static javax.swing.JMenuItem menuItem_screenshot;
  public static javax.swing.JMenuItem menuItem_stopGame;
  public static javax.swing.JPanel panelMain;
  public static javax.swing.JPanel panel_header;
  public static com.googlecode.starrating.StarRating rating;
  public static javax.swing.JPanel right;
  public static javax.swing.JSplitPane splitpane;
  public static com.googlecode.svalidators.formcomponents.STextField tf_addInternetGame;
  public static javax.swing.JTextField tf_password;
  public static javax.swing.JTextField tf_plays;
  public static javax.swing.JToolBar toolbar;
  public static javax.swing.JMenu toolsMenu;
  // End of variables declaration//GEN-END:variables

  private void createDatabase() {
    database = new Database();
  }

  private void createLogger() {
    logger = GamesLogger.createHtmlLogger("FLASHGAMESPLAYER", Options.USER_DIR + "FlashGamesPlayer", 262144, true, 1);
    logger.setLevel(Level.ALL);
  }

  public static void setOptions(HashMap<String, Object> op) {
    options = op;
    MyFunctions.checkInternetConnection("http://www.google.com");

    gamesTree.tree.setRowHeight((Integer) options.get(Options.TREE_ROW_HEIGHT) + 8);
    gamesTree.tree.revalidate();
    gamesTree.tree.repaint();
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
