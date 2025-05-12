package view;

import controller.LoginController;
import controller.UserController;
import model.User;
import service.ILoginService;
import service.LoginService;
import service.UserService;

import java.util.Scanner;

public class AuthView {
    private static final Scanner scanner = new Scanner(System.in);
    private final LoginController loginController;

    public AuthView() {
        final UserService userService = UserService.getInstance();
        final UserController userController = new UserController();
        final ILoginService loginService = new LoginService(userService);
        this.loginController = new LoginController(loginService, userController);
    }

    public User loginMenu() {
        while (true) {
            System.out.println("\n--- THƯ VIỆN SÁCH ---");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    User user = loginController.login();
                    if (user != null) return user;
                }
                case "2" -> loginController.register();
                case "0" -> {
                    System.out.println("Tạm biệt!");
                    System.exit(0);
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}