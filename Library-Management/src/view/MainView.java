package view;

import model.*;
import controller.BookController;
import java.time.LocalDate;

public class MainView {
    public static void main(String[] args) {
        BookController bookController = new BookController();
        addSampleBooks(bookController);

        AuthView authView = new AuthView();
        while (true) {
            User currentUser = authView.loginMenu();
            if (currentUser == null) continue;

            if (currentUser instanceof Admin) {
                new AdminMenuView().showMenu();
            } else if (currentUser instanceof Librarian) {
                new LibrarianMenuView().showMenu();
            } else if (currentUser instanceof Borrower) {
                new UserMenuView(currentUser).showMenu();
            } else {
                System.out.println("Không xác định được vai trò người dùng.");
            }
        }
    }

    private static void addSampleBooks(BookController bookController) {
        // Sách 1: Dragon Ball
        Author author1 = new Author("Akira Toriyama", "Nhật Bản");
        Category category1 = new Category("Manga", "Truyện tranh Nhật Bản", 10);
        LocalDate date1 = LocalDate.of(1984, 11, 20);
        Book book1 = new Book("DB001", "Dragon Ball", author1, "Shueisha", date1, category1, "Tiếng Nhật", 1000, 750, "Cuộc phiêu lưu của Son Goku và những người bạn trong hành trình tìm kiếm Ngọc Rồng.");

        // Sách 2: One Piece
        Author author2 = new Author("Eiichiro Oda", "Nhật Bản");
        Category category2 = new Category("Manga", "Truyện tranh Nhật Bản", 13);
        LocalDate date2 = LocalDate.of(1997, 7, 22);
        Book book2 = new Book("OP001", "One Piece", author2, "Shueisha", date2, category2, "Tiếng Nhật", 1500, 1200, "Hành trình trở thành Vua Hải Tặc của Monkey D. Luffy và băng hải tặc Mũ Rơm.");

        // Sách 3: Sherlock Holmes (tập hợp truyện)
        Author author3 = new Author("Arthur Conan Doyle", "Anh");
        Category category3 = new Category("Trinh thám", "Truyện bí ẩn", 14);
        LocalDate date3 = LocalDate.of(1887, 1, 1);
        Book book3 = new Book("SH001", "Sherlock Holmes", author3, "Ward Lock & Co.", date3, category3, "Tiếng Anh", 200, 150, "Những vụ án ly kỳ được giải quyết bởi thám tử tài ba Sherlock Holmes và người bạn đồng hành Dr. Watson.");

        // Sách 4: Lão Hạc
        Author author4 = new Author("Nam Cao", "Việt Nam");
        Category category4 = new Category("Văn học Việt Nam", "Truyện ngắn hiện thực", 16);
        LocalDate date4 = LocalDate.of(1943, 1, 1);
        Book book4 = new Book("LH001", "Lão Hạc", author4, "NXB Đời Nay", date4, category4, "Tiếng Việt", 150, 100, "Câu chuyện cảm động về số phận bi thảm của một người nông dân nghèo khổ.");

        // Sách 5: Những người khốn khổ
        Author author5 = new Author("Victor Hugo", "Pháp");
        Category category5 = new Category("Tiểu thuyết", "Văn học kinh điển", 16);
        LocalDate date5 = LocalDate.of(1862, 1, 1);
        Book book5 = new Book("NKH01", "Những người khốn khổ", author5, "Albert Lacroix et Cie", date5, category5, "Tiếng Pháp", 300, 200, "Câu chuyện vĩ đại về Jean Valjean và những số phận con người trong xã hội Pháp thế kỷ 19.");

        bookController.addBook(book1);
        bookController.addBook(book2);
        bookController.addBook(book3);
        bookController.addBook(book4);
        bookController.addBook(book5);
    }
}

