package service.entries;

import config.AppConfig;
import model.Reservation;
import model.ReservationStatus;
import service.interfaces.IReservationService;
import storage.GenericFileStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService implements IReservationService {
    private static ReservationService instance;
    private final GenericFileStorage<Reservation> storage;
    private List<Reservation> reservations;

    private ReservationService() {
        this.storage = new GenericFileStorage<>();
        this.reservations = storage.readFromFile(AppConfig.RESERVATION_FILE);
        if (this.reservations == null) {
            this.reservations = new ArrayList<>();
        }
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            synchronized (ReservationService.class) {
                if (instance == null) {
                    instance = new ReservationService();
                }
            }
        }
        return instance;
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        storage.writeToFile(AppConfig.RESERVATION_FILE, reservations);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    public List<Reservation> getPendingReservationsForBook(String bookId) {
        return reservations.stream()
                .filter(r -> r.getBookId().equals(bookId) && r.getStatus() == ReservationStatus.PENDING)
                .collect(Collectors.toList());
    }

    public void updateReservationStatus(String bookId, String userId, ReservationStatus newStatus) {
        for (Reservation reservation : reservations) {
            if (reservation.getBookId().equals(bookId) && reservation.getBorrowerId().equals(userId)) {
                reservation.setStatus(newStatus);
                storage.writeToFile(AppConfig.RESERVATION_FILE, reservations);
                return;
            }
        }
        System.out.println("Không tìm thấy đơn đặt trước cho sách ID: " + bookId + " và người dùng ID: " + userId);
    }
}