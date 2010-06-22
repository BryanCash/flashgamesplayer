/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.games.tree;

import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Game;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
      Game game = (Game)obj;
      leafIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeaf.png"));
      if(!FlashGamesPlayer.isInternet  && game.isInternet()==Game.INTERNET){
      leafIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeafDisabled.png"));
      setFont(getFont().deriveFont(Font.ITALIC));
      setForeground(Color.GRAY);
      }
    } else if (obj instanceof String) {
    }
    return this;
  }
}
