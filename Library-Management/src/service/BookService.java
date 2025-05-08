package service;

import model.Book;
import storage.BookStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService implements IBookService {
    private final BookStorage storage = new BookStorage();
    private List<Book> books;

    public BookService() {
        books = storage.readFromFile(config.AppConfig.BOOK_FILE);
        if (books == null) books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
        save();
    }

    @Override
    public boolean removeBook(String id) {
        boolean removed = books.removeIf(b -> b.getId().equals(id));
        if (removed) save();
        return removed;
    }

    @Override
    public Book findById(String id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return books.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchByCategory(String category) {
        return books.stream()
                .filter(b -> b.getCategory().toLowerCase().contains(category.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchByLanguage(String language) {
        return books.stream()
                .filter(b -> b.getLanguage().toLowerCase().contains(language.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(updatedBook.getId())) {
                books.set(i, updatedBook);
                save();
                break;
            }
        }
    }

    private void save() {
        storage.writeToFile(config.AppConfig.BOOK_FILE, books);
    }
}
