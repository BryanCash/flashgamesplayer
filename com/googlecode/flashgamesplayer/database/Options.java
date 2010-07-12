/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.flashgamesplayer.database;

import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssoldatos
 */
public class Options extends Record{

  public static final String USER_DIR = "./";
  public static final String GAMES_DIR = "games/";
  public static final String SCREENSHOT_DIR = "screenshots/";
  public static final int SCREENSHOT_WIDTH = 340;
  public static final int SCREENSHOT_HEIGHT = 256;
  public static final String STRING = "string";
  public static final String INTEGER = "int";
  public static final String BOOLEAN = "boolean";
  public static final String PROXY = "proxy";
  public static final String PORT = "port";
  public static final String USE_PROXY = "useProxy";
  public static final String TREE_ROW_HEIGHT = "treeRowHeight";
  public static final String DISPLAY_GAME_TITLE = "displayGameTitle";
  public static final String DISPLAY_GAME_SCREENSHOT = "displayGameScreenshot";

  private String option;
  private String type;
  private String value;

  public Options(String option, String type, String value) {
    this.option = option;
    this.type = type;
    this.value = value;
  }

  public int save() throws SQLException {
    String sql;

    sql = "UPDATE options SET option = '" + this.getOption() +"', "
        + "type = '" + this.getType() +"', "
        + "value ='"+this.getValue() +"' WHERE option = '" + this.getOption() +"'";
    return queryUpdate(sql);
  }

  public static HashMap<String,Object> getOptions() {
    HashMap<String,Object> options = new HashMap<String,Object>();
    try {
      String sql = "SELECT * FROM options";
      ResultSet rs = query(sql);
      while (rs.next()){
        String type = rs.getString("type");
        if(type.equals(STRING)){
          options.put(rs.getString("option"), rs.getString("value"));
        } else if(type.equals(INTEGER)){
          options.put(rs.getString("option"), Integer.parseInt(rs.getString("value")));
        }else if(type.equals(BOOLEAN)){
          options.put(rs.getString("option"), Boolean.parseBoolean(rs.getString("value")));
        }
      }
      return options;
    } catch (SQLException ex) {
      FlashGamesPlayer.logger.log(Level.SEVERE, null, ex);
      return options;
    }
  }

  

  /**
   * @return the option
   */
  public String getOption() {
    return option;
  }

  /**
   * @param option the option to set
   */
  public void setOption(String option) {
    this.option = option;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }



}
