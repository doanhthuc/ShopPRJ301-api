package service;

import model.OrderDetailModel;

import java.util.List;

public interface IOrderDetailService {
    void saveOrderDetail(List<OrderDetailModel> orderDetailList);
    List<OrderDetailModel> findAllOrderDetailByUsername (String username);
}
