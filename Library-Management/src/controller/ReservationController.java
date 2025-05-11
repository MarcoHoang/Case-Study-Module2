package controller;

import model.Reservation;
import model.User;
import service.ReservationService;

import java.time.LocalDate;
import java.util.Scanner;

public class ReservationController {
    private final ReservationService reservationService;
    private final User currentUser;
    private final Scanner scanner = new Scanner(System.in);

    public ReservationController(ReservationService reservationService, User currentUser) {
        this.reservationService = reservationService;
        this.currentUser = currentUser;
    }

    public void makeReservation() {
        System.out.println("=== Đặt sách ===");
        System.out.print("Nhập ID sách bạn muốn đặt: ");
        String bookId = scanner.nextLine();

        LocalDate reservationDate = LocalDate.now();

        Reservation reservation = new Reservation(bookId, currentUser.getId(), reservationDate);
        reservationService.addReservation(reservation);
        System.out.println("Đặt sách thành công.");
    }

    public void viewReservations() {
        System.out.println("=== Danh sách sách đã đặt ===");
        reservationService.getAllReservations().forEach(reservation -> {
            System.out.println("Sách ID: " + reservation.getBookId());
            System.out.println("Người dùng ID: " + reservation.getBorrowerId());
            System.out.println("Ngày đặt: " + reservation.getReservationDate());
            System.out.println("-------------------------------");
        });
    }
}
