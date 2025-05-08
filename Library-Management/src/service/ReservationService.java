package service;

import model.Reservation;
import storage.ReservationStorage;
import java.util.List;

public class ReservationService implements IReservationService {
    private final ReservationStorage storage = new ReservationStorage();

    @Override
    public void addReservation(Reservation reservation) {
        List<Reservation> reservations = storage.readFromFile("reservations.dat");
        reservations.add(reservation);
        storage.writeToFile("reservations.dat", reservations);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return storage.readFromFile("reservations.dat");
    }
}
