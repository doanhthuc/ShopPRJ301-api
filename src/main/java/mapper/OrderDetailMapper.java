package mapper;

import model.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailMapper implements RowMapper<OrderDetail> {
    @Override
    public OrderDetail mapRow(ResultSet resultSet) {
        try {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(resultSet.getInt("order_id"));
            orderDetail.setProductId(resultSet.getInt("product_id"));
            orderDetail.setQuantity(resultSet.getInt("quantity"));
            orderDetail.setPrice(resultSet.getFloat("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
