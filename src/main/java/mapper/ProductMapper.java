package mapper;

import model.Category;
import model.Product;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet) {
        try {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getFloat("price"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setDescription(resultSet.getString("description"));
            product.setImageUrl(resultSet.getString("image_url"));
            try {
                Category category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));
                product.setCategory(category);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                User seller = new User();
                seller.setId(resultSet.getInt("seller_id"));
                seller.setFullName(resultSet.getString("seller_name"));
                seller.setPhone(resultSet.getString("seller_phone"));
                seller.setAddress(resultSet.getString("seller_address"));
                product.setSeller(seller);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
