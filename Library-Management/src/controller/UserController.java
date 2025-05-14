package controller;

import model.User;
import service.interfaces.IUserService;
import service.entries.UserService;
import util.PasswordUtil;

import java.util.List;

public class UserController {
    private final IUserService userService;

    public UserController() {
        this.userService = UserService.getInstance();
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

    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        try {
            PasswordUtil.validatePassword(newPassword);
            return userService.changePassword(userId, oldPassword, newPassword);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyOldPassword(String userId, String oldPassword) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return PasswordUtil.checkPassword(oldPassword, user.getPassword());
        } else {
            System.out.println("User not found with ID: " + userId);
            return false;
        }
    }

    public User getUserById(String userId) {
        return userService.getUserById(userId);
    }

    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }
}