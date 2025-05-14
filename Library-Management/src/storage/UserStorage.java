package storage;

import model.User;
import service.interfaces.IDataStorage;

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
            try {
                new File(path).createNewFile();  // Tạo tệp mới nếu không tồn tại
            } catch (IOException ioException) {
                System.out.println("Error creating new file: " + ioException.getMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void writeToFile(String path, List<User> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(data);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
