package com.reservation.microservicesreservation.web.controller;

import com.reservation.microservicesreservation.model.Reservation;
import com.reservation.microservicesreservation.model.Vehicule;
import com.reservation.microservicesreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
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

    public ArrayList<Integer> getReservedVehiculeIds () {
        return reservationRepository.getVehiculeIds();
    }

/*    public void sendVehiculeIdToVehicules (@RequestBody int vehicule_id) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        URI uri = new URI(urlAPIVehicules + "/available");
        ArrayList<Integer> vehiculesIds = reservationRepository.findById(vehicule_id);
        for (Vehicule vehicule : vehiculesIds) {

        }

    }*/

/*    public ArrayList<Vehicule> getAllVehicules() {
        Vehicule vehicule = restTemplate.getForObject(urlAPIVehicules + Vehicule.class);

    }*/
}
