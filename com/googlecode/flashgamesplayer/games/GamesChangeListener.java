/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.games;

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
  public static final String GAME_PLAY = "gamePlay";
  public static final String GAME_DELETED = "gameDeleted";
  public static final String GAME_RESTORED = "gameRestored";
  public static final String GAME_SELECTED = "gameSelected";

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals(GAME_ADDED)) {
      FlashGamesPlayer.gamesTree.populateTree();
    } else if (evt.getPropertyName().equals(GAME_PLAY)) {
      Game game = (Game) evt.getNewValue();
      FlashGamesPlayer.gamePanel.setGame(game);
    } else if (evt.getPropertyName().equals(GAME_DELETED)) {
      FlashGamesPlayer.gamePanel.setGame(null);
      FlashGamesPlayer.label_gameTitle.setText("");
      FlashGamesPlayer.rating.setRate(0.0);
      FlashGamesPlayer.rating.setRatingEnabled(false);
      FlashGamesPlayer.tf_plays.setText("0");
      FlashGamesPlayer.gamesTree.populateTree();
    } else if (evt.getPropertyName().equals(GAME_RESTORED)) {
      Game game = (Game) evt.getNewValue();
      FlashGamesPlayer.gamePanel.setGame(game);
      FlashGamesPlayer.gamesTree.populateTree();
    } else if (evt.getPropertyName().equals(GAME_SELECTED)) {
      Game game = (Game) evt.getNewValue();
      FlashGamesPlayer.gamePanel.setGame(game, false);
    }
  }
}
