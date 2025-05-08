package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private String borrowerId;
    private String bookId;
    private LocalDate reservationDate;

    public Reservation() {}

    public Reservation(String borrowerId, String bookId, LocalDate reservationDate) {
        this.borrowerId = borrowerId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
    }

    public String getBorrowerId() { return borrowerId; }
    public void setBorrowerId(String borrowerId) { this.borrowerId = borrowerId; }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }
}