/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.flashgamesplayer.genres;

import com.googlecode.flashgamesplayer.database.Options;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class GenreRenderer extends DefaultTableCellRenderer{
  private static final long serialVersionUID = 83838982192L;

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    this.setText(value.toString());
    File sc = new File(Options.USER_DIR + Options.CATEGORIES_DIR + value.toString().replaceAll(" ", "_")+".png");
    if(sc.exists()){
    ImageIcon sortIcon =new ImageIcon(new ImageIcon(sc.getAbsolutePath()).getImage().getScaledInstance(
          22, 22, Image.SCALE_SMOOTH));
    setIcon(sortIcon);
    } else{
      ImageIcon def = new ImageIcon(getClass().getResource(
        "/com/googlecode/flashgamesplayer/images/gameLeaf.png"));
      setIcon(def);
    }
    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    return this;
  }
}
