package controller;

import model.Gender;
import model.User;
import model.Librarian;
import model.Borrower;
import service.ILoginService;
import util.InputHelper;
import util.PasswordUtil;

public class LoginController {
    private final ILoginService loginService;
    private final UserController userController;

    public LoginController(ILoginService loginService, UserController userController) {
        this.loginService = loginService;
        this.userController = userController;
    }

    public User login() {
        System.out.println("=== ĐĂNG NHẬP ===");
        String username = InputHelper.inputNonEmptyString("Tên đăng nhập: ");
        String password = InputHelper.inputNonEmptyString("Mật khẩu: ");

        User user = loginService.authenticate(username, password);
        if (user != null) {
            if (loginService.isLibrarian(user)) {
                System.out.println("Đăng nhập thành công! Bạn là Thủ thư (Admin).");
            } else {
                System.out.println("Đăng nhập thành công! Bạn là Người dùng.");
            }
        } else {
            System.out.println("Đăng nhập thất bại. Tên đăng nhập hoặc mật khẩu không đúng.");
        }
        return user;
    }

    public void register() {
        System.out.println("=== ĐĂNG KÝ NGƯỜI DÙNG ===");

        String id = InputHelper.inputNonEmptyString("ID: ");

        String username;
        while (true) {
            username = InputHelper.inputNonEmptyString("Tên đăng nhập: ");
            if (userController.findByUsername(username) != null) {
                System.out.println("Lỗi: Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
            } else {
                break;
            }
        }

        String password;
        while (true) {
            password = InputHelper.inputNonEmptyString("Mật khẩu: ");
            try {
                PasswordUtil.validatePassword(password);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
        String hashedPassword = PasswordUtil.hashPassword(password);

        String email = InputHelper.inputEmail("Email: ");
        String phone = InputHelper.inputPhone("Số điện thoại: ");
        int age = InputHelper.inputIntInRange("Tuổi: ", 1, 150);
        Gender gender = InputHelper.inputGender("Giới tính (Nam/Nữ/Khác): ");
        int role = InputHelper.inputIntInRange("Vai trò (1 - Thủ thư, 2 - Bạn đọc): ", 1, 2);

        User newUser;
        if (role == 1) {
            newUser = new Librarian(id, username, hashedPassword, email, phone, age, gender);
        } else {
            String borrowerType = InputHelper.inputNonEmptyString("Loại bạn đọc (ví dụ: sinh viên, giảng viên...): ");
            newUser = new Borrower(id, username, hashedPassword, email, phone, age, gender, borrowerType);
        }

        userController.register(newUser);
        System.out.println("Đăng ký thành công!");
    }
}