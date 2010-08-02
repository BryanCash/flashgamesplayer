/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.flashgamesplayer.genres;

import java.awt.Component;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class EditGenreRenderer extends DefaultTableCellRenderer{
  private static final long serialVersionUID = 83838982192L;

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    this.setText("");
    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
  this.setIcon(new ImageIcon(getClass().getResource("/com/googlecode/flashgamesplayer/images/delete.png")));
    return this;
  }
}
