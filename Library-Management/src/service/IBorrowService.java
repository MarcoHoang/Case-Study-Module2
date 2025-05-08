package service;

import model.BorrowRecord;

import java.util.List;

public interface IBorrowService {
    void borrowBook(BorrowRecord record);                     // Ghi nhận mượn sách

    boolean returnBook(String borrowId);                      // Trả sách theo ID mượn

    List<BorrowRecord> getAllBorrowRecords();                 // Lấy tất cả lịch sử mượn trả

    List<BorrowRecord> getBorrowRecordsByUser(String userId); // Lấy lịch sử mượn theo người dùng
}
