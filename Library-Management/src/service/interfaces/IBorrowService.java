package service.interfaces;

import model.BorrowRecord;

import java.util.List;

public interface IBorrowService {
    void borrowBook(BorrowRecord record);

    boolean returnBook(String borrowId);

    String generateBorrowId(String userId, String bookId);

    List<BorrowRecord> getAllBorrowRecords();

    List<BorrowRecord> getBorrowRecordsByUser(String userId);
}
