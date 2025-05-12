package controller;

import model.Borrower;
import model.Gender;
import model.Librarian;
import model.User;
import service.IUserService;
import service.UserService;

import java.util.List;

public class AdminController {
    private final IUserService userService;

    public AdminController() {
        this.userService = UserService.getInstance();
    }

    public IUserService getUserService() {
        return userService;
    }

    public void createLibrarianAccount(String id, String username, String password, String email, String phone, int age, String genderInput) {
        if (userService.findByUsername(username) != null && userService.getUserById(id) != null) {
            System.out.println("Lỗi: Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
            return;
        }
        Librarian newLibrarian = new Librarian(id, username, password, email, phone, age, Gender.valueOf(genderInput.toUpperCase()));
        userService.register(newLibrarian);
        System.out.println("Tạo tài khoản Thủ thư thành công!");
    }

    public boolean deleteLibrarianAccount(String userId) {
        User userToDelete = userService.getUserById(userId);

        if (userToDelete == null) {
            System.out.println("Lỗi: Không tìm thấy người dùng với ID: " + userId);
            return false;
        }

        if (userToDelete instanceof Librarian) {
            return userService.deleteUser(userId);
        } else {
            System.out.println("Lỗi: Đây không phải tài khoản thủ thư!");
            return false;
        }

    }

    public List<User> getAllLibrarians() {
        return userService.getAllUsers().stream()
                .filter(user -> user instanceof Librarian)
                .toList();
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers().stream()
                .filter(user -> user instanceof Borrower)
                .toList();
    }
}
