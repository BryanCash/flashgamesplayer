/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.myEvents;

import com.googlecode.flashgamesplayer.FlashGamesPlayer;
import com.googlecode.flashgamesplayer.database.Game;

/**
 *
 * @author ssoldatos
 */
public class MyEventHandler implements MyEventListener {

  public static final int UNLOAD_GAME = 0;
  public static final int ADD_GAME = 1;
  public static final int LOAD_GAME = 2;
  public static final int SELECT_GAME = 3;
  public static final int DELETE_GAME = 4;
  public static final int RESTORE_GAME = 5;
  public static final int INTERNET_CONNECTION = 6;

  @Override
  public void myEventOccured(MyEvent evt) {
    Game game = null;
    switch (evt.getType()) {
      case UNLOAD_GAME:
        FlashGamesPlayer.gamePanel.setGame(null);
        break;
      case ADD_GAME:
        FlashGamesPlayer.gamesTree.populateTree();
        break;
      case LOAD_GAME:
        game = evt.getGame();
        FlashGamesPlayer.gamePanel.setGame(game);
        break;
      case SELECT_GAME:
        game = evt.getGame();
        FlashGamesPlayer.gamePanel.setGame(game, false);
        break;
      case DELETE_GAME:
        FlashGamesPlayer.gamePanel.init();
        break;
      case RESTORE_GAME:
        game = evt.getGame();
        FlashGamesPlayer.gamePanel.setGame(game);
        FlashGamesPlayer.gamesTree.populateTree();
        break;
      case INTERNET_CONNECTION:
        if (FlashGamesPlayer.isInternet) {
          FlashGamesPlayer.bt_internet.setIcon(FlashGamesPlayer.bt_internet.getIcon());
        } else {
          FlashGamesPlayer.bt_internet.setIcon(FlashGamesPlayer.bt_internet.getDisabledIcon());
        }
        break;
    }
  }
}
