package com.reservation.microservicesreservation.web.controller;

import com.reservation.microservicesreservation.model.Reservation;
import com.reservation.microservicesreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RequestMapping("/reservations")
@RestController
public class ReservationController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired

    private ReservationRepository reservationRepository;

    private final String urlAPIVehicules = "http://localhost:9092/vehicules/";

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public void getAllVehicules(String vehiculeId) {


    }

    @GetMapping
    public ArrayList<Reservation> displayAllOccupiedVehicules() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation displayReservationById(@PathVariable int id) {
        return reservationRepository.findById(id);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @PutMapping("{id}")
    public Reservation modifyReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("{id}")
    public Reservation deleteReservation(@PathVariable int id) {
        return reservationRepository.deleteById(id);
    }
}
