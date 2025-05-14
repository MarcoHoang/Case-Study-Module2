package service.interfaces;

import model.Reservation;
import model.ReservationStatus;
import java.util.List;

public interface IReservationService {
    void addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    List<Reservation> getPendingReservationsForBook(String bookId);
    void updateReservationStatus(String bookId, String userId, ReservationStatus newStatus);
}