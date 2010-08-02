/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.genres;

import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Genre;
import com.googlecode.flashgamesplayer.database.Options;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author ssoldatos
 */
public class GenreTableModelListener implements TableModelListener {

  private final GenresForm gf;

  GenreTableModelListener(GenresForm gf) {
    this.gf = gf;

  }

  @Override
  public void tableChanged(TableModelEvent e) {
    try {
      if(e.getType()!=TableModelEvent.UPDATE){
        return;
      }
      int row = e.getFirstRow();
      int col = e.getColumn();
      GenreTableModel model = (GenreTableModel) e.getSource();
      String val = (String) model.getValueAt(row, col);
      Genre genre = (Genre) model.getValueAt(row, col + 1);
      String oldName = genre.getGenre();
      genre.setGenre(val);
      if (genre.save() > -1) {
        File sc = new File(Options.USER_DIR + Options.CATEGORIES_DIR + oldName.replaceAll(" ", "_") + ".png");
        if (sc.exists()) {
          File dest = new File(Options.USER_DIR + Options.CATEGORIES_DIR + genre.getGenre().replaceAll(" ", "_") + ".png");
          sc.renameTo(dest);
        }
      }
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
    }
  }


}
