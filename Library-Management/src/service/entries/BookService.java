package service.entries;

import model.*;
import service.interfaces.IBookService;
import storage.BookStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService implements IBookService {
    private final BookStorage storage = new BookStorage();
    private List<Book> books;
    private final ReservationService reservationService;
    private final BorrowService borrowService;

    public BookService() {
        this.borrowService = BorrowService.getInstance();
        this.reservationService = ReservationService.getInstance();
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

    public void updateBookTitle(String bookId, String title) {
        Book book = findById(bookId);
        if (book != null) {
            book.setTitle(title);
            save();
            System.out.println("Cập nhật tiêu đề thành công!");
        }
    }

    public void updateBookAuthor(String bookId, Author author) {
        Book book = findById(bookId);
        if (book != null) {
            book.setAuthor(author);
            save();
            System.out.println("Cập nhật tác giả thành công!");
        }
    }

    public void updateBookCategory(String bookId, Category category) {
        Book book = findById(bookId);
        if (book != null) {
            book.setCategory(category);
            save();
            System.out.println("Cập nhật thể loại thành công!");
        }
    }

    public void updateBookLanguage(String bookId, String language) {
        Book book = findById(bookId);
        if (book != null) {
            book.setLanguage(language);
            save();
            System.out.println("Cập nhật ngôn ngữ thành công!");
        }
    }

    public void updateBookPublishDate(String bookId, LocalDate publishDate) {
        Book book = findById(bookId);
        if (book != null) {
            book.setPublishDate(publishDate);
            save();
            System.out.println("Cập nhật ngày xuất bản thành công!");
        }
    }

    public void updateBookDescription(String bookId, String description) {
        Book book = findById(bookId);
        if (book != null) {
            book.setDescription(description);
            save();
            System.out.println("Cập nhật mô tả thành công!");
        }
    }

    public void updateBookTotalCopies(String bookId, int totalCopies) {
        Book book = findById(bookId);
        if (book != null) {
            if (totalCopies < book.getAvailableCopies()) {
                System.out.println("Lỗi: Tổng số lượng không được nhỏ hơn số sách còn lại.");
                return;
            }
            book.setTotalCopies(totalCopies);
            save();
            System.out.println("Cập nhật tổng số lượng thành công!");
        }
    }

    public void updateBookAvailableCopies(String bookId, int availableCopies) {
        Book book = findById(bookId);
        if (book != null) {
            int oldAvailableCopies = book.getAvailableCopies();
            if (availableCopies > book.getTotalCopies()) {
                System.out.println("Lỗi: Sách còn lại không được vượt quá tổng số lượng.");
                return;
            }
            book.setAvailableCopies(availableCopies);
            save();

            System.out.println("Cập nhật số lượng sách ID " + bookId + " thành " + availableCopies + ". Số lượng cũ: " + oldAvailableCopies);
            if (oldAvailableCopies == 0 && availableCopies > 0) {
                List<Reservation> pendingReservations = reservationService.getPendingReservationsForBook(bookId);

                for (Reservation reservation : pendingReservations) {
                    if (book.getAvailableCopies() > 0) {
                        reservationService.updateReservationStatus(reservation.getBookId(), reservation.getBorrowerId(),
                                ReservationStatus.COMPLETED);

                        LocalDate borrowDate = LocalDate.now();
                        LocalDate dueDate = borrowDate.plusDays(7);
                        String borrowId = borrowService.generateBorrowId(reservation.getBorrowerId(),
                                reservation.getBookId());
                        BorrowRecord borrowRecord = new BorrowRecord(borrowId, reservation.getBookId(),
                                reservation.getBorrowerId(), borrowDate, dueDate);
                        borrowService.borrowBook(borrowRecord);

                        updateBookCopies(bookId, -1);
                        System.out.println("Đã tự động chuyển đặt trước sang mượn thành công!");
                    }
                }
            }
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

    @Override
    public void updateBookCopies(String bookId, int delta) {
        Book book = findById(bookId);
        if (book != null) {
            int newAvailable = book.getAvailableCopies() + delta;

            if (newAvailable < 0) {
                System.out.println("Lỗi: Số lượng sách còn lại không đủ.");
                return;
            }

            book.setAvailableCopies(newAvailable);
            save();
        } else {
            System.out.println("Không tìm thấy sách với ID: " + bookId);
        }
    }

    @Override
    public void save() {
        storage.writeToFile(config.AppConfig.BOOK_FILE, books);
    }
}
