package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExecutor {

  public static void main(String[] args) {

    final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport",
        "postgres", "password");
    try {
      Connection connection = dcm.getConnection();
      //OrderDAO orderDAO = new OrderDAO(connection);
      //Order order = orderDAO.findById(1000);
      //List<OrderLines> ol = new ArrayList<OrderLines>();
      //ol = order.getOrderLines();
      //System.out.print(ol.size());
      CustomerDAO customerDAO = new CustomerDAO(connection);
      //Customer customer = new Customer();
      //customer.setFirst_name("zaied");
      //customer.setLast_name("zaman");
      //customer.setEmail("zzaman2");
      //customer.setPhone("1122");
      //customer.setAddress("cccc");
      //customer.setCity("gg");
      //customer.setState("hh");
      //customer.setZipcode("0000");
      //Customer returnedCustomer = customerDAO.create(customer);
      //System.out.println(returnedCustomer.getFirst_name());
      //customerDAO.delete(10009);
      Customer customer = customerDAO.findById(1000);
      customer.setFirst_name("updated _name");
      customer = customerDAO.update(customer);
      System.out.println(customer.getFirst_name());
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
      throw new RuntimeException("JDBCExecutor Failed", e);
    }
  }

}
