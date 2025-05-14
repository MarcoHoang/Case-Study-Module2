package view;

import controller.*;
import model.User;
import service.RatingService;
import service.ReservationService;
import util.InputHelper;

import java.util.Scanner;

public class UserMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final User currentUser;
    private final BookController bookController = new BookController();
    private final BorrowController borrowController = new BorrowController();
    private final ReservationController reservationController;
    private final RatingController ratingController;
    private final UserController userController;

    public UserMenuView(User user) {
        this.currentUser = user;
        this.ratingController = new RatingController(RatingService.getInstance(), currentUser);
        this.userController = new UserController();
        this.reservationController = new ReservationController(ReservationService.getInstance(), currentUser);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- MENU NGƯỜI DÙNG ---");
            System.out.println("1. Xem danh sách sách");
            System.out.println("2. Mượn sách");
            System.out.println("3. Trả sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Xem sách đã mượn");
            System.out.println("6. Đánh giá sách");
            System.out.println("7. Đặt trước sách");
            System.out.println("8. Đổi mật khẩu");
            System.out.println("9. Xem đánh giá");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> bookController.getAllBooks().forEach(System.out::println);
                case "2" -> BookView.borrowBook(currentUser, borrowController, bookController);
                case "3" -> BookView.returnBook(borrowController, bookController);
                case "4" -> BookView.searchBooks(bookController);
                case "5" -> BookView.showMyBorrowedBooks(currentUser, borrowController);
                case "6" -> ratingController.addRating();
                case "7" -> BookView.reserveBook(bookController, reservationController);
                case "8" -> changePassword();
                case "9" -> ratingController.viewRatings();
                case "0" -> {
                    System.out.println("Đăng xuất...");
                    return;
                }
                default -> System.out.println("Không hợp lệ.");
            }
        }
    }

    private void changePassword() {
        String oldPwd;

        while (true) {
            oldPwd = InputHelper.inputString("Mật khẩu cũ: ");
            if (userController.verifyOldPassword(currentUser.getId(), oldPwd)) {
                break;
            } else {
                System.out.println("Lỗi: Mật khẩu cũ không đúng. Vui lòng thử lại.");
            }
        }

        String newPwd;
        String confirm;

        while (true) {
            newPwd = InputHelper.inputString("Mật khẩu mới: ");
            confirm = InputHelper.inputString("Xác nhận mật khẩu: ");

            if (!newPwd.equals(confirm)) {
                System.out.println("Lỗi: Mật khẩu mới và xác nhận mật khẩu không trùng khớp. Vui lòng nhập lại.");
                continue;
            }

            boolean ok = userController.changePassword(currentUser.getId(), oldPwd, newPwd);
            if (ok) {
                System.out.println("Đổi mật khẩu thành công.");
                break;
            }
        }
    }
}