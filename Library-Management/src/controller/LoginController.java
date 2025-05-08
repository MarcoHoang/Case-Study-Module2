package controller;

import model.User;
import model.Librarian;
import model.Borrower;
import service.IUserService;
import service.UserService;

import java.util.Scanner;

public class LoginController {
    private final IUserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);

    public User login() {
        System.out.print("Tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();

        User user = userService.login(username, password);
        if (user != null) {
            System.out.println("Đăng nhập thành công!");
            if (user instanceof Librarian) {
                System.out.println("Xin chào thủ thư " + user.getUserName());
            } else if (user instanceof Borrower) {
                System.out.println("Xin chào bạn đọc " + user.getUserName());
            }
        } else {
            System.out.println("Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.");
        }
        return user;
    }

    public void register() {
        System.out.println("=== ĐĂNG KÝ NGƯỜI DÙNG ===");
        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Tên đăng nhập: ");
        String username = scanner.nextLine();

        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Số điện thoại: ");
        String phone = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Vai trò (1 - Thủ thư, 2 - Bạn đọc): ");
        int role = Integer.parseInt(scanner.nextLine());

        User newUser;
        if (role == 1) {
            newUser = new Librarian(id, username, password, email, phone, age, gender);
        } else {
            System.out.print("Borrower Type: ");
            String borrowerType = scanner.nextLine();
            newUser = new Borrower(id, username, password, email, phone, age, gender, borrowerType);
        }

        userService.register(newUser);
        System.out.println("Đăng ký thành công!");
    }
}
