package controller;

import model.Author;
import model.Book;
import model.Category;
import service.entries.BookService;
import service.interfaces.IBookService;

import java.time.LocalDate;
import java.util.List;

public class BookController {
    private final IBookService bookService = new BookService();

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public boolean removeBook(String id) {
        return bookService.removeBook(id);
    }

    public Book findBookById(String id) {
        return bookService.findById(id);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookService.searchByTitle(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return bookService.searchByAuthor(author);
    }

    public List<Book> searchBooksByCategory(String category) {
        return bookService.searchByCategory(category);
    }

    public List<Book> searchBooksByLanguage(String language) {
        return bookService.searchByLanguage(language);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public void updateBookTitle(String bookId, String title) {
        bookService.updateBookTitle(bookId, title);
    }

    public void updateBookAuthor(String bookId, Author author) {
        bookService.updateBookAuthor(bookId, author);
    }

    public void updateBookCategory(String bookId, Category category) {
        bookService.updateBookCategory(bookId, category);
    }

    public void updateBookLanguage(String bookId, String language) {
        bookService.updateBookLanguage(bookId, language);
    }

    public void updateBookPublishDate(String bookId, LocalDate publishDate) {
        bookService.updateBookPublishDate(bookId, publishDate);
    }

    public void updateBookDescription(String bookId, String description) {
        bookService.updateBookDescription(bookId, description);
    }

    public void updateBookTotalCopies(String bookId, int totalCopies) {
        bookService.updateBookTotalCopies(bookId, totalCopies);
    }

    public void updateBookAvailableCopies(String bookId, int availableCopies) {
        bookService.updateBookAvailableCopies(bookId, availableCopies);
    }

    public void updateBookCopies(String bookId, int delta) {
        bookService.updateBookCopies(bookId, delta);
    }
}
