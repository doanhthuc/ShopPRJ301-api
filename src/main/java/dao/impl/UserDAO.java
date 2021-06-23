package dao.impl;

import dao.IUserDAO;
import mapper.UserMapper;
import model.User;

import java.util.List;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {
    @Override
    public Integer save(User user) {
        String sql = "INSERT INTO user_account (full_name, username, password, address, phone, role_id, rank_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, user.getFullName(), user.getUsername(), user.getPassword(), user.getAddress(),
                user.getPhone(), user.getRoleId(), user.getRankId());
    }

    @Override
    public User findOne(Integer id) {
        String sql = "SELECT u.id, u.full_name, u.username, u.password, u.address, u.phone, r.id role_id, \n" +
                "r.name role_name, ra.id rank_id, ra.name rank_name, ra.discount_percent \n" +
                "FROM User_Account u JOIN Role r ON u.role_id = r.id \n" +
                "JOIN Ranking ra ON u.rank_id = ra.id " +
                "WHERE u.id = ?";
        List<User> list = query(sql, new UserMapper(), id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void update(User updateUser) {
        String sql = "UPDATE user_Account SET full_name = ?, username = ?, password = ?, address = ?, \n" +
                "phone = ?, role_id = ?, rank_id = ? WHERE id = ?";
        update(sql, updateUser.getFullName(), updateUser.getUsername(), updateUser.getPassword(),
               updateUser.getAddress(), updateUser.getPhone(), updateUser.getRoleId(),
               updateUser.getRankId());
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public boolean hasExistedUsername(String username) {
        String sql = "SELECT COUNT(*) FROM user_account WHERE username = ?";
        return count(sql, username) > 0;
    }

    @Override
    public User findByUsernameAndPassword(String userName, String password) {
        String sql = "SELECT u.full_name, u.username, u.password, u.address, u.phone, r.id role_id, \n" +
                "r.name role_name, ra.id rank_id, ra.name rank_name, ra.discount_percent \n" +
                "FROM User_Account u JOIN Role r ON u.role_id = r.id \n" +
                "JOIN Ranking ra ON u.rank_id = ra.id " +
                "WHERE username = ? AND password = ?";
        List<User> list = query(sql, new UserMapper(), userName, password);
        return list.isEmpty() ? null : list.get(0);
    }
}
