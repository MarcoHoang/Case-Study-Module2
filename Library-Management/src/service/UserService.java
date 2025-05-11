package service;

import model.User;
import storage.GenericFileStorage;
import config.AppConfig;
import util.PasswordUtil;
import java.util.List;
import java.util.ArrayList;

public class UserService implements IUserService {
    private static UserService instance;
    private final GenericFileStorage<User> storage = new GenericFileStorage<>();
    private List<User> users;

    private UserService() {
        users = storage.readFromFile(AppConfig.USER_FILE);
        if (users == null) {
            users = new ArrayList<>();
        }
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public User login(String username, String password) {
        User user = findByUsername(username);
        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void register(User user) {
        if (isUsernameTaken(user.getUserName())) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
        }
        users.add(user);
        save();
    }

    @Override
    public boolean deleteUser(String userId) {
        boolean removed = users.removeIf(u -> u.getId().equals(userId));
        if (removed) save();
        return removed;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElse(null);
    }

    private boolean isUsernameTaken(String username) {
        if (users == null || username == null) {
            return false;
        }
        return users.stream().anyMatch(u -> u.getUserName().equalsIgnoreCase(username));
    }

    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        User user = getUserById(userId);
        if (user == null) return false;

        if (!PasswordUtil.checkPassword(oldPassword, user.getPassword())) {
            return false;
        }

        user.setPassword(PasswordUtil.hashPassword(newPassword));
        save();
        return true;
    }

    public User getUserById(String userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    private void save() {
        storage.writeToFile(AppConfig.USER_FILE, users);
        System.out.println("Lưu: " + users);
    }
}
