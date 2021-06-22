package dao;

import model.Order;
import model.OrderDetail;

import java.util.List;

public interface IOrderDetailsDAO extends GenericDAO<OrderDetail>{
//    Integer save(Order order);
    List<OrderDetail> findAllByOrderId(Integer orderId);
    OrderDetail findOne(Integer orderId, Integer productId);
}
