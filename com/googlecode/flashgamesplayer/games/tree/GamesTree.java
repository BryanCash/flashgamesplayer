/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Tree.java
 *
 * Created on 21 Ιουν 2010, 10:56:02 πμ
 */
package com.googlecode.flashgamesplayer.games.tree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Database;
import com.googlecode.flashgamesplayer.database.Game;
import com.googlecode.flashgamesplayer.database.Genre;
import com.googlecode.flashgamesplayer.games.GameForm;
import com.googlecode.flashgamesplayer.games.GamesChangeListener;
import com.googlecode.flashgamesplayer.tools.MyMessages;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

/**
 *
 * @author ssoldatos
 */
public class GamesTree extends javax.swing.JPanel {

  public static final int GENRE = 0;
  public static final int PLAYED = 1;
  public static final int RATE = 2;
  public static final int INTERNET = 3;
  public static final int DATE = 4;
  public static final int DELETED = 5;
  public static final String[] SORTS = {"Genre", "Played", "Rate", "Internet", "Date Added", "Deleted"};
  private static final long serialVersionUID = 345345636456L;
  DefaultTreeModel model = new GamesTreeModel(null);
  private boolean isSelected;
  private int sort = GENRE;
  private Game selectedGame;
  private Genre selectedGenre;
  private TreePath selectedPath;

