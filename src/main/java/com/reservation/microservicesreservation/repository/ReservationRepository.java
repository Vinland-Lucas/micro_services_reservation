package com.reservation.microservicesreservation.repository;

import com.reservation.microservicesreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    ArrayList<Reservation> findAll();
    Reservation findById(int id);
    Reservation save(Reservation reservation);
    Reservation deleteById(int id);

    @Query(value = "SELECT r.vehiculeId FROM Reservation r " +
            "WHERE r.startingDate BETWEEN (:userReservationStartingDate) AND (:userReservationEndingDate) " +
            "OR r.endingDate BETWEEN (:userReservationStartingDate) AND (:userReservationEndingDate) " +
            "OR r.startingDate < (:userReservationStartingDate) AND r.endingDate > (:userReservationEndingDate)"
    )
    List<Integer> getVehiculeIds(Date userReservationStartingDate, Date userReservationEndingDate);

}
