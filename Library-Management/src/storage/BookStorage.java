package storage;

import model.Book;
import service.interfaces.IDataStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookStorage implements IDataStorage<Book> {

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> readFromFile(String path) {
        List<Book> books = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating new file...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void writeToFile(String path, List<Book> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
