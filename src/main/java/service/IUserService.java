package service;

import model.User;

public interface IUserService {
    User register(User user);
    User findByUsernameAndPassword(String userName, String password);
}
