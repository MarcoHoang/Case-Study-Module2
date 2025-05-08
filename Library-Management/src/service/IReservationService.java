package service;

import model.Reservation;

import java.util.List;

public interface IReservationService {
    void addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
}
