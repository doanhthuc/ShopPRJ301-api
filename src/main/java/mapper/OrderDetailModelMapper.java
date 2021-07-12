package mapper;

import model.OrderDetailModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailModelMapper implements RowMapper<OrderDetailModel>{
    @Override
    public OrderDetailModel mapRow(ResultSet resultSet) {
        try {
            OrderDetailModel orderDetail = new OrderDetailModel();
            orderDetail.setId(resultSet.getInt("id"));
            orderDetail.setProductId(resultSet.getInt("product_id"));
            orderDetail.setUsername(resultSet.getString("username"));
            orderDetail.setPrice(resultSet.getFloat("price"));
            orderDetail.setQuantity(resultSet.getInt("quantity"));
            orderDetail.setOrderDate(resultSet.getTimestamp("order_date"));
            return orderDetail;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
