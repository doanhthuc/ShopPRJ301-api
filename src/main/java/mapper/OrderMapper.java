package mapper;

import model.Order;
import model.OrderDetail;
import model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet) {
        try {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setCustomerId(resultSet.getInt("customer_id"));
            order.setOrderDate(resultSet.getTimestamp("order_date"));
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
