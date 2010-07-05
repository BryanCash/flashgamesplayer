/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.tools;

import com.googlecode.flashgamesplayer.games.GameForm;
import java.io.File;
import net.iharder.dnd.FileDrop;
import net.iharder.dnd.FileDropEvent;
import net.iharder.dnd.FileDropListener;

/**
 *
 * @author ssoldatos
 */
public class MyFileDropListener implements FileDrop.Listener {

  @Override
  public void filesDropped(File[] files) {
    if (files.length != 1) {
      MyMessages.error("Add Game", "You can only drop 1 file");
      return;
    }
    if(!files[0].getName().toLowerCase().endsWith(".swf")){
      MyMessages.error("Add Game", "You can only add swf files");
      return;
    } else {
      new GameForm(files[0]);
    }
  }
}
