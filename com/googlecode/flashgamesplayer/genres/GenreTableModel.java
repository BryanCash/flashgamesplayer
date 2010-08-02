/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.flashgamesplayer.genres;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ssoldatos
 */
public class GenreTableModel extends DefaultTableModel{
  private static final long serialVersionUID = 896786756L;

  @Override
  public int getColumnCount() {
    return 2;
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    if(column==0){
      return true;
    }
    return false;
  }




}
