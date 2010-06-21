/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.tools.Options;

/**
 *
 * @author lordovol
 */
public class Database {

  public static final String PATH = "./games.db";
  private Connection conn;
  private Statement stmt;

  public Database() {
    File db = new File(PATH);
    if (db.isFile()) {
      connect();
    } else {
      connect();
      createDatabase();
    }
  }

  private void connect() {
    try {
      FlashGamesPlayer.logger.log(Level.INFO, "Creating database connection");
      Class.forName("org.sqlite.JDBC");
      setConn(DriverManager.getConnection("jdbc:sqlite:" + Options.USER_DIR + PATH));
      setStmt(getConn().createStatement());
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, "Could not connect to the SQLite database", ex);
    } catch (ClassNotFoundException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, "Could not find SQLite class", ex);
    }
  }

  private void createDatabase() {
    try {
      FlashGamesPlayer.logger.log(Level.INFO, "Creating table games");
      getStmt().executeUpdate("CREATE TABLE `games` (`id` INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
          "`genre_id` INTEGER," +
          " `title` VARCHAR, " +
          "`filename` VARCHAR,`played` INTEGER DEFAULT 0 ,`rate` DOUBLE DEFAULT 0)");
      FlashGamesPlayer.logger.log(Level.INFO, "Creating table genres");
      getStmt().executeUpdate("CREATE TABLE `genres` (`id` INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " + "`genre` VARCHAR NOT NULL  UNIQUE)");
      addDefaultGenres();
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, "Could create the tables", ex);
    }

  }

  /**
   * @return the conn
   */
  public Connection getConn() {
    return conn;
  }

  /**
   * @param conn the conn to set
   */
  public void setConn(Connection conn) {
    this.conn = conn;
  }

  /**
   * @return the stmt
   */
  public Statement getStmt() {
    return stmt;
  }

  /**
   * @param stmt the stmt to set
   */
  public void setStmt(Statement stmt) {
    this.stmt = stmt;
  }

  private void addDefaultGenres() throws SQLException {
    FlashGamesPlayer.database = new Database();
    String[] genres = {"Arcade", "Adventure", "Puzzle"};
    for (int i = 0; i < genres.length; i++) {
      String genre = genres[i];
      Genre g = new Genre();
      g.setGenre(genre);
      g.save();
    }
  }
}
