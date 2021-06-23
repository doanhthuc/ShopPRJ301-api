package service;

import model.Product;
import paging.Pageable;

import java.util.List;

public interface IProductService {
    Product save(Product newProduct);
    Product update(Product updateProduct);
    void delete(int[] ids);
    List<Product> findAll(Pageable pageable);
    List<Product> findAll();
    int getTotalItem();
    Product findOne(Integer id);
}
