package view;

import controller.AdminController;
import controller.BookController;
import controller.BorrowController;
import controller.RatingController;
import controller.ReservationController;
import model.User;
import service.entries.RatingService;
import service.entries.ReservationService;
import util.AccountHelper;
import util.InputHelper;

import java.util.List;
import java.util.Scanner;

public class AdminMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final BookController bookController = new BookController();
    private final AdminController adminController = new AdminController();
    private final RatingController ratingController = new RatingController(RatingService.getInstance(), null);
    private final BorrowController borrowController = new BorrowController();
    private final ReservationController reservationController = new ReservationController(ReservationService.getInstance(), null);

    public void showMenu() {
        while (true) {
            System.out.println("\n--- MENU QUẢN LÝ ADMIN ---");
            System.out.println("1. Xem danh sách sách");
            System.out.println("2. Thêm sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm kiếm sách");
            System.out.println("6. Quản lý thủ thư");
            System.out.println("7. Quản lý người dùng");
            System.out.println("8. Tạo tài khoản Thủ thư");
            System.out.println("9. Xóa tài khoản Thủ thư");
            System.out.println("10. Xem đánh giá");
            System.out.println("11. Xem sách đã cho mượn");
            System.out.println("12. Xem sách đã đặt trước");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn chức năng: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> bookController.getAllBooks().forEach(System.out::println);
                case "2" -> BookView.addBook(bookController);
                case "3" -> BookView.updateBook(bookController);
                case "4" -> BookView.removeBook(bookController);
                case "5" -> BookView.searchBooks(bookController);
                case "6" -> manageLibrarians();
                case "7" -> manageUsers();
                case "8" -> createLibrarianAccount();
                case "9" -> deleteLibrarianAccount();
                case "10" -> ratingController.viewRatings();
                case "11" -> BorrowView.viewBorrowedBooks(borrowController);
                case "12" -> reservationController.viewReservations();
                case "0" -> {
                    System.out.println("Đăng xuất...");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private void manageLibrarians() {
        System.out.println("--- Danh sách Thủ thư ---");
        List<User> librarians = adminController.getAllLibrarians();
        if (librarians.isEmpty()) {
            System.out.println("Chưa có thủ thư nào.");
        } else {
            librarians.forEach(System.out::println);
        }
    }

    private void manageUsers() {
        System.out.println("--- Danh sách Người dùng ---");
        List<User> users = adminController.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Không có người dùng nào.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private void createLibrarianAccount() {
        System.out.println("--- Tạo tài khoản Thủ thư ---");
        AccountHelper.AccountInfo accountInfo = AccountHelper.getAccountInfo("thủ thư");

        String username = accountInfo.username;
        while (true) {
            if (adminController.getUserService().findByUsername(username) != null) {
                System.out.println("Lỗi: Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
                accountInfo.username = InputHelper.inputNonEmptyString("Tên đăng nhập: ");
                username = accountInfo.username;
            } else {
                break;
            }
        }

        adminController.createLibrarianAccount(accountInfo.id, accountInfo.username, accountInfo.hashedPassword, accountInfo.email, accountInfo.phone, accountInfo.age, accountInfo.gender.toString());
        System.out.println("Đã tạo tài khoản Thủ thư thành công.");
    }

    private void deleteLibrarianAccount() {
        System.out.print("Nhập ID thủ thư cần xóa: ");
        String userId = scanner.nextLine();
        boolean success = adminController.deleteLibrarianAccount(userId);
        if (success) {
            System.out.println("Đã xóa thủ thư thành công.");
        } else {
            System.out.println("Không thể xóa thủ thư.");
        }
    }
}