package controller;

import model.Book;
import service.BookService;
import service.IBookService;

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

    public void updateBook(String bookId, int updateChoice, String newValue) {
        bookService.updateBook(bookId, updateChoice, newValue);
    }
}
