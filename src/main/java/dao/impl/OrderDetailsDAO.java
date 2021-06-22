package dao.impl;

import dao.IOrderDetailsDAO;
import mapper.OrderDetailMapper;
import model.OrderDetail;

import java.util.List;

public class OrderDetailsDAO extends AbstractDAO<OrderDetail> implements IOrderDetailsDAO {

    @Override
    public List<OrderDetail> findAllByOrderId(Integer orderId) {
        String sql = "SELECT * FROM order_details WHERE order_id = ?";
        return query(sql, new OrderDetailMapper(), orderId);
    }

    @Override
    public OrderDetail findOne(Integer orderId, Integer productId) {
        String sql = "SELECT * FROM order_details WHERE order_id = ? AND product_id = ?";
        List<OrderDetail> list = query(sql, new OrderDetailMapper(), orderId, productId);
        return list.isEmpty() ? null : list.get(0);
    }
}
