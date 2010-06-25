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
import java.awt.image.BufferedImage;
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

  private static long serialVersionUID = 1242536474L;
  private Integer treeHeight = 64;

  public GamesCellRenderer() {
    setOpaque(false);
    setBackgroundSelectionColor(Color.LIGHT_GRAY);
    setBackgroundNonSelectionColor(Color.WHITE);
    setBorderSelectionColor(Color.WHITE);
    treeHeight = (Integer) FlashGamesPlayer.options.get(Options.TREE_ROW_HEIGHT);
    openIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/open.png")).getImage().getScaledInstance(treeHeight, treeHeight, Image.SCALE_SMOOTH));
    closedIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/closed.png")).getImage().getScaledInstance(treeHeight, treeHeight, Image.SCALE_SMOOTH));
  }

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
    Object obj = node.getUserObject();
    ImageIcon disabled;
    BufferedImage buff;
    if (selected) {
      setForeground(Color.BLACK);
    }
    ImageIcon catIcon = null;
    if (obj instanceof Game) {
      Game game = (Game) obj;
      ImageIcon gameIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeaf.png"));
      File sc = new File(Options.USER_DIR + Options.SCREENSHOT_DIR + game.getId() + ".png");
      if (sc.isFile()) {

        gameIcon = new ImageIcon(new ImageIcon(sc.getAbsolutePath()).getImage().getScaledInstance(
            treeHeight, treeHeight, Image.SCALE_SMOOTH));

        // setText("");
      }
      if (!FlashGamesPlayer.isInternet && game.isInternet() == Game.INTERNET) {

        disabled = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeafDisabled.png"));
        buff = new BufferedImage(treeHeight, treeHeight, BufferedImage.TYPE_INT_ARGB);
        buff.getGraphics().drawImage(gameIcon.getImage(), 0, 0, this);
        buff.getGraphics().drawImage(disabled.getImage(), treeHeight - 22, treeHeight - 33, this);
        gameIcon = new ImageIcon(buff);
        setFont(getFont().deriveFont(Font.ITALIC));
        setForeground(Color.GRAY);
      } else {
        setToolTipText(game.getTitle());
      }
      setIcon(gameIcon);
      setFont(getFont().deriveFont(12F));
    } else if (obj instanceof String) {
      File catFile = new File(Options.USER_DIR + Options.SCREENSHOT_DIR + obj + ".png");
      if (catFile.isFile()) {
        catIcon = new ImageIcon(new ImageIcon(catFile.getAbsolutePath()).getImage().getScaledInstance(
            treeHeight, treeHeight, Image.SCALE_SMOOTH));
        ImageIcon custom;
        if (expanded) {
          custom = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/expanded.png"));
        } else {
          custom = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/collapsed.png"));
        }
        buff = new BufferedImage(treeHeight, treeHeight, BufferedImage.TYPE_INT_ARGB);
        buff.getGraphics().drawImage(catIcon.getImage(), 0, 0, this);
        buff.getGraphics().drawImage(custom.getImage(), treeHeight/2,treeHeight/2,treeHeight - treeHeight/2,treeHeight - treeHeight/2, this);
        catIcon = new ImageIcon(buff);
        setIcon(catIcon);
      }

      setFont(getFont().deriveFont(20F));
    }
    setPreferredSize(new Dimension(200, tree.getRowHeight()));
    if (!(Boolean) FlashGamesPlayer.options.get(Options.DISPLAY_GAME_TITLE)) {
      setText("");
    }
    return this;
  }
}
