package com.RocketTeam.Configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnManager {
  private static ConnManager instance;

  private ConnManager() {
	  
  }

  public static ConnManager getInstance() {
    if (instance == null) {
      instance = new ConnManager();
    }
    return instance;
  }

  public Connection getConnection() {
	  return getConnection(true);
  }
  
  public Connection getConnection(boolean AutoCommit) {
    Connection conn = null;

    try {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	
	    conn = DriverManager.getConnection("jdbc:oracle:thin:@20.206.88.149:1521:fiaptest", "system", "Naotemsenha");
	    conn.setAutoCommit(AutoCommit);
    } catch (SQLException | ClassNotFoundException e) {
    	e.printStackTrace();
    }
    return conn;
  }
}