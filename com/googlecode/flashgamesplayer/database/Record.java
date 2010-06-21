/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import com.googlecode.flashgamesplayer.FlashGamesPlayer;

/**
 * The base class for SQLite Records
 * @author lordovol
 */
public class Record {

  
  /**
   * The default constructor
   */
  Record() {
  }

  /**
   * Executes a query
   * @param sql The query to execute
   * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
   *         or (2) 0 for SQL statements that return nothing
   * @throws SQLException
   */
  public static ResultSet query(String sql) throws SQLException {
    return FlashGamesPlayer.database.getStmt().executeQuery(sql);
  }

  /**
   * execute an update query
   * @param sql
   * @return the id of the inserted record or -1
   * @throws java.sql.SQLException
   */
  public static int queryUpdate(String sql) throws SQLException {
    ResultSet rs = null;
    try {
     // System.out.println("save " + sql);
      FlashGamesPlayer.database.getStmt().executeUpdate(sql);
      rs = FlashGamesPlayer.database.getStmt().executeQuery("SELECT last_insert_rowid() AS id");
      int ai;
      if (rs.next()) {
        ai = rs.getInt("id");
      } else {
        ai = -1;
      }
      rs.close();
      return ai;
    } catch (SQLException ex){
      if(ex.getMessage().equals("cannot commit transaction - SQL statements in progress")){
       // System.out.println("Retry " + sql);
        return queryUpdate(sql);
      } else {
       // System.out.println("fail " + sql);
        throw ex;
      }
    } finally {
      if (rs != null) {
        rs.close();
      }

    }
  }
}
