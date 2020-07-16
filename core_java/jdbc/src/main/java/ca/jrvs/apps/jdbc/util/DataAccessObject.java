package ca.jrvs.apps.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataAccessObject<T extends DataTransferObject> {

  protected final Logger logger = LoggerFactory.getLogger(DataAccessObject.class);
  protected final Connection connection;
  protected String CUSTOMER_SEQ = "hp_customer_seq";

  public DataAccessObject(Connection connection) {
    super();
    this.connection = connection;
  }

  public abstract T create(T dto);

  public abstract T findById(long id);

  public abstract T update(T dto);

  public abstract void delete(long id);

  protected int getLastVal(String col) {

    String q = "select last_value from " + col;
    try (Statement statement = this.connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(q);
      while (resultSet.next()) {
        return resultSet.getInt(1);
      }

    } catch (SQLException e) {
      this.logger.error(e.getMessage(), e);
      throw new RuntimeException("getLastVal Failed", e);
    }
    return 0;
  }
}
