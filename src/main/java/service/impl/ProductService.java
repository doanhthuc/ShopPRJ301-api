package service.impl;

import dao.IProductDAO;
import model.Product;
import paging.Pageable;
import service.IProductService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class ProductService implements IProductService {

    @Inject
    IProductDAO productDAO;

    @Override
    public Product save(Product newProduct) {
        newProduct.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Integer id = productDAO.save(newProduct);
        return productDAO.findOne(id);
    }

    @Override
    public Product update(Product updateProduct) {
        Product oldProduct = productDAO.findOne(updateProduct.getId());
        updateProduct.setCreateDate(oldProduct.getCreateDate());
        updateProduct.setModifyDate(new Timestamp(System.currentTimeMillis()));
        productDAO.update(updateProduct);
        return productDAO.findOne(updateProduct.getId());
    }

    @Override
    public void delete(int[] ids) {
        for (int id: ids) {
            productDAO.delete(id);
        }
    }

    @Override
    public List<Product> findAll(Pageable pageable) {
        return productDAO.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public int getTotalItem() {
        return productDAO.getTotalItem();
    }

    @Override
    public Product findOne(Integer id) {
        return productDAO.findOne(id);
    }
}
