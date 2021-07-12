package service.impl;

import dao.IOrderDAO;
import dao.IOrderDetailsDAO;
import dao.IProductDAO;
import dao.IUserDAO;
import model.*;
import service.IOrderService;
import service.IRankService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    @Inject
    IOrderDAO orderDAO;

    @Inject
    IUserDAO userDAO;

    @Inject
    IOrderDetailsDAO orderDetailsDAO;

    @Inject
    IProductDAO productDAO;

    @Inject
    IRankService rankService;

    @Override
    public Order save(Order newOrder) {
        newOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        Integer orderId = orderDAO.save(newOrder);
        List<Order> orders = orderDAO.findAllByCustomerId(newOrder.getCustomerId());
        float totalSpent = 0F;
        for (Order order: orders) {
            List<OrderDetail> orderDetails = orderDetailsDAO.findAllByOrderId(order.getId());
            for (OrderDetail detail: orderDetails) {
                totalSpent += detail.getQuantity() * detail.getPrice();
            }
        }
        Ranking rank = rankService.classifyRank(totalSpent);
        User user = userDAO.findOne(newOrder.getCustomerId());
        user.setRankId(rank.getId());
        return orderDAO.findOne(orderId);
    }

    @Override
    public List<Order> findAllByCustomerId(Integer customerId) {
        List<Order> orders = orderDAO.findAllByCustomerId(customerId);
        for (Order order: orders) {
            List<OrderDetail> orderDetails = orderDetailsDAO.findAllByOrderId(order.getId());
            List<Product> productList = new ArrayList<>();
            for (OrderDetail orderDetail: orderDetails) {
                productList.add(productDAO.findOne(orderDetail.getProductId()));
            }
            order.setOrderList(productList);
        }
        return orders;
    }

//    public Order save(Order newOrder, String username) {
//        newOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
//    }
}
