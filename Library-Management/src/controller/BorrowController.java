package controller;

import model.BorrowRecord;
import service.BorrowService;
import service.IBorrowService;

import java.util.List;

public class BorrowController {
    private final IBorrowService borrowService = BorrowService.getInstance();

    public void borrowBook(BorrowRecord record) {
        borrowService.borrowBook(record);
    }

    public boolean returnBook(String borrowId) {
        return borrowService.returnBook(borrowId);
    }

    public List<BorrowRecord> getAllBorrows() {
        return borrowService.getAllBorrowRecords();
    }

    public List<BorrowRecord> getBorrowRecordsByUser(String userId) {
        return borrowService.getBorrowRecordsByUser(userId);
    }

    public BorrowRecord findBorrowRecordById(String borrowId) {
        return borrowService.getAllBorrowRecords().stream()
                .filter(record -> record.getBorrowId().equals(borrowId))
                .findFirst()
                .orElse(null);
    }

    public String generateBorrowId(String userId, String bookId) {
        return borrowService.generateBorrowId(userId, bookId);
    }
}