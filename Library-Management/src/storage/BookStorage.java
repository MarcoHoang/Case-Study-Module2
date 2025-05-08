package storage;

import model.Book;
import service.IDataStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookStorage implements IDataStorage<Book> {

    @Override
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

    public void addBook(String path, Book book) {
        List<Book> books = readFromFile(path);
        books.add(book);
        writeToFile(path, books);
    }

    public void removeBook(String path, String bookId) {
        List<Book> books = readFromFile(path);
        books.removeIf(book -> book.getId().equals(bookId));
        writeToFile(path, books);
    }

    public void updateBook(String path, Book updatedBook) {
        List<Book> books = readFromFile(path);
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(updatedBook.getId())) {
                books.set(i, updatedBook);
                break;
            }
        }
        writeToFile(path, books);
    }

    public Book findBookById(String path, String bookId) {
        List<Book> books = readFromFile(path);
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBooksByTitle(String path, String title) {
        List<Book> books = readFromFile(path);
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByAuthor(String path, String author) {
        List<Book> books = readFromFile(path);
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByCategory(String path, String category) {
        List<Book> books = readFromFile(path);
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().toLowerCase().contains(category.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByLanguage(String path, String language) {
        List<Book> books = readFromFile(path);
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getLanguage().toLowerCase().contains(language.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooks(String path) {
        return readFromFile(path);
    }
}
