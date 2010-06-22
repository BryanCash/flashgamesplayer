/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.tools;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Game;

/**
 *
 * @author ssoldatos
 */
public class GamesChangeListener implements PropertyChangeListener {

  public static final String GAME_ADDED = "gameAdded";
  public static final String GAME_SELECTED = "gameSelected";

  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals(GAME_ADDED)) {
      FlashGamesPlayer.gamesTree.populateTree();
    } else if (evt.getPropertyName().equals(GAME_SELECTED)) {
      Game game = (Game) evt.getNewValue();
      FlashGamesPlayer.gamePanel.setGame(game);
      FlashGamesPlayer.label_gameTitle.setText(game.getTitle());
      FlashGamesPlayer.rating.setRate(game.getRate());
      FlashGamesPlayer.rating.setRatingEnabled(true);
      FlashGamesPlayer.tf_plays.setText(String.valueOf(game.getPlayed()));
    }
  }
}
