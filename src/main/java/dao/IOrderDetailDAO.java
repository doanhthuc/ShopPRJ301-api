package dao;

import model.OrderDetailModel;

import java.util.List;

public interface IOrderDetailDAO {
    void save(OrderDetailModel orderDetail);
    List<OrderDetailModel> findAllByUsername(String username);

}
