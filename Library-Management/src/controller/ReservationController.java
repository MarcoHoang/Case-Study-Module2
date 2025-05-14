package controller;

import model.Reservation;
import model.User;
import service.entries.ReservationService;

import java.time.LocalDate;

import static model.ReservationStatus.PENDING;


public class ReservationController {
    private final ReservationService reservationService;
    private final User currentUser;

    public ReservationController(ReservationService reservationService, User currentUser) {
        this.reservationService = ReservationService.getInstance();
        this.currentUser = currentUser;
    }

    public void makeReservation(String bookId) {
        if (bookId == null || bookId.isEmpty()) {
            return;
        }

        LocalDate reservationDate = LocalDate.now();
        Reservation reservation = new Reservation(bookId, currentUser.getId(), reservationDate, PENDING);
        reservationService.addReservation(reservation);
    }

    public void viewReservations() {
        System.out.println("=== Danh sách sách đã đặt ===");
        reservationService.getAllReservations().forEach(reservation -> {
            System.out.println("Sách ID: " + reservation.getBookId());
            System.out.println("Người dùng ID: " + reservation.getBorrowerId());
            System.out.println("Ngày đặt: " + reservation.getReservationDate());
            System.out.println("Trạng thái: " + reservation.getStatus());
            System.out.println("-------------------------------");
        });
    }
}
