/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.tools;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class MySortComboRenderer extends JLabel implements ListCellRenderer {

  public MySortComboRenderer() {
    setOpaque(true);
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    if (isSelected) {
      setBackground(Color.GRAY);
      setForeground(Color.WHITE);
    } else {
      setBackground(Color.WHITE);
      setForeground(Color.BLACK);
    }
    ImageIcon sortIcon = new ImageIcon(getClass().getResource(
        "/com/googlecode/flashgamesplayer/images/combo_"+value.toString().replaceAll(" ", "_")+".png"));
    setIcon(sortIcon);
    setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
    setText(value.toString());
    return this;
  }
}
