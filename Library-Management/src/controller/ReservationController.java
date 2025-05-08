package controller;

import model.Reservation;
import service.ReservationService;
import java.util.Scanner;

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ID sách bạn muốn đặt: ");
        String bookId = scanner.nextLine();

        System.out.print("Nhập ID người dùng: ");
        String userId = scanner.nextLine();

        Reservation reservation = new Reservation(bookId, userId);
        reservationService.addReservation(reservation);
        System.out.println("Đặt sách thành công.");
    }

    public void viewReservations() {
        reservationService.getAllReservations().forEach(reservation -> {
            System.out.println("Sách ID: " + reservation.getBookId() + ", Người dùng ID: " + reservation.getUserId());
        });
    }
}
