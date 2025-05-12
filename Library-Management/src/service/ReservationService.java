package service;

import config.AppConfig;
import model.Reservation;
import storage.GenericFileStorage;

import java.util.ArrayList;
import java.util.List;

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
}