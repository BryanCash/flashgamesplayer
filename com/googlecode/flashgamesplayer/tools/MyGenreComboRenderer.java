/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.tools;

import com.googlecode.flashgamesplayer.database.Options;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class MyGenreComboRenderer extends JLabel implements ListCellRenderer {

  public MyGenreComboRenderer() {
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
    setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
    setText(value.toString());
    return this;
  }
}
