package view;

import controller.BorrowController;
import model.BorrowRecord;

import java.util.List;

public class BorrowView {

    public static void viewBorrowedBooks(BorrowController borrowController) {
        List<BorrowRecord> borrowedBooks = borrowController.getAllBorrows();
        if (borrowedBooks.isEmpty()) {
            System.out.println("Không có sách nào đang được mượn.");
        } else {
            System.out.println("--- Danh sách Sách Đang Được Mượn ---");
            for (BorrowRecord record : borrowedBooks) {
                System.out.println(record);
            }
        }
    }
}