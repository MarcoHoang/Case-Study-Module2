package service;

import model.Author;
import model.Book;
import model.Category;
import storage.BookStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
        if (findById(book.getId()) != null) {
            System.out.println("Lỗi: ID sách đã tồn tại. Không thể thêm sách.");
            return;
        }

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
    public void updateBook(String bookId, int updateChoice, String newValue) {
        Scanner scanner = new Scanner(System.in);
        Book book = findById(bookId);
        if (book != null) {
            switch (updateChoice) {
                case 1:
                    book.setTitle(newValue);
                    break;
                case 2:
                    System.out.print("Nhập tên tác giả mới: ");
                    System.out.print("Nhập quốc tịch tác giả mới: ");
                    String authorNationality = scanner.nextLine();
                    Author newAuthor = new Author(newValue, authorNationality);
                    book.setAuthor(newAuthor);
                    break;
                case 3:
                    System.out.print("Nhập tên thể loại mới: ");
                    System.out.print("Nhập mô tả thể loại mới: ");
                    String categoryDescription = scanner.nextLine();
                    System.out.print("Nhập độ tuổi tối thiểu cho thể loại: ");
                    int minAge = scanner.nextInt();
                    Category newCategory = new Category(newValue, categoryDescription, minAge);
                    book.setCategory(newCategory);
                    break;
                case 4:
                    book.setLanguage(newValue);
                    break;
                case 5:
                    try {
                        System.out.print("Nhập ngày xuất bản (yyyy-MM-dd): ");
                        LocalDate publishDate = LocalDate.parse(newValue);
                        book.setPublishDate(publishDate);
                    } catch (Exception e) {
                        System.out.println("Lỗi: Định dạng ngày tháng không hợp lệ.");
                    }
                    break;
                case 6:
                    book.setDescription(newValue);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    return;
            }
            save();
            System.out.println("Cập nhật sách thành công!");
        } else {
            System.out.println("Không tìm thấy sách với ID: " + bookId);
        }
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
                .filter(b -> b.getAuthor().getName().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchByCategory(String category) {
        return books.stream()
                .filter(b -> b.getCategory().getName().toLowerCase().contains(category.toLowerCase()))
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

    private void save() {
        storage.writeToFile(config.AppConfig.BOOK_FILE, books);
    }
}
