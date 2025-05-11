package service;

import model.Book;
import java.util.List;

public interface IBookService {
    void addBook(Book book);
    boolean removeBook(String id);
    void updateBook(Book updatedBook);
    Book findById(String id);
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> searchByCategory(String category);
    List<Book> searchByLanguage(String language);
    List<Book> getAllBooks();
}
