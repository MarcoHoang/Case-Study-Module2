package view;

import controller.BookController;
import controller.BorrowController;
import controller.ReservationController;
import model.*;
import util.InputHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addBook(BookController controller) {
        Book book = inputBookData();
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

        System.out.println("Thông tin sách hiện tại: ");
        System.out.println(existing);

        int updateChoice;
        do {
            System.out.println("Chọn trường cần cập nhật:");
            System.out.println("1. Tiêu đề sách");
            System.out.println("2. Tác giả");
            System.out.println("3. Thể loại");
            System.out.println("4. Ngôn ngữ sách");
            System.out.println("5. Ngày xuất bản (yyyy-MM-dd)");
            System.out.println("6. Mô tả sách");
            System.out.println("7. Tổng số lượng sách");
            System.out.println("8. Số lượng sách còn lại");

            updateChoice = InputHelper.inputInt("Lựa chọn (1-8): ");
            if (updateChoice < 1 || updateChoice > 8) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (updateChoice < 1 || updateChoice > 8);

        boolean updated = false;
        while (!updated) {
            try {
                switch (updateChoice) {
                    case 1 -> {
                        String title = InputHelper.inputNonEmptyString("Tiêu đề mới: ");
                        controller.updateBookTitle(bookId, title);
                    }
                    case 2 -> {
                        String name = InputHelper.inputNonEmptyString("Tên tác giả mới: ");
                        String nationality = InputHelper.inputNonEmptyString("Quốc tịch tác giả: ");
                        controller.updateBookAuthor(bookId, new Author(name, nationality));
                    }
                    case 3 -> {
                        String categoryName = InputHelper.inputNonEmptyString("Tên thể loại: ");
                        String description = InputHelper.inputNonEmptyString("Mô tả thể loại: ");
                        int minAge = InputHelper.inputInt("Độ tuổi tối thiểu: ");
                        controller.updateBookCategory(bookId, new Category(categoryName, description, minAge));
                    }
                    case 4 -> {
                        String language = InputHelper.inputNonEmptyString("Ngôn ngữ mới: ");
                        controller.updateBookLanguage(bookId, language);
                    }
                    case 5 -> {
                        LocalDate publishDate = InputHelper.inputDate("Ngày xuất bản (yyyy-MM-dd): ");
                        controller.updateBookPublishDate(bookId, publishDate);
                    }
                    case 6 -> {
                        String desc = InputHelper.inputString("Mô tả mới: ");
                        controller.updateBookDescription(bookId, desc);
                    }
                    case 7 -> {
                        int total = InputHelper.inputInt("Tổng số lượng mới: ");
                        controller.updateBookTotalCopies(bookId, total);
                    }
                    case 8 -> {
                        int available = InputHelper.inputInt("Số bản còn lại mới: ");
                        controller.updateBookAvailableCopies(bookId, available);
                    }
                }
                updated = true;
                System.out.println("Cập nhật thành công.");
            } catch (Exception e) {
                System.out.println("Lỗi khi cập nhật: " + e.getMessage());
                System.out.println("Vui lòng nhập lại.");
            }
        }
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

    public static void borrowBook(User user, BorrowController borrowController, BookController bookController) {
        String bookId = inputBookId();
        Book book = bookController.findBookById(bookId);

        if (book == null) {
            System.out.println("Không tìm thấy sách.");
            return;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("Hiện tại sách đã hết. Không thể mượn.");
            return;
        }

        if (user.getAge() < book.getCategory().getMinAge()) {
            System.out.println("Bạn không đủ tuổi để mượn cuốn sách này.");
            return;
        }

        String borrowId = borrowController.generateBorrowId(user.getId(), bookId);
        System.out.println("Mã mượn sách của bạn là (BR_userId_bookId_BOOK): " + borrowId);

        LocalDate borrowDate = InputHelper.inputDate("Ngày mượn (yyyy-MM-dd): ");
        LocalDate dueDate = InputHelper.inputDate("Ngày hết hạn (yyyy-MM-dd): ");

        BorrowRecord record = new BorrowRecord(borrowId, user.getId(), bookId, borrowDate, dueDate);
        borrowController.borrowBook(record);

        bookController.updateBookCopies(bookId, -1);
        System.out.println("Mượn sách thành công.");
    }

    public static void returnBook(BorrowController controller, BookController bookController) {
        String borrowId = InputHelper.inputString("ID mượn sách: ");
        BorrowRecord record = controller.findBorrowRecordById(borrowId);

        if (record == null || record.isReturned()) {
            System.out.println("Không tìm thấy bản ghi hoặc sách đã được trả.");
            return;
        }

        boolean success = controller.returnBook(borrowId);
        if (success) {
            Book book = bookController.findBookById(record.getBookId());
            if (book != null) {
                bookController.updateBookCopies(record.getBookId(), 1);
            }
            System.out.println("Trả sách thành công.");
        } else {
            System.out.println("Trả sách thất bại.");
        }
    }

    public static void reserveBook(BookController controller, ReservationController resController) {
        String bookId = inputBookId();
        Book book = controller.findBookById(bookId);
        if (book == null) {
            System.out.println("Không tìm thấy sách.");
            return;
        }

        if (book.getAvailableCopies() > 0) {
            System.out.println("Sách còn bản có sẵn, không cần đặt trước.");
        } else {
            resController.makeReservation(bookId);
            System.out.println("Đặt trước sách thành công.");
        }
    }

    public static void showMyBorrowedBooks(User user, BorrowController controller) {
        List<BorrowRecord> records = controller.getBorrowRecordsByUser(user.getId());
        if (records.isEmpty()) System.out.println("Bạn chưa mượn sách nào.");
        else records.forEach(System.out::println);
    }

    private static String inputBookId() {
        return InputHelper.inputString("ID sách: ");
    }

    private static String inputBookTitle() {
        return InputHelper.inputString("Tiêu đề sách: ");
    }

    private static Author inputBookAuthor() {
        String authorName = InputHelper.inputString("Tên tác giả: ");
        String authorNationality = InputHelper.inputString("Quốc tịch tác giả: ");
        return new Author(authorName, authorNationality);
    }

    private static Category inputBookCategory() {
        String categoryName = InputHelper.inputString("Tên thể loại sách: ");
        String categoryDesc = InputHelper.inputString("Mô tả thể loại sách: ");
        int minAge = InputHelper.inputInt("Độ tuổi tối thiểu: ");
        return new Category(categoryName, categoryDesc, minAge);
    }

    private static String inputPublisher() {
        return InputHelper.inputString("NXB (Nhà xuất bản): ");
    }

    private static LocalDate inputPublishDate() {
        return InputHelper.inputDate("Ngày xuất bản (yyyy-MM-dd): ");
    }

    private static String inputLanguage() {
        return InputHelper.inputString("Ngôn ngữ sách: ");
    }

    private static int inputTotalCopies() {
        return InputHelper.inputInt("Tổng số bản sách: ");
    }

    private static int inputAvailableCopies() {
        return InputHelper.inputInt("Số bản còn lại: ");
    }

    private static String inputDescription() {
        return InputHelper.inputString("Mô tả sách: ");
    }

    private static Book inputBookData() {
        String id = inputBookId();
        String title = inputBookTitle();
        Author author = inputBookAuthor();
        String publisher = inputPublisher();
        LocalDate pubDate = inputPublishDate();
        Category category = inputBookCategory();
        String language = inputLanguage();
        int total = inputTotalCopies();
        int available = inputAvailableCopies();
        String desc = inputDescription();

        return new Book(id, title, author, publisher, pubDate, category, language, total, available, desc);
    }
}
