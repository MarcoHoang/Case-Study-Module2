package service;

import model.User;
import java.util.List;

public interface IUserService {
    User login(String username, String password);
    void register(User user);
    boolean deleteUser(String userId);
    List<User> getAllUsers();
}
