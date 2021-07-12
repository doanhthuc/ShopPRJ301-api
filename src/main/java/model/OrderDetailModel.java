package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDetailModel {
    private int id;
    private int productId;
    private String username;
    private float price;
    private int quantity;
    private Timestamp orderDate;
    private List<OrderDetailModel> orderDetailList;


    public OrderDetailModel(int productId, String username, float price, int quantity, Timestamp orderDate) {
        this.productId = productId;
        this.username = username;
        this.price = price;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }
}
