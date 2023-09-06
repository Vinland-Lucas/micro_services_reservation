package com.reservation.microservicesreservation.repository;

import com.reservation.microservicesreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    ArrayList<Reservation> findAll();
    Reservation findById(int id);

    Reservation save(Reservation reservation);

    Reservation deleteById(int id);

    @Query(value = "SELECT vehiculeId FROM Reservation")
    ArrayList<Integer> getVehiculeIds();

}
