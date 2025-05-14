package model;

import java.io.Serializable;
import java.time.LocalDate;

public class BorrowRecord implements Serializable {
    private String borrowId;
    private String borrowerId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;

    public BorrowRecord(String borrowId, String borrowerId, String bookId,
                        LocalDate borrowDate, LocalDate dueDate) {
        this.borrowId = borrowId;
        this.borrowerId = borrowerId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
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

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return String.format(
                "\n" +
                        "╔══════════════════════════════════════╗\n" +
                        "║ Borrow ID : %-25s ║\n" +
                        "║ Borrower ID : %-25s ║\n" +
                        "║ Book ID    : %-25s ║\n" +
                        "║ Borrow Date: %-25s ║\n" +
                        "║ Due Date   : %-25s ║\n" +
                        "║ Return Date: %-25s ║\n" +
                        "╚══════════════════════════════════════╝\n",
                borrowId, borrowerId, bookId,
                borrowDate != null ? borrowDate.toString() : "Chưa mượn",
                dueDate != null ? dueDate.toString() : "Chưa có hạn",
                returnDate != null ? returnDate.toString() : "Chưa trả"
        );
    }

}
