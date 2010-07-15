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

  public static final int DEFAULT_CATEGORY_HEIGHT = 64;
  public static final int DEFAULT_ROW_HEIGHT = 24;
  public static final int GAMES_FONT_SIZE = 12;
  public static final int CATEGORY_FONT_SIZE = 16;
  private static long serialVersionUID = 1242536474L;
  /** The games screenshots  height**/
  private Integer screenshotHeight = 64;
  /** The games screenshots  width**/
  private Integer screenshotWidth = 85;
  /** The category image  height**/
  private Integer categoryHeight = 64;
  /** Show screenshot or not**/
  private Boolean showScreenshot;
  /** Show title or not**/
  private Boolean showTitle;
  /** Default game Icon **/
  private Image gameIcon;
  private int rowHeight;

  public GamesCellRenderer() {
    setOpaque(false);
    setBackgroundSelectionColor(Color.LIGHT_GRAY);
    setBackgroundNonSelectionColor(Color.WHITE);
    setBorderSelectionColor(Color.WHITE);
    gameIcon = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/icon.png")).getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH);

  }

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
    Object obj = node.getUserObject();
    screenshotHeight = (Integer) FlashGamesPlayer.options.get(Options.TREE_ROW_HEIGHT);
    screenshotWidth = (screenshotHeight * 4) / 3;
    showScreenshot = (Boolean) FlashGamesPlayer.options.get(Options.DISPLAY_GAME_SCREENSHOT);
    showTitle = (Boolean) FlashGamesPlayer.options.get(Options.DISPLAY_GAME_TITLE);
    categoryHeight = !showScreenshot ? DEFAULT_CATEGORY_HEIGHT : screenshotHeight < categoryHeight ? screenshotHeight : DEFAULT_CATEGORY_HEIGHT;
    tree.setRowHeight(0);
    //COMMON
    rowHeight = getRowHeight(obj);

    // IF CATEGORY
    if (obj instanceof GamesTree.Category) {
      Category cat = (Category) obj;
      // tree.setRowHeight(treeRowHeight);
      if (screenshotHeight < categoryHeight) {
        categoryHeight = screenshotHeight;
      }
      setPreferredSize(new Dimension(180, rowHeight));

      setIcon(getCategoryIcon(cat, expanded));
    } // IF GAME
    else if (obj instanceof Game) {
      // tree.setRowHeight(treeRowHeight);
      Game game = (Game) obj;
      if (selected) {
        setBorder(BorderFactory.createLineBorder(Color.RED, 2));
      } else {
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
      }
      setPreferredSize(new Dimension(screenshotWidth + (showTitle ? 180 : 0), rowHeight + 4));
      if (!showTitle) {
        setText("");
      }

      setIcon(!showScreenshot ? new ImageIcon(gameIcon) : getGameIcon(game, expanded));
    }
    //SET FONT 
    if (obj instanceof Game) {
      Game game = (Game) obj;
      setFont(!FlashGamesPlayer.isInternet && game.isInternet() == Game.INTERNET
          ? getFont().deriveFont(Font.ITALIC, GAMES_FONT_SIZE)
          : getFont().deriveFont(Font.PLAIN, GAMES_FONT_SIZE));
    } else {
      setFont(getFont().deriveFont(Font.BOLD, CATEGORY_FONT_SIZE));
    }
    return this;
  }

  private ImageIcon getGameIcon(Game game, boolean expanded) {
    ImageIcon gameScreenshot = null;
    File sc = new File(Options.USER_DIR + Options.SCREENSHOT_DIR + game.getId() + ".png");
    if (sc.isFile()) {
      gameScreenshot = new ImageIcon(new ImageIcon(sc.getAbsolutePath()).getImage().getScaledInstance(
          screenshotWidth, screenshotHeight, Image.SCALE_SMOOTH));
    } else {
      gameScreenshot = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeaf.png"));
    }
    if (!FlashGamesPlayer.isInternet && game.isInternet() == Game.INTERNET) {
      ImageIcon disabled = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/gameLeafDisabled.png"));
      BufferedImage buff = new BufferedImage(screenshotWidth, screenshotHeight, BufferedImage.TYPE_INT_ARGB);
      buff.getGraphics().drawImage(gameScreenshot.getImage(), 0, 0, this);
      buff.getGraphics().drawImage(disabled.getImage(), screenshotWidth - 22, screenshotHeight - 33, this);
      gameScreenshot = new ImageIcon(buff);
    } else {
      setToolTipText(game.getTitle());
    }
    return gameScreenshot;
  }

  private ImageIcon getCategoryIcon(GamesTree.Category cat, boolean expanded) {
    ImageIcon catIcon = null;
    BufferedImage buff;
    if (cat.getType() == GamesTree.GENRE
        || cat.getType() == GamesTree.DATE
        || cat.getType() == GamesTree.INTERNET
        || cat.getType() == GamesTree.DELETED) {
      File catFile = new File(Options.USER_DIR + Options.SCREENSHOT_DIR + cat.toString() + ".png");
      if (catFile.isFile()) {
        catIcon = new ImageIcon(new ImageIcon(catFile.getAbsolutePath()).getImage().getScaledInstance(
            categoryHeight / 2, categoryHeight / 2, Image.SCALE_SMOOTH));
        ImageIcon custom;
        if (expanded) {
          custom = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/expanded.png"));
        } else {
          custom = new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/collapsed.png"));
        }
        buff = new BufferedImage(categoryHeight / 2, categoryHeight / 2, BufferedImage.TYPE_INT_ARGB);
        buff.getGraphics().drawImage(catIcon.getImage(), 0, 0, this);
        buff.getGraphics().drawImage(custom.getImage(), categoryHeight / 4, categoryHeight / 4, categoryHeight / 4, categoryHeight / 4, this);
        catIcon = new ImageIcon(buff);
      } else {
        openIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/open.png")).getImage().getScaledInstance(categoryHeight / 2, categoryHeight / 2, Image.SCALE_SMOOTH));
        closedIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/closed.png")).getImage().getScaledInstance(categoryHeight / 2, categoryHeight / 2, Image.SCALE_SMOOTH));

      }
      return catIcon;
    } else if (cat.getType() == GamesTree.RATE) {
      catIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/star.png")).getImage().getScaledInstance(categoryHeight / 2, categoryHeight / 2, Image.SCALE_SMOOTH));
      return catIcon;
    } else if (cat.getType() == GamesTree.PLAYED) {
      catIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/play.png")).getImage().getScaledInstance(categoryHeight / 2, categoryHeight / 2, Image.SCALE_SMOOTH));
      return catIcon;
    } else {
      return catIcon;
    }
  }

  private int getRowHeight(Object obj) {
    if (obj instanceof GamesTree.Category) {
      return categoryHeight;
    } else {
      return showScreenshot ? screenshotHeight : DEFAULT_ROW_HEIGHT;
    }

  }
}
