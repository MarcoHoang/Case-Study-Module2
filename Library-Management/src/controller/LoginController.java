package controller;

import model.*;
import service.interfaces.ILoginService;
import util.AccountHelper;
import util.InputHelper;

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
            if (user instanceof Admin) {
                System.out.println("Đăng nhập thành công! Bạn là Admin.");
            } else if (loginService.isLibrarian(user)) {
                System.out.println("Đăng nhập thành công! Bạn là Thủ thư.");
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
        AccountHelper.AccountInfo accountInfo = AccountHelper.getAccountInfo("người dùng");

        String username = accountInfo.username;
        while (true) {
            if (userController.findByUsername(username) != null) {
                System.out.println("Lỗi: Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
                accountInfo.username = InputHelper.inputNonEmptyString("Tên đăng nhập: ");
                username = accountInfo.username;
            } else {
                break;
            }
        }

        String borrowerType = InputHelper.inputNonEmptyString("Loại bạn đọc (ví dụ: sinh viên, giảng viên...): ");
        Borrower newUser = new Borrower(accountInfo.id, accountInfo.username, accountInfo.hashedPassword, accountInfo.email, accountInfo.phone, accountInfo.age, accountInfo.gender, borrowerType);

        userController.register(newUser);
        System.out.println("Đăng ký thành công! Bạn đã trở thành Người dùng.");
    }
}