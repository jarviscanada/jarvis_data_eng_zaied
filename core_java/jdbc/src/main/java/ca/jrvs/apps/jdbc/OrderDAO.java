package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderDAO extends DataAccessObject<Order> {

  protected final Logger logger = LoggerFactory.getLogger(OrderDAO.class);

  private String findById =
      "select c.first_name,c.last_name, c.email, o.order_id, o.creation_date, "
          + "o.total_due, o.status, s.first_name, s.last_name, s.email,ol.quantity, p.code, p.name, p.size, "
          + "p.variety, p.price from orders o join customer c on o.customer_id=c.customer_id join salesperson s "
          + "on o.salesperson_id=s.salesperson_id join order_item ol on ol.order_id=o.order_id join product p "
          + "on ol.product_id = p.product_id where o.order_id=?";

  public OrderDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Order create(Order dto) {
    return null;
  }

  @Override
  public Order findById(long id) {
    Order order = new Order();
    try (PreparedStatement preparedStatement = this.connection.prepareStatement(findById)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<OrderLines> orderLinesList = new ArrayList<OrderLines>();
      while (resultSet.next()) {
        order.setId(resultSet.getLong("order_id"));
        order.setCustomerFirstname(resultSet.getString("first_name"));
        order.setCustomerLastName(resultSet.getString("last_name"));
        order.setCustomerEmail(resultSet.getString("email"));
        order.setCreationDate(resultSet.getTimestamp("creation_date"));
        order.setTotalDue(resultSet.getInt("total_due"));
        order.setStatus(resultSet.getString("status"));
        order.setSalespersonFirstName(resultSet.getString("first_name"));
        order.setSalespersonLastName(resultSet.getString("last_name"));
        order.setSalespersonEmail(resultSet.getString("email"));
        OrderLines currOrderLine = new OrderLines();
        currOrderLine.setQuantity(resultSet.getInt("quantity"));
        currOrderLine.setProductCode(resultSet.getString("code"));
        currOrderLine.setProductName(resultSet.getString("name"));
        currOrderLine.setProductSize(resultSet.getInt("size"));
        currOrderLine.setProductVariety(resultSet.getString("variety"));
        currOrderLine.setProductPrice(resultSet.getDouble("price"));
        orderLinesList.add(currOrderLine);
      }
      order.setOrderLines(orderLinesList);
    } catch (SQLException e) {
      this.logger.error(e.getMessage(), e);
      throw new RuntimeException("findById Failed");
    }
    return order;
  }

  @Override
  public Order update(Order dto) {
    return null;
  }

  @Override
  public void delete(long id) {

  }
}
