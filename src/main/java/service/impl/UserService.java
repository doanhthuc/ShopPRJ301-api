package service.impl;

import dao.IUserDAO;
import model.User;
import service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {

    @Inject
    IUserDAO userDAO;

    @Override
    public User register(User user) {
        Integer id = userDAO.save(user);
        return userDAO.findOne(id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDAO.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findOne(String username) {
        return userDAO.findOne(username);
    }
}
