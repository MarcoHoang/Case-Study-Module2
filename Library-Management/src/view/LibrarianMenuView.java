package view;

import controller.BookController;
import controller.RatingController;
import controller.ReservationController;
import controller.UserController;
import model.Borrower;
import model.Librarian;
import model.User;
import service.RatingService;
import service.ReservationService;

import java.util.Scanner;

public class LibrarianMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final BookController bookController = new BookController();
    private final UserController userController = new UserController();
    private final RatingController ratingController = new RatingController(RatingService.getInstance(), null);
    private final ReservationController reservationController = new ReservationController(ReservationService.getInstance(), null);

    public LibrarianMenuView() {
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- MENU QUẢN TRỊ ---");
            System.out.println("1. Xem danh sách sách");
            System.out.println("2. Thêm sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm kiếm sách");
            System.out.println("6. Xem danh sách người dùng");
            System.out.println("7. Xóa người dùng");
            System.out.println("8. Xem đánh giá");
            System.out.println("9. Xem danh sách sách cho mượn");
            System.out.println("10. Đăng xuất");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> bookController.getAllBooks().forEach(System.out::println);
                case "2" -> BookView.addBook(bookController);
                case "3" -> BookView.updateBook(bookController);
                case "4" -> BookView.removeBook(bookController);
                case "5" -> BookView.searchBooks(bookController);
                case "6" -> {
                    var users = userController.getAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("Không có người dùng nào.");
                    } else {
                        System.out.println("---- Danh sách ADMIN (Librarian) ----");
                        users.stream()
                                .filter(user -> user instanceof Librarian)
                                .forEach(System.out::println);

                        System.out.println("---- Danh sách USER (Borrower) ----");
                        users.stream()
                                .filter(user -> user instanceof Borrower)
                                .forEach(System.out::println);
                    }
                }
                case "7" -> {
                    System.out.print("Nhập ID người dùng cần xóa: ");
                    String userId = scanner.nextLine();
                    User user = userController.getUserById(userId);

                    if (user == null) {
                        System.out.println("Không tìm thấy người dùng với ID đã nhập.");
                    } else if (user instanceof Librarian) {
                        System.out.println("Không thể xóa tài khoản quản trị (Librarian).");
                    } else if (user instanceof Borrower) {
                        boolean deleted = userController.deleteUser(userId);
                        if (deleted) {
                            System.out.println("Đã xóa người dùng.");
                        } else {
                            System.out.println("Xóa người dùng thất bại.");
                        }
                    } else {
                        System.out.println("Không rõ loại người dùng, không thể xóa.");
                    }
                }
                case "8" -> ratingController.viewRatings();
                case "9" -> reservationController.viewReservations();
                case "10" -> {
                    System.out.println("Đăng xuất...");
                    return;
                }
                default -> System.out.println("Không hợp lệ.");
            }
        }
    }
}