  /** Creates new form Tree */
  public GamesTree() {
    initComponents();
    addPropertyChangeListener(new GamesChangeListener());
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

    popup = new javax.swing.JPopupMenu();
    addGame = new javax.swing.JMenuItem();
    delete = new javax.swing.JMenuItem();
    scrollpane = new javax.swing.JScrollPane();
    tree = new javax.swing.JTree();

    popup.setComponentPopupMenu(popup);
    popup.setInvoker(scrollpane);

    addGame.setText("Add Game");
    addGame.setToolTipText("Add a new Game");
    addGame.setPreferredSize(new java.awt.Dimension(140, 22));
    addGame.setRolloverEnabled(true);
    addGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addGameActionPerformed(evt);
      }
    });
    popup.add(addGame);

    delete.setText("Delete");
    delete.setToolTipText("Delete");
    delete.setPreferredSize(new java.awt.Dimension(140, 22));
    delete.setRolloverEnabled(true);
    delete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteActionPerformed(evt);
      }
    });
    popup.add(delete);

    scrollpane.setPreferredSize(new java.awt.Dimension(120, 522));

    tree.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
    tree.setModel(model);
    tree.setMaximumSize(new java.awt.Dimension(100, 800));
    tree.setMinimumSize(new java.awt.Dimension(80, 200));
    tree.setRowHeight(40);
    tree.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(java.awt.event.MouseEvent evt) {
        treeMouseReleased(evt);
      }
    });
    tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
      public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
        treeValueChanged(evt);
      }
    });
    tree.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        treeKeyReleased(evt);
      }
    });
    scrollpane.setViewportView(tree);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(scrollpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(scrollpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents

  private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
  }//GEN-LAST:event_treeValueChanged

  private void playGame() {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    setSelectedGame(null);
    if (node != null) {
      if (node.isLeaf()) {
        if (node.getUserObject() instanceof Game) {
          setSelectedGame((Game) node.getUserObject());
          if (!FlashGamesPlayer.isInternet && getSelectedGame().isInternet() == Game.INTERNET) {
            MyMessages.error("Play Game", "This games requires internet connection which is not established");
            setSelectedGame(null);
          }
          if (getSelectedGame().getDeleted() == 1) {
            MyMessages.error("Play Game", "This games is deleted. Undelete it first.");
          }
          firePropertyChange(GamesChangeListener.GAME_SELECTED, FlashGamesPlayer.gamePanel.getGame(), getSelectedGame());
        } else if (node.getUserObject() instanceof Genre) {
          selectedGenre = (Genre) node.getUserObject();
        }
      } else {
        if (node.getUserObject() instanceof Genre) {
          selectedGenre = (Genre) node.getUserObject();
        }
      }
    }
  }

  private void treeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseReleased
    DefaultMutableTreeNode node;
    if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
      Point p = evt.getPoint();
      selectedPath = tree.getClosestPathForLocation(p.x, p.y);
      if (tree.getPathBounds(selectedPath).contains(p)) {
        tree.setSelectionPath(selectedPath);
        playGame();
      }
    }
  }//GEN-LAST:event_treeMouseReleased

  private void addGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGameActionPerformed
    new GameForm();
  }//GEN-LAST:event_addGameActionPerformed

  private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
    TreePath path = tree.getSelectionPath();
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
    Object obj = node.getUserObject();
    if (obj instanceof Game) {
      Game game = (Game) obj;
    } else if (obj instanceof Genre) {
      Genre genre = (Genre) obj;
    }
  }//GEN-LAST:event_deleteActionPerformed

  public void deleteGame(Game game) {
    if (MyMessages.question("Delete Game", "Really delete the game : " + game.getTitle()) == JOptionPane.OK_OPTION) {
      if (game.setDeleted(Game.DELETED)) {
        firePropertyChange(GamesChangeListener.GAME_DELETED, game, null);
      } else {
        MyMessages.error("Error", "Could not delete the game");
      }
    }
  }

  private void treeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_treeKeyReleased
    TreePath path = tree.getSelectionPath();
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
    Object obj = node.getUserObject();
    if (obj instanceof Game) {
      Game game = (Game) obj;
      if (evt.getKeyCode() == KeyEvent.VK_F2) {
        new GameForm(game, null);
      } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
        deleteGame(game);
      }
    }
  }//GEN-LAST:event_treeKeyReleased
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem addGame;
  private javax.swing.JMenuItem delete;
  private javax.swing.JPopupMenu popup;
  private javax.swing.JScrollPane scrollpane;
  public javax.swing.JTree tree;
  // End of variables declaration//GEN-END:variables

  public void populateTree(int sort) {
    setSort(sort);
    ArrayList<GameNode> list = new ArrayList<GameNode>();
    String groupAndOrder = "";
    switch (sort) {
      case GENRE:
        groupAndOrder = "GROUP BY gen.id, g.id ORDER BY gen.id, g.title";
        break;
      case PLAYED:
        groupAndOrder = "ORDER BY g.played DESC, g.title";
        break;
      case RATE:
        groupAndOrder = "GROUP BY g.rate, g.id ORDER BY g.rate DESC, g.title";
        break;
      case INTERNET:
        groupAndOrder = "GROUP BY g.internet, g.id ORDER BY g.internet DESC, g.title";
        break;
      case DATE:
        groupAndOrder = "ORDER BY g.id DESC, g.title";
        break;
      case DELETED:
        groupAndOrder = "GROUP BY g.deleted,g.id ORDER BY g.deleted, g.title";
        break;
    }
    String sql = "SELECT g.id AS id FROM games  g "
        + "INNER JOIN genres gen ON g.genre_id = gen.id "
        + (sort != DELETED ? "WHERE g.deleted = " + Game.NOT_DELETED + " " : "")
        + groupAndOrder;
    try {
      ResultSet rs = new Database().getStmt().executeQuery(sql);
      while (rs.next()) {
        Game game = Game.getGameById(rs.getInt("id"));
        Category category = null;
        switch (sort) {
          case GENRE:
            category = new Category(GENRE, Genre.getGenreById(game.getGenre_id()).getGenre());
            break;
          case PLAYED:
            category = new Category(PLAYED, game.getPlayed());
            break;
          case RATE:
            category = new Category(RATE, game.getRate());
            break;
          case INTERNET:
            category = new Category(INTERNET, game.isInternet() == Game.INTERNET ? "Internet" : "No Internet");
            break;
          case DATE:
            category = new Category(DATE, "Date");
            break;
          case DELETED:
            category = new Category(DELETED, game.getDeleted() == Game.DELETED ? "Deleted" : "Not Deleted");
            break;
        }

        list.add(new GameNode(category, game));
      }
      DefaultMutableTreeNode root = createTree(list);
      model = new DefaultTreeModel(root);
      tree.setModel(model);
      int row = 0;
      while (row < tree.getRowCount()) {
        tree.expandRow(row);
        row++;
      }
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
    }
  }

  private DefaultMutableTreeNode createTree(ArrayList<GameNode> list) {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Category(GENRE, "Games"));
    Object prevNodeCategory = "";
    DefaultMutableTreeNode curNode = null;
    for (Iterator<GameNode> it = list.iterator(); it.hasNext();) {
      GameNode gameNode = it.next();
      if (!gameNode.category.value.equals(prevNodeCategory)) {
        curNode = new DefaultMutableTreeNode(gameNode.category);
        root.add(curNode);
        curNode.add(new DefaultMutableTreeNode(gameNode.game));
        prevNodeCategory = gameNode.category.value;
      } else {
        curNode.add(new DefaultMutableTreeNode(gameNode.game));
      }
    }
    return root;

  }

  public void populateTree() {
    populateTree(getSort());
  }

  /**
   * @return the sort
   */
  public int getSort() {
    return sort;
  }

  /**
   * @param sort the sort to set
   */
  public void setSort(int sort) {
    this.sort = sort;
  }

  /**
   * @return the selectedGame
   */
  public Game getSelectedGame() {
    return selectedGame;
  }

  /**
   * @param selectedGame the selectedGame to set
   */
  public void setSelectedGame(Game selectedGame) {
    FlashGamesPlayer.bt_delete.setEnabled(selectedGame != null);
    FlashGamesPlayer.bt_screenshot.setEnabled(selectedGame != null);
    FlashGamesPlayer.menuItem_deleteGame.setEnabled(selectedGame != null);
    FlashGamesPlayer.menuItem_screenshot.setEnabled(selectedGame != null);
    this.selectedGame = selectedGame;
  }

  public void restoreGame() {
    if (FlashGamesPlayer.gamePanel.getGame() != null) {
      Game game = FlashGamesPlayer.gamePanel.getGame();
      if (game.setDeleted(Game.NOT_DELETED)) {
        firePropertyChange(GamesChangeListener.GAME_RESTORED, null, FlashGamesPlayer.gamePanel.getGame());
      } else {
        MyMessages.error("Game restore", "Could not restore the game");
      }
    }
  }


class GameNode {

  private Category category;
  private Game game;

  public GameNode(Category category, Game g) {
    this.category = category;
    this.game = g;
  }
}

class Category {

  private int type;
  private Object value;

  public Category(int type, Object value) {
    this.type = type;
    this.value = value;

  }

  @Override
  public String toString() {
    return value.toString();
  }

  /**
   * @return the type
   */
  public int getType() {
    return type;
  }

  /**
   * @return the value
   */
  public Object getValue() {
    return value;
  }
}

}
