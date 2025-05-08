package view;

import controller.RatingController;
import controller.ReservationController;
import service.RatingService;
import service.ReservationService;
import java.util.Scanner;

public class MainView {
    private static final RatingController ratingController = new RatingController(new RatingService());
    private static final ReservationController reservationController = new ReservationController(new ReservationService());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Đánh giá sách");
        }
    }
}
