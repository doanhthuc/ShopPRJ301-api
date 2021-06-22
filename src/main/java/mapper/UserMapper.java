package mapper;

import model.Ranking;
import model.Role;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet) {

        try {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setFullName(resultSet.getString("full_name"));
            user.setPassword(resultSet.getString("password"));
            user.setAddress(resultSet.getString("address"));
            user.setPhone(resultSet.getString("phone"));
            try{
                Role role = new Role();
                role.setName(resultSet.getString("role_id"));
                role.setName(resultSet.getString("role_name"));
                user.setRole(role);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            try{
                Ranking rank = new Ranking();
                rank.setName(resultSet.getString("rank_id"));
                rank.setName(resultSet.getString("rank_name"));
                rank.setDiscountPercent(resultSet.getFloat("discount_percent"));
                user.setRank(rank);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return user;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
