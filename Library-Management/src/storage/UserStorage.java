package storage;

import model.User;
import model.Librarian;
import model.Borrower;
import service.IDataStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorage implements IDataStorage<User> {

    @Override
    @SuppressWarnings("unchecked")
    public List<User> readFromFile(String path) {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating new file...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void writeToFile(String path, List<User> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String path, User user) {
        List<User> users = readFromFile(path);
        users.add(user);
        writeToFile(path, users);
    }

    public void removeUser(String path, String userId) {
        List<User> users = readFromFile(path);
        users.removeIf(user -> user.getId().equals(userId));
        writeToFile(path, users);
    }

    public User findUserById(String path, String userId) {
        List<User> users = readFromFile(path);
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByUsername(String path, String username) {
        List<User> users = readFromFile(path);
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers(String path) {
        return readFromFile(path);
    }
}
