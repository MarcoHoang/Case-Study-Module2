package service.entries;

import config.AppConfig;
import model.BorrowRecord;
import service.interfaces.IBorrowService;
import storage.GenericFileStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowService implements IBorrowService {
    private static BorrowService instance;
    private final GenericFileStorage<BorrowRecord> storage = new GenericFileStorage<>();
    private List<BorrowRecord> records;

    private BorrowService() {
        records = storage.readFromFile(AppConfig.BORROW_FILE);
        if (records == null) records = new ArrayList<>();
    }

    public static BorrowService getInstance() {
        if (instance == null) {
            synchronized (BorrowService.class) {
                if (instance == null) {
                    instance = new BorrowService();
                }
            }
        }
        return instance;
    }

    @Override
    public void borrowBook(BorrowRecord record) {
        records.add(record);
        save();
    }

    @Override
    public boolean returnBook(String borrowId) {
        for (BorrowRecord record : records) {
            if (record.getBorrowId().equals(borrowId) && !record.isReturned()) {
                record.setReturned(true);
                record.setReturnDate(java.time.LocalDate.now());
                save();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecords() {
        return new ArrayList<>(records);
    }

    @Override
    public List<BorrowRecord> getBorrowRecordsByUser(String userId) {
        return records.stream()
                .filter(record -> record.getBorrowerId().equals(userId))
                .collect(Collectors.toList());
    }

    public String generateBorrowId(String userId, String bookId) {
        return "BR_" + userId + "_" + bookId + "_BOOK";
    }

    private void save() {
        storage.writeToFile(AppConfig.BORROW_FILE, records);
    }
}