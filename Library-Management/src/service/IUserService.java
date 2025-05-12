package service;

import model.User;
import java.util.List;

public interface IUserService {
    void register(User user);
    boolean deleteUser(String userId);
    User findByUsername(String username);
    List<User> getAllUsers();
    User getUserById(String userId);
    boolean changePassword(String userId, String oldPassword, String newPassword);
}
