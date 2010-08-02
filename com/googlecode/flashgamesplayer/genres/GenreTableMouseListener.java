/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.genres;

import com.googlecode.flashgamesplayer.database.Genre;
import com.googlecode.flashgamesplayer.database.Options;
import com.googlecode.flashgamesplayer.tools.MyMessages;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ssoldatos
 */
public class GenreTableMouseListener extends MouseAdapter {
  private final GenresForm gForm;

  GenreTableMouseListener(GenresForm gForm) {
    this.gForm = gForm;

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      JTable table = (JTable) e.getSource();
      Point point = e.getPoint();
      int row = table.rowAtPoint(point);
      int col = table.columnAtPoint(point);
      if (col == 1) {
        if (table.getValueAt(row, col) instanceof Genre) {
          Genre genre = (Genre) table.getValueAt(row, col);
          int an = MyMessages.question("Delete Genre", "Do you really want to delete the genre : " + genre + "?");
          if (an == JOptionPane.YES_OPTION) {
            if (Genre.hasGames(genre.getId())) {
              MyMessages.message("Delete Genre", "You can't delete a non empty genre");
            } else {
              if (genre.delete() != -1) {
                File sc = new File(Options.USER_DIR + Options.CATEGORIES_DIR + genre.toString().replaceAll(" ", "_") + ".png");
                if (sc.exists()) {
                  //sc.delete();
                }
                gForm.populateTable();
              } else {
                MyMessages.error("Delete Genre", "Could not delete the genre");
              }
            }
          }
        }
      }
    }
  }
}
