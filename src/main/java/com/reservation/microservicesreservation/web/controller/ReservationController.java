package com.reservation.microservicesreservation.web.controller;

import com.reservation.microservicesreservation.model.Reservation;
import com.reservation.microservicesreservation.model.Vehicule;
import com.reservation.microservicesreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/reservations")
@RestController
public class ReservationController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired

    private ReservationRepository reservationRepository;

    private final String uriAPIVehicules = "http://192.168.1.238:9092/vehicules";

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
    public @ResponseBody Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @PutMapping("/{id}")
    public Reservation modifyReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    public Reservation deleteReservation(@PathVariable int id) {
        return reservationRepository.deleteById(id);
    }

    @GetMapping("/available-vehicules/{userReservationStartingDate}/{userReservationEndingDate}")
    public List<Vehicule> getAvailableVehicules (@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date userReservationStartingDate, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date userReservationEndingDate) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        URI uri = new URI(uriAPIVehicules + "/available");
        List<Integer> reservedVehiculeIds = reservationRepository.getVehiculeIds(userReservationStartingDate, userReservationEndingDate);


        HttpEntity<List> httpEntity = new HttpEntity<>(reservedVehiculeIds, headers);

        List<Vehicule> availableVehicules = restTemplate.postForObject(uri, httpEntity, List.class);

        return availableVehicules;
    }

/*    public Date getReservationDate(Date reservationStartingDate, Date reservationEndingDate) {
        return reservationRepository.
    }*/

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
