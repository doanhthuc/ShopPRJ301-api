package dao.impl;

import dao.IOrderDetailDAO;
import mapper.OrderDetailModelMapper;
import model.OrderDetailModel;

import java.util.List;

public class OrderDetailDAO extends AbstractDAO<OrderDetailModel> implements IOrderDetailDAO {

    @Override
    public void save(OrderDetailModel orderDetail) {
        String sql = "INSERT INTO Order_Detail " +
                "(product_id, username, price, quantity, order_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        insert(sql, orderDetail.getProductId(), orderDetail.getUsername(),
                orderDetail.getPrice(), orderDetail.getQuantity(), orderDetail.getOrderDate());
    }

    @Override
    public List<OrderDetailModel> findAllByUsername(String username) {
        String sql = "SELECT * FROM Order_Detail WHERE username = ?";
        return query(sql, new OrderDetailModelMapper(), username);
    }
}
