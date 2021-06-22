package dao;

import model.Product;
import model.User;
import paging.Pageable;

import java.util.List;

public interface IProductDAO extends GenericDAO<Product> {
    List<Product> findByCategoryId(Integer categoryId);
    Integer save(Product product);
    Product findOne(Integer id);
    void update(Product updatedProduct);
    void delete(Integer id);
    List<Product> findAll(Pageable pageable);
    List<Product> findAll();
    int getTotalItem();
}
