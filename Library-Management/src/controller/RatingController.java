package controller;

import model.Rating;
import model.User;
import service.RatingService;
import java.util.Scanner;

public class RatingController {
    private final RatingService ratingService;
    private final User currentUser;

    public RatingController(RatingService ratingService, User currentUser) {
        this.ratingService = ratingService;
        this.currentUser = currentUser;
    }

    public void addRating() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ID sách: ");
        String bookId = scanner.nextLine();

        System.out.print("Nhập số sao (1-5): ");
        int stars = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập nhận xét: ");
        String comment = scanner.nextLine();

        Rating rating = new Rating(bookId, currentUser.getId(), stars, comment);
        ratingService.addRating(rating);
        System.out.println("Đánh giá đã được thêm.");
    }


    public void viewRatings() {
        System.out.println("=== DANH SÁCH ĐÁNH GIÁ ===");
        ratingService.getAllRatings().forEach(rating -> System.out.println(
                "Sách ID: " + rating.getBookId() +
                        ", Người dùng ID: " + rating.getUserId() +
                        ", Số sao: " + rating.getStars() +
                        ", Nhận xét: " + rating.getComment()
        ));
    }
}
