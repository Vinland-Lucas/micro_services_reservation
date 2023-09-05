package com.reservation.microservicesreservation.web.controller;

import com.reservation.microservicesreservation.model.Reservation;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RequestMapping("/reservations")
@RestController
public class ReservationController {

    public void getAllVehicules(ArrayList<>)

    @GetMapping
    public ArrayList<Reservation> displayAllOccupiedVehicules() {
        return
    }

    @GetMapping("/{id}")
    public Reservation displayReservationById(@PathVariable int id) {

    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {

    }

    @PutMapping("{id}")
    public Reservation modifyReservation(@RequestBody Reservation reservation) {

    }

    @DeleteMapping("{id}")
    public Reservation deleteReservation(@PathVariable int id) {

    }
}
