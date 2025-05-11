package view;

import model.*;
import controller.BookController;
import service.ReservationService;

import java.time.LocalDate;

public class MainView {
    public static void main(String[] args) {
        BookController bookController = new BookController();
        ReservationService reservationService = new ReservationService();
        addSampleBooks(bookController);

        AuthView authView = new AuthView();
        while (true) {
            User currentUser = authView.loginMenu();
            if (currentUser == null) continue;

            if (currentUser instanceof Librarian) {
                new AdminMenuView().showMenu();
            } else if (currentUser instanceof Borrower) {
                new UserMenuView(currentUser, reservationService).showMenu();
            } else {
                System.out.println("Không xác định được vai trò người dùng.");
            }
        }
    }

    private static void addSampleBooks(BookController bookController) {
        Book book1 = BookFactory.createBook("B001", "Sách A", "Tác giả A", "Nhà xuất bản A", "Thể loại A", "Tiếng Việt", 10, 8, "Việt Nam", 12);
        Book book2 = BookFactory.createBook("B002", "Sách B", "Tác giả B", "Nhà xuất bản B", "Thể loại B", "Tiếng Anh", 15, 12, "Anh", 15);
        Book book3 = BookFactory.createBook("B003", "Sách C", "Tác giả C", "Nhà xuất bản C", "Thể loại C", "Tiếng Việt", 20, 18, "Mỹ", 18);
        Book book4 = BookFactory.createBook("B004", "Sách D", "Tác giả D", "Nhà xuất bản D", "Thể loại D", "Tiếng Anh", 5, 3, "Canada", 20);
        Book book5 = BookFactory.createBook("B005", "Sách E", "Tác giả E", "Nhà xuất bản E", "Thể loại E", "Tiếng Việt", 25, 23, "Pháp", 10);

        bookController.addBook(book1);
        bookController.addBook(book2);
        bookController.addBook(book3);
        bookController.addBook(book4);
        bookController.addBook(book5);
    }
}

