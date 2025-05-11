package storage;

import service.IDataStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenericFileStorage<T> implements IDataStorage<T> {

    @Override
    @SuppressWarnings("unchecked")
    public List<T> readFromFile(String path) {
        File file = new File(path);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading file: " + path);
            return new ArrayList<>();
        }
    }

    @Override
    public void writeToFile(String path, List<T> data) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(data);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + path);
            e.printStackTrace();
        }
    }


}