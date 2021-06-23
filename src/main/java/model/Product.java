package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Float price;
    private Integer quantity;
    private String description;

    private Timestamp createDate;
    private Timestamp modifyDate;

    private Integer categoryId;
    private Category category;

    private Integer sellerId;
    private User seller;
}
