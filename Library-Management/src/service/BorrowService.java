package service;

import config.AppConfig;
import model.BorrowRecord;
import storage.GenericFileStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowService implements IBorrowService {
    private final GenericFileStorage<BorrowRecord> storage = new GenericFileStorage<>();
    private List<BorrowRecord> records;

    public BorrowService() {
        records = storage.readFromFile(AppConfig.BORROW_FILE);
        if (records == null) records = new ArrayList<>();
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

    private void save() {
        storage.writeToFile(AppConfig.BORROW_FILE, records);
    }
}
