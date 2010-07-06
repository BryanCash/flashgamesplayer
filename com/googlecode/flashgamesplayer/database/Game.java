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
  private boolean newGame;
  private String password;
  private int deleted;
  public static int NO_INTERNET = 0;
  public static int INTERNET = 1;
  public static int NOT_DELETED = 0;
  public static int DELETED = 1;

  public Game() {
    super();
    setNewGame(true);
  }

  public int save() throws SQLException {
    String sql;
    if (this.getId() != 0) {
      sql = "UPDATE games SET " + "genre_id = " + this.getGenre_id()
          + ", title = '" + this.getTitle()
          + "', filename = '" + this.getFilename()
          + "', password = '" + this.getPassword()
          + "', internet = " + this.isInternet()
          + ", deleted = " + this.getDeleted()
          + " WHERE id = " + this.getId();
    } else {
      sql = "INSERT INTO games (genre_id, title, filename, internet, password, deleted ) "
          + "VALUES(" + this.getGenre_id() + ", '" + this.getTitle()
          + "', '" + this.getFilename() + "'," + this.isInternet()
          + ", '" + this.getPassword() + "', " + this.getDeleted() + " )";
    }
    return queryUpdate(sql);
  }

  public static Game getGameById(int id) {
    try {
      String sql = "SELECT * FROM games WHERE id = " + id;
      Game game = new Game();
      ResultSet rs = query(sql);
      if (rs.next()) {
        game.filename = rs.getString("filename");
        game.genre_id = rs.getInt("genre_id");
        game.id = rs.getInt("id");
        game.title = rs.getString("title");
        game.played = rs.getInt("played");
        game.rate = rs.getDouble("rate");
        game.internet = rs.getInt("internet");
        game.password = rs.getString("password");
        game.deleted = rs.getInt("deleted");
        game.setNewGame(false);
        return game;
      }
      return null;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return null;
    }
  }

  private int updatePlayed(int id) {
    int result;
    String sql = "UPDATE games SET played = played + 1";
    try {
      result = queryUpdate(sql);
      this.played++;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return -1;
    }
    return result;

  }

  private int updatePassword(String password) {
    int result;
    String sql = "UPDATE games SET password = '" + password + "'";
    try {
      result = queryUpdate(sql);
      this.password = password;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return -1;
    }
    return result;
  }

  private int updateRate(double rate) {
    int result;
    String sql = "UPDATE games SET rate = " + rate + " WHERE id = " + id;
    try {
      result = queryUpdate(sql);
      this.rate = rate;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return -1;
    }
    return result;
  }

  private boolean delete() {
    try {
      String sql = "UPDATE games SET deleted = " + DELETED + " WHERE id =" + this.getId();
      queryUpdate(sql);
      this.deleted = DELETED;
      //new File(Options.USER_DIR + Options.GAMES_DIR + this.getFilename()).delete();
      return true;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return false;
    }

  }

  private boolean restore() {
    try {
      String sql = "UPDATE games SET deleted = " + NOT_DELETED + " WHERE id =" + this.getId();
      queryUpdate(sql);
      return true;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return false;
    }

  }

  public static Game getFirstGame(int genre, boolean deleted) {
    String sql;
    if (genre == 0) {
      sql = "SELECT id FROM games" + (!deleted ? " WHERE deleted = " + NOT_DELETED : "");
    } else {
      sql = "SELECT id FROM games WHERE genre_id = " + genre + (!deleted ? " AND deleted = " + NOT_DELETED : "");
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
    updatePlayed(id);
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
    updateRate(rate);
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

  /**
   * @return the newGame
   */
  public boolean isNewGame() {
    return newGame;
  }

  /**
   * @param newGame the newGame to set
   */
  public void setNewGame(boolean newGame) {
    this.newGame = newGame;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    updatePassword(password);
  }

  /**
   * @return the deleted
   */
  public int getDeleted() {
    return deleted;
  }

  /**
   * @param deleted the deleted to set
   */
  public boolean setDeleted(int deleted) {
    if(deleted == NOT_DELETED){
     return restore();
    } else if(deleted == DELETED){
      return delete();
    }
    return false;
  }
}
