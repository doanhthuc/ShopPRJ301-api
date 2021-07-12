package service.impl;

import dao.IOrderDetailDAO;
import model.OrderDetailModel;
import service.IOrderDetailService;
import service.IRankService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class OrderDetailService implements IOrderDetailService {

    @Inject
    IOrderDetailDAO orderDetailDAO;

    @Inject
    IRankService rankService;


    @Override
    public void saveOrderDetail(List<OrderDetailModel> orderDetailList) {
        for (OrderDetailModel orderDetail: orderDetailList) {
            orderDetail.setOrderDate(new Timestamp(System.currentTimeMillis()));
            orderDetailDAO.save(orderDetail);
        }
        float totalSpent = 0;
        List<OrderDetailModel> orders = orderDetailDAO.findAllByUsername(orderDetailList.get(0).getUsername());
        if (orders != null) {
            for (OrderDetailModel orderDetail: orders) {
                totalSpent += orderDetail.getPrice() * orderDetail.getQuantity();
            }
            rankService.classifyRank(totalSpent);
        }
    }

    @Override
    public List<OrderDetailModel> findAllOrderDetailByUsername(String username) {
        return orderDetailDAO.findAllByUsername(username);
    }
}
