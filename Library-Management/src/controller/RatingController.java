package controller;

import model.Rating;
import service.RatingService;
import java.util.Scanner;

public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    public void addRating() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ID sách: ");
        String bookId = scanner.nextLine();

        System.out.print("Nhập số sao (1-5): ");
        int starRating = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập nhận xét: ");
        String comment = scanner.nextLine();

        Rating rating = new Rating(bookId, starRating, comment);
        ratingService.addRating(rating);
        System.out.println("Đánh giá đã được thêm.");
    }

    public void viewRatings() {
        ratingService.getAllRatings().forEach(rating -> {
            System.out.println("Sách ID: " + rating.getBookId() + ", Số sao: " + rating.getStarRating() + ", Nhận xét: " + rating.getComment());
        });
    }
}
