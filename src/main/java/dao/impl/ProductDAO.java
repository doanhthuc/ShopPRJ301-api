package dao.impl;

import dao.IProductDAO;
import mapper.ProductMapper;
import model.Product;
import paging.Pageable;
import sort.Sorter;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product> implements IProductDAO {
    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        return null;
    }

    @Override
    public Integer save(Product product) {
        String sql = "INSERT INTO product (name, category_id, price, quantity, seller_id, description, image_url, create_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, product.getName(), product.getCategoryId(), product.getPrice(), product.getQuantity(),
                product.getSellerId(), product.getDescription(), product.getImageUrl(), product.getCreateDate());
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT p.id, p.name, p.price, p.quantity, p.description, p.image_url, c.id category_id, c.name category_name, \n" +
                "u.id seller_id, u.full_name seller_name, u.phone seller_phone, u.address seller_address\n" +
                "FROM Product p JOIN Category c ON p.category_id = c.id \n" +
                "JOIN User_Account u ON p.seller_id = u.id";
        return query(sql, new ProductMapper());
    }

    @Override
    public Product findOne(Integer id) {
        String sql = "SELECT p.id, p.name, p.price, p.quantity, p.description, p.image_url, c.id category_id, " +
                "c.name category_name, u.id seller_id, u.full_name seller_name, u.phone seller_phone, u.address seller_address\n" +
                "FROM Product p JOIN Category c ON p.category_id = c.id \n" +
                "JOIN User_Account u ON p.seller_id = u.id WHERE p.id = ?";
        List<Product> products = query(sql, new ProductMapper(), id);
        return products.isEmpty() ? null : products.get(0);
    }

    @Override
    public void update(Product updatedProduct) {
        String sql = "UPDATE product SET name = ?, category_id = ?, price = ?, quantity = ?, seller_id = ?, " +
                "description = ?, image_url = ? WHERE id = ?";
        update(sql, updatedProduct.getName(), updatedProduct.getCategoryId(), updatedProduct.getPrice(),
                updatedProduct.getQuantity(), updatedProduct.getSellerId(), updatedProduct.getDescription(),
                updatedProduct.getId(), updatedProduct.getImageUrl());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM product WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<Product> findAll(Pageable pageable) {
        String sql = "SELECT p.id, p.name, p.price, p.quantity, p.description, p.image_url, c.id category_id, c.name category_name, " +
                "u.id seller_id, u.full_name seller_name, u.phone seller_phone, u.address seller_address " +
                "FROM Product p JOIN Category c ON p.category_id = c.id " +
                "JOIN User_Account u ON p.seller_id = u.id\n";
        pageable.setSorter(pageable.getSorter().getSortName() == null ? new Sorter("id", "")
                : pageable.getSorter());
        sql += "ORDER BY " + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy();
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS FETCH NEXT " + pageable.getLimit() + " ROWS ONLY;";
        }
        return query(sql, new ProductMapper());
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM product";
        return count(sql);
    }
}
