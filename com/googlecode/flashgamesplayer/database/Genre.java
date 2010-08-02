/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.tools.MyDraggable;

/**
 *
 * @author lordovol
 */
public class Genre extends Record {


  private int id;
  private String genre;

  public Genre() {
    super();
  }

  public int save() throws SQLException {
    String sql;
    if (this.getId() != 0) {
      sql = "UPDATE genres SET genre = '" + this.getGenre() + "' WHERE id = " + this.getId();
    } else {
      sql = "INSERT INTO genres (genre) "
              + "VALUES('" + this.getGenre() + "')";
    }
    return queryUpdate(sql);
  }

  public int delete(){
    try {
      String sql = "DELETE FROM genres WHERE id = " + this.getId();
      return queryUpdate(sql);
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return -1;
    }
  }

  public static Genre getGenreById(int id) {
    try {
      String sql = "SELECT * FROM genres WHERE id =" + id;
      ResultSet rs = FlashGamesPlayer.database.getStmt().executeQuery(sql);
      while (rs.next()) {
        Genre g = new Genre();
        g.setId(rs.getInt("id"));
        g.setGenre(rs.getString("genre"));
        return g;
      }
      return null;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return null;
    }
  }

   public static boolean hasGames(int id) {
    try {
      String sql = "SELECT games.id FROM games JOIN genres ON " +
          "games.genre_id = genres.id WHERE genres.id = " + id;
      ResultSet rs = FlashGamesPlayer.database.getStmt().executeQuery(sql);
      if(rs.next()){
        return true;
      }
      return false;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return true;
    }
  }


  public static Vector<Genre> getAll() {
    Vector<Genre> genres = new Vector<Genre>();
    try {
      String sql = "SELECT * FROM genres ORDER BY genre";
      ResultSet rs = FlashGamesPlayer.database.getStmt().executeQuery(sql);
      while (rs.next()) {
        Genre g = new Genre();
        g.setId(rs.getInt("id"));
        g.setGenre(rs.getString("genre"));
        genres.add(g);
      }
      return genres;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.WARNING, "Error while trying to get genres", ex);
      return genres;
    }

  }

  @Override
  public String toString() {
    return getGenre();
  }



  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the genre
   */
  public String getGenre() {
    return genre;
  }

  /**
   * @param genre the genre to set
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }
}
