/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.games.tree;

import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Game;
import com.googlecode.flashgamesplayer.database.Options;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class GamesCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {

  private static final long serialVersionUID = 1242536474L;

  public GamesCellRenderer() {
    openIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/open.png"));
    closedIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/closed.png"));
  }

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
    Object obj = node.getUserObject();

    if (obj instanceof Game) {
      Game game = (Game) obj;
      ImageIcon gameIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeaf.png"));
      File sc = new File(Options.USER_DIR + Options.SCREENSHOT_DIR + game.getId() + ".png");
      if (sc.isFile()) {
        int height = (Integer) FlashGamesPlayer.options.get(Options.TREE_ROW_HEIGHT);
        gameIcon = new ImageIcon(new ImageIcon(sc.getAbsolutePath()).getImage().getScaledInstance(
                height, height, Image.SCALE_SMOOTH));

        setText("");
      }
      if (!FlashGamesPlayer.isInternet && game.isInternet() == Game.INTERNET) {
        gameIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeafDisabled.png"));
        setFont(getFont().deriveFont(Font.ITALIC));
        setForeground(Color.GRAY);
      } else {
        setToolTipText(game.getTitle());
      }
      setIcon(gameIcon);
    } else if (obj instanceof String) {
    }
    setPreferredSize(new Dimension(200, tree.getRowHeight()));

    return this;
  }
}
