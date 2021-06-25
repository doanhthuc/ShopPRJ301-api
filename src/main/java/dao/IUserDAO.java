package dao;

import model.User;
import paging.Pageable;

import java.util.List;

public interface IUserDAO extends GenericDAO<User> {
    Integer save(User user);
    User findOne(Integer id);
    User findOne(String username);
    void update(User updateUser);
    void delete(Integer id);
    User findByUsernameAndPassword(String userName, String password);
    boolean hasExistedUsername(String username);
}
