package view;

import controller.BookController;

import java.util.Scanner;

public class AdminMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final BookController bookController = new BookController();

    public AdminMenuView() {
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- MENU QUẢN TRỊ ---");
            System.out.println("1. Xem danh sách sách");
            System.out.println("2. Thêm sách");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm kiếm sách");
            System.out.println("6. Đăng xuất");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> bookController.getAllBooks().forEach(System.out::println);
                case "2" -> BookView.addBook(bookController);
                case "3" -> BookView.updateBook(bookController);
                case "4" -> BookView.removeBook(bookController);
                case "5" -> BookView.searchBooks(bookController);
                case "6" -> {
                    System.out.println("Đăng xuất...");
                    return;
                }
                default -> System.out.println("Không hợp lệ.");
            }
        }
    }
}
