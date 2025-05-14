package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private String bookId;
    private String borrowerId;
    private LocalDate reservationDate;
    private ReservationStatus status;

    public Reservation(String bookId, String borrowerId, LocalDate reservationDate, ReservationStatus status) {
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "\n" +
                        "╔══════════════════════════════════════╗\n" +
                        "║ Borrower ID       : %-25s ║\n" +
                        "║ Book ID           : %-25s ║\n" +
                        "║ Reservation Date  : %-25s ║\n" +
                        "║ Status            : %-25s ║\n" +
                        "╚══════════════════════════════════════╝\n",
                borrowerId, bookId, reservationDate, status
        );
    }
}