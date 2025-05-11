package view;

import controller.BookController;
import controller.BorrowController;
import controller.ReservationController;
import controller.UserController;
import model.*;
import util.InputHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addBook(BookController controller) {
        Book book = inputBookData(InputHelper.inputString("ID sách: "));
        controller.addBook(book);
        System.out.println("Thêm sách thành công.");
    }

    public static void updateBook(BookController controller) {
        String bookId = InputHelper.inputString("ID sách cần cập nhật: ");
        Book existing = controller.findBookById(bookId);
        if (existing == null) {
            System.out.println("Không tìm thấy sách.");
            return;
        }
        Book book = inputBookData(bookId);
        controller.updateBook(book);
        System.out.println("Cập nhật thành công.");
    }

    public static void removeBook(BookController controller) {
        String bookId = InputHelper.inputString("ID sách cần xóa: ");
        if (!InputHelper.inputString("Xác nhận xóa? (Y/N): ").equalsIgnoreCase("Y")) return;
        if (controller.removeBook(bookId)) {
            System.out.println("Xóa thành công.");
        } else {
            System.out.println("Xóa thất bại.");
        }
    }

    public static void searchBooks(BookController controller) {
        System.out.println("Tìm theo: 1. Tác giả 2. Thể loại 3. Ngôn ngữ 4. Tiêu đề");
        String opt = scanner.nextLine();
        System.out.print("Nhập từ khóa: ");
        String kw = scanner.nextLine();
        List<Book> results = switch (opt) {
            case "1" -> controller.searchBooksByAuthor(kw);
            case "2" -> controller.searchBooksByCategory(kw);
            case "3" -> controller.searchBooksByLanguage(kw);
            case "4" -> controller.searchBooksByTitle(kw);
            default -> List.of();
        };
        if (results.isEmpty()) System.out.println("Không tìm thấy.");
        else results.forEach(System.out::println);
    }

    public static void borrowBook(User user, BorrowController controller) {
        String borrowId = InputHelper.inputString("Mã mượn: ");
        String bookId = InputHelper.inputString("ID sách: ");
        LocalDate borrowDate = InputHelper.inputDate("Ngày mượn (yyyy-MM-dd): ");
        LocalDate dueDate = InputHelper.inputDate("Ngày hết hạn (yyyy-MM-dd): ");

        BorrowRecord record = new BorrowRecord(borrowId, user.getId(), bookId, borrowDate, dueDate, false);
        controller.borrowBook(record);
        System.out.println("Mượn sách thành công.");
    }

    public static void returnBook(BorrowController controller) {
        String borrowId = InputHelper.inputString("ID mượn sách: ");
        if (controller.returnBook(borrowId)) {
            System.out.println("Trả sách thành công.");
        } else {
            System.out.println("Không tìm thấy bản ghi hoặc đã trả.");
        }
    }

    public static void reserveBook(User user, BookController controller, ReservationController resController) {
        String bookId = InputHelper.inputString("ID sách muốn đặt: ");
        Book book = controller.findBookById(bookId);
        if (book == null) {
            System.out.println("Không tìm thấy sách.");
            return;
        }
        if (book.getAvailableCopies() > 0) {
            System.out.println("Sách còn bản có sẵn, không cần đặt trước.");
        } else {
            resController.makeReservation();
            System.out.println("Đặt trước thành công.");
        }
    }

    public static void showMyBorrowedBooks(User user, BorrowController controller) {
        List<BorrowRecord> records = controller.getBorrowRecordsByUser(user.getId());
        if (records.isEmpty()) System.out.println("Bạn chưa mượn sách nào.");
        else records.forEach(System.out::println);
    }

    private static Book inputBookData(String id) {
        String title = InputHelper.inputString("Tiêu đề: ");
        String authorName = InputHelper.inputString("Tên tác giả: ");
        String authorNationality = InputHelper.inputString("Quốc tịch: ");
        String publisher = InputHelper.inputString("NXB: ");
        LocalDate pubDate = InputHelper.inputDate("Ngày xuất bản (yyyy-MM-dd): ");
        String categoryName = InputHelper.inputString("Tên thể loại: ");
        String categoryDesc = InputHelper.inputString("Mô tả thể loại: ");
        int minAge = InputHelper.inputInt("Độ tuổi tối thiểu: ");
        String language = InputHelper.inputString("Ngôn ngữ: ");
        int total = InputHelper.inputInt("Tổng số bản: ");
        int available = InputHelper.inputInt("Số bản còn lại: ");
        String desc = InputHelper.inputString("Mô tả sách: ");

        Author author = new Author(authorName, authorNationality);
        Category category = new Category(categoryName, categoryDesc, minAge);

        return new Book(id, title, author, publisher, pubDate, category, language, total, available, desc);
    }
}
