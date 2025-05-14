package controller;

import model.Rating;
import model.User;
import service.entries.RatingService;
import util.InputHelper;

public class RatingController {
    private final RatingService ratingService;
    private final User currentUser;

    public RatingController(RatingService ratingService, User currentUser) {
        this.ratingService = ratingService;
        this.currentUser = currentUser;
    }

    public void addRating() {
        System.out.println("\n--- Thêm Đánh Giá Sách ---");

        String bookId = InputHelper.inputNonEmptyString("Nhập ID sách: ");

        int stars = InputHelper.inputIntInRange("Nhập số sao (1-5): ", 1, 5);

        String comment = InputHelper.inputString("Nhập nhận xét: ");

        Rating rating = new Rating(bookId, currentUser.getId(), stars, comment);
        ratingService.addRating(rating);
        System.out.println("Đánh giá đã được thêm thành công.");
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
