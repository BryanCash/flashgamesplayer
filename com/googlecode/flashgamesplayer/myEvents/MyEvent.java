/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.myEvents;

import com.googlecode.flashgamesplayer.database.Game;
import java.util.EventObject;

/**
 *
 * @author ssoldatos
 */
public class MyEvent extends EventObject {

  private static final long serialVersionUID = 34536467474567L;
  private int type;
  private Game game = null;
  
  public MyEvent(Object source, int type) {
    super(source);
    this.type = type;

  }

  public int getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(int type) {
    this.type = type;
  }

  /**
   * @return the game
   */
  public Game getGame() {
    return game;
  }

  /**
   * @param game the game to set
   */
  public void setGame(Game game) {
    this.game = game;
  }

  
}
