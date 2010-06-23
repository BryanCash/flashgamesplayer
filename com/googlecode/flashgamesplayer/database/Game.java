/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.tools.MyMessages;
import java.io.File;

/**
 *
 * @author lordovol
 */
public class Game extends Record {

  private int id;
  private int genre_id;
  private String title;
  private String filename;
  private int played;
  private double rate = 0.0;
  private int internet;

  public static int NO_INTERNET = 0;
  public static int INTERNET = 1;

  public Game() {
    super();
  }

  public int save() throws SQLException {
    String sql;
    if (this.getId() != 0) {
      sql = "UPDATE games SET "
          + "genre_id = " + this.getGenre_id()
          + ", title = '" + this.getTitle()
          + "', filename = '" + this.getFilename()
          + "', internet = " + this.isInternet()
          + " WHERE id = " + this.getId();
    } else {
      sql = "INSERT INTO games (genre_id, title, filename, internet ) "
          + "VALUES(" + this.getGenre_id() + ", '" + this.getTitle() + "', '"
          + this.getFilename() + "'," + this.isInternet() + " )";
    }
    return queryUpdate(sql);
  }

  public static Game getGameById(int id) {
    try {
      String sql = "SELECT * FROM games WHERE id = " + id;
      Game game = new Game();
      ResultSet rs = query(sql);
      if (rs.next()) {
        game.setFilename(rs.getString("filename"));
        game.setGenre_id(rs.getInt("genre_id"));
        game.setId(rs.getInt("id"));
        game.setTitle(rs.getString("title"));
        game.setPlayed(rs.getInt("played"));
        game.setRate(rs.getDouble("rate"));
        game.setInternet(rs.getInt("internet"));
        return game;
      }
      return null;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return null;
    }
  }

  public static int updatePlayed(int id) throws SQLException {
    String sql = "UPDATE games SET played = played + 1 WHERE id = " + id;
    return queryUpdate(sql);
  }

  public static int updateRate(int id, double rate) throws SQLException {
    String sql = "UPDATE games SET rate = " + rate + " WHERE id = " + id;
    return queryUpdate(sql);
  }

   public boolean delete() {
    try {
      String sql = "DELETE FROM games WHERE id =" + this.getId();
      queryUpdate(sql);
      //new File(Options.USER_DIR + Options.GAMES_DIR + this.getFilename()).delete();
      return true;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return false;
    }
    
  }

  public static Game getFirstGame(int genre) {
    String sql;
    if (genre == 0) {
      sql = "SELECT id FROM games";
    } else {
      sql = "SELECT id FROM games WHERE genre_id = " + genre;
    }
    try {
      ResultSet rs = query(sql);
      if (rs.next()) {
        int id = rs.getInt("id");
        return getGameById(id);
      }
      return null;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return null;
    }
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
   * @return the genre_id
   */
  public int getGenre_id() {
    return genre_id;
  }

  /**
   * @param genre_id the genre_id to set
   */
  public void setGenre_id(int genre_id) {
    this.genre_id = genre_id;
  }

  /**
   * @return the name
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the name to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the filename
   */
  public String getFilename() {
    return filename;
  }

  /**
   * @param filename the filename to set
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  @Override
  public String toString() {
    return getTitle();
  }

  /**
   * @return the played
   */
  public int getPlayed() {
    return played;
  }

  /**
   * @param played the played to set
   */
  public void setPlayed(int played) {
    this.played = played;
  }

  /**
   * @return the rate
   */
  public double getRate() {
    return rate;
  }

  /**
   * @param rate the rate to set
   */
  public void setRate(double rate) {
    this.rate = rate;
  }

  /**
   * @return the internet
   */
  public int isInternet() {
    return internet;
  }

  /**
   * @param internet the internet to set
   */
  public void setInternet(int internet) {
    this.internet = internet;
  }


}
