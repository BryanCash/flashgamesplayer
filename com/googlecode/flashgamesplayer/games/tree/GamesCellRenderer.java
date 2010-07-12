/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.games.tree;

import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Game;
import com.googlecode.flashgamesplayer.database.Options;
import com.googlecode.flashgamesplayer.games.tree.GamesTree.Category;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.BorderFactory;
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
  private Integer screenshotHeight = 64;
  private Integer screenshotWidth = 85;
  private Boolean showScreenshot;

  public GamesCellRenderer() {
    setOpaque(false);
    setBackgroundSelectionColor(Color.LIGHT_GRAY);
    setBackgroundNonSelectionColor(Color.WHITE);
    setBorderSelectionColor(Color.WHITE);
    

  }

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    screenshotHeight = (Integer) FlashGamesPlayer.options.get(Options.TREE_ROW_HEIGHT);
    screenshotWidth = (screenshotHeight * 4) / 3;
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
    Object obj = node.getUserObject();
    showScreenshot = (Boolean) FlashGamesPlayer.options.get(Options.DISPLAY_GAME_SCREENSHOT);
    if (!showScreenshot) {
      tree.setRowHeight(16);
      Image gameIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeaf.png")).getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);
      if (obj instanceof Game) {
        setIcon(new ImageIcon(gameIcon));
      }
      return this;
    }

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
            screenshotWidth, screenshotHeight, Image.SCALE_SMOOTH));

        // setText("");
      }
      if (!FlashGamesPlayer.isInternet && game.isInternet() == Game.INTERNET) {

        disabled = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeafDisabled.png"));
        buff = new BufferedImage(screenshotWidth, screenshotHeight, BufferedImage.TYPE_INT_ARGB);
        buff.getGraphics().drawImage(gameIcon.getImage(), 0, 0, this);
        buff.getGraphics().drawImage(disabled.getImage(), screenshotWidth - 22, screenshotHeight - 33, this);
        gameIcon = new ImageIcon(buff);
        setFont(getFont().deriveFont(Font.ITALIC));
        setForeground(Color.GRAY);
      } else {
        setToolTipText(game.getTitle());
      }
      setIcon(gameIcon);
      setFont(getFont().deriveFont(12F));
    } else if (obj instanceof GamesTree.Category) {
      Category cat = (Category) obj;
      if (cat.getType() == GamesTree.GENRE
          || cat.getType() == GamesTree.DATE
          || cat.getType() == GamesTree.INTERNET
          || cat.getType() == GamesTree.DELETED) {
        File catFile = new File(Options.USER_DIR + Options.SCREENSHOT_DIR + obj + ".png");
        if (catFile.isFile()) {
          catIcon = new ImageIcon(new ImageIcon(catFile.getAbsolutePath()).getImage().getScaledInstance(
              screenshotWidth / 2, screenshotHeight / 2, Image.SCALE_SMOOTH));
          ImageIcon custom;
          if (expanded) {
            custom = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/expanded.png"));
          } else {
            custom = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/collapsed.png"));
          }
          buff = new BufferedImage(screenshotWidth / 2, screenshotHeight / 2, BufferedImage.TYPE_INT_ARGB);
          buff.getGraphics().drawImage(catIcon.getImage(), 0, 0, this);
          buff.getGraphics().drawImage(custom.getImage(), screenshotWidth / 4, screenshotHeight / 4, screenshotHeight / 4, screenshotHeight / 4, this);
          catIcon = new ImageIcon(buff);

        } else {
          openIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/open.png")).getImage().getScaledInstance(screenshotWidth / 2, screenshotHeight / 2, Image.SCALE_SMOOTH));
          closedIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/closed.png")).getImage().getScaledInstance(screenshotWidth / 2, screenshotHeight / 2, Image.SCALE_SMOOTH));

        }
        setIcon(catIcon);
        setFont(getFont().deriveFont(20F));
      } else if (cat.getType() == GamesTree.RATE) {
        catIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/star.png")).getImage().getScaledInstance(screenshotWidth / 2, screenshotHeight / 2, Image.SCALE_SMOOTH));
        setIcon(catIcon);
        setFont(getFont().deriveFont(20F));
      } else if (cat.getType() == GamesTree.PLAYED) {
        catIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/play.png")).getImage().getScaledInstance(screenshotWidth / 2, screenshotHeight / 2, Image.SCALE_SMOOTH));
        setIcon(catIcon);
        setFont(getFont().deriveFont(20F));
      }
    }
    setPreferredSize(new Dimension(screenshotWidth * 3, tree.getRowHeight()));
    if (!(Boolean) FlashGamesPlayer.options.get(Options.DISPLAY_GAME_TITLE)
        && obj instanceof Game) {
      setText("");
    }
    return this;
  }
}
