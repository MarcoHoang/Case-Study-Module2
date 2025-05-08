package service;

import config.AppConfig;
import model.Borrower;
import model.User;
import storage.GenericFileStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private final GenericFileStorage<User> storage = new GenericFileStorage<>();
    private List<User> users;

    public UserService() {
        users = storage.readFromFile(AppConfig.USER_FILE);
        if (users == null) users = new ArrayList<>();
    }

    @Override
    public User login(String username, String password) {
        List<User> users = storage.readFromFile("users.dat");
        return users.stream().filter(u -> u.getUserName().equals(username) && u.getPassword().equals(password)).findFirst().orElse(null);
    }

    @Override
    public void register(User user) {
        List<User> users = storage.readFromFile("users.dat");
        users.add(user);
        storage.writeToFile("users.dat", users);
    }

    @Override
    public boolean deleteUser(String userId) {
        List<User> users = storage.readFromFile("users.dat");
        boolean removed = users.removeIf(u -> u.getId().equals(userId));
        if (removed) storage.writeToFile("users.dat", users);
        return removed;
    }

    @Override
    public List<User> getAllUsers() {
        return storage.readFromFile(AppConfig.USER_FILE);
    }

    private void save() {
        storage.writeToFile(AppConfig.USER_FILE, users);
    }
}