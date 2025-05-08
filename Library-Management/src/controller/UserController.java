package controller;

import model.User;
import service.IUserService;
import service.UserService;

import java.util.List;

public class UserController {
    private final IUserService userService = new UserService();

    public User login(String username, String password) {
        return userService.login(username, password);
    }

    public void register(User user) {
        userService.register(user);
    }

    public boolean deleteUser(String userId) {
        return userService.deleteUser(userId);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
