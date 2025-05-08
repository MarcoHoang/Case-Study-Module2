package storage;

import config.AppConfig;
import model.Reservation;
import java.util.List;

public class ReservationStorage extends GenericFileStorage<Reservation> {
    public List<Reservation> getAllReservations() {
        return readFromFile(AppConfig.RESERVATION_FILE);
    }

    public void saveAllReservations(List<Reservation> reservations) {
        writeToFile(AppConfig.RESERVATION_FILE, reservations);
    }
}
