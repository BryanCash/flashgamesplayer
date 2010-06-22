/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.flashgamesplayer.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author lordovol
 */
public class MyFunctions {

  /**
   * Copies a file
   * @param srFile The source file
   * @param dtFile The destination file
   * @throws java.io.FileNotFoundException
   * @throws java.io.IOException
   * @return true if succedded copying
   */
  public static boolean copyfile(String srFile, String dtFile) throws FileNotFoundException, IOException {

    File f1 = new File(srFile);
    File f2 = new File(dtFile);
    InputStream in = new FileInputStream(f1);
    OutputStream out = new FileOutputStream(f2);
    try {
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }
    } finally {
      in.close();
      out.close();
    }
    return true;
  }

  /**
   * Initializes internet connection settings
   */
  public static void initInternetConnection() {
//    if (Options.toBoolean(Options.USE_PROXY)) {
//      Properties props = System.getProperties();
//      props.put("http.proxyHost", Options.toString(Options.PROXY_HOST));
//      props.put("http.proxyPort", Options.toString(Options.PROXY_PORT));
//      System.setProperties(props);
//    } else {
    Properties props = System.getProperties();
    props.put("http.proxyHost", "192.168.6.191");
    props.put("http.proxyPort", "8080");
    System.setProperties(props);
//    }
  }

  public static boolean hasInternetConnection(String address) {
    initInternetConnection();
    BufferedReader in = null;
    try {
      URL url = new URL(address);
      in = new BufferedReader(new InputStreamReader(url.openStream()));
      return true;
    } catch (IOException ex) {
      return false;
    }
  }
}
