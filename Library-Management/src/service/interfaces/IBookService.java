package service.interfaces;

import model.Author;
import model.Book;
import model.Category;

import java.time.LocalDate;
import java.util.List;

public interface IBookService {
    void addBook(Book book);
    boolean removeBook(String id);
    Book findById(String id);
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> searchByCategory(String category);
    List<Book> searchByLanguage(String language);
    void updateBookCopies(String bookId, int delta);
    void updateBookTitle(String bookId, String title);
    void updateBookAuthor(String bookId, Author author);
    void updateBookCategory(String bookId, Category category);
    void updateBookLanguage(String bookId, String language);
    void updateBookPublishDate(String bookId, LocalDate publishDate);
    void updateBookDescription(String bookId, String description);
    void updateBookTotalCopies(String bookId, int totalCopies);
    void updateBookAvailableCopies(String bookId, int availableCopies);
    List<Book> getAllBooks();
    void save();
}
