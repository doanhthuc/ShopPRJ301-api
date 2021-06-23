package service;

import model.Order;

import java.util.List;

public interface IOrderService {
    Order save(Order newOrder);
    List<Order> findAllByCustomerId(Integer customerId);
}
