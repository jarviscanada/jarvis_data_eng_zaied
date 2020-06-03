package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

  private String url;
  private Properties properties;

  public DatabaseConnectionManager(String host, String databseName,
      String username, String password) {
    this.url = "jdbc:postgresql://" + host + "/" + databseName;
    this.properties = new Properties();
    this.properties.setProperty("user", username);
    this.properties.setProperty("password", password);
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(this.url, this.properties);
  }

}
