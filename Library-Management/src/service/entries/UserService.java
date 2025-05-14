package service.entries;

import model.Admin;
import model.Gender;
import model.User;
import service.interfaces.IUserService;
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
        createAdminAccount();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void register(User user) {
        if (findByUsername(user.getUserName()) != null && getUserById(user.getId()) != null) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
        }
        users.add(user);
        save();
    }

    @Override
    public boolean deleteUser(String userId) {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }

        boolean removed = users.remove(user);
        if (removed) {
            save();
        }
        return removed;
    }

    private void createAdminAccount() {
        boolean adminExists = users.stream().anyMatch(u -> u.getUserName().equals("admin"));
        if (!adminExists) {
            Admin admin = new Admin("ad", "admin", PasswordUtil.hashPassword("Admin123@"), "admin@example.com",
                    "0123456789", 30, Gender.MALE);
            users.add(admin);
            save();
            System.out.println("Tạo tài khoản Admin mặc định thành công!");
        }
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
    }
}
