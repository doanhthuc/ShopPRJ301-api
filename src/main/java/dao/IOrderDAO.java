package dao;

import model.Order;

import java.util.List;

public interface IOrderDAO extends GenericDAO<Order>{
    Integer save(Order order);
    List<Order> findAllByCustomerId(Integer customerId);
    Order findOne(Integer orderId);
}
