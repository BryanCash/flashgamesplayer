/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.flashgamesplayer.games.internet;

/**
 *
 * @author ssoldatos
 */
public class Finder implements Runnable{
  private final AddInternetGame form;

  public Finder(AddInternetGame form) {
    this.form = form;
  }


  @Override
  public void run() {
    form.progress.setString("ddd");
    form.lb_found.setText("asasasasas");
    System.out.println("asdasdada");
  }

}
