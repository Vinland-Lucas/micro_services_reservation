package com.reservation.microservicesreservation.web.controller;

import com.reservation.microservicesreservation.DTO.VehiculesDTO;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping("/reservations")
@RestController
public class ReservationController {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ReservationRepository reservationRepository;
    private final String uriAPIVehicules = "http://localhost:9092/vehicules";

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

    @GetMapping("/available-vehicules/{userReservationStartingDate}/{userReservationEndingDate}/{estimatedMileage}")
    public List<VehiculesDTO> getAvailableVehicules(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date userReservationStartingDate,
                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date userReservationEndingDate,
                                                 @PathVariable int estimatedMileage)
            throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(uriAPIVehicules + "/available");
        // Méthode pour récupérer la liste des ids de véhicules occupés aux dates (date début, date de fin)
        List<Integer> reservedVehiculeIds = reservationRepository.getVehiculeIds(userReservationStartingDate, userReservationEndingDate);
        // Liste de "vehiculesIds" occupés à envoyer à microservice Vehicules
        HttpEntity<List> httpEntity = new HttpEntity<>(reservedVehiculeIds, headers);
        // Réponse attendue de l'API microservices vehicules : Post (pour api à l'adresse "uri", objet envoyé pour comparairson (corps de requête), type de retour attendu)
        // On récupère une liste de véhicules disponibles
        List<Vehicule> availableVehicules = Arrays.asList(restTemplate.postForObject(uri, httpEntity, Vehicule[].class));

        return convertToDto(availableVehicules, estimatedMileage);
    }

    public double calculateTotalPrice(Vehicule vehicule, int estimatedMileage) {
        double totalPrice;
        if (vehicule.getType().equals("car")){
            totalPrice = calculateStartingPrice(vehicule) + calculateTotalMileagePrice(vehicule, estimatedMileage);
        } else if (vehicule.getType().equals("moto")) {
            totalPrice = calculateStartingPrice(vehicule) + calculateTotalMileagePrice(vehicule, estimatedMileage) * (vehicule.getDisplacement() * 0.001);
        } else {
            totalPrice = calculateStartingPrice(vehicule) + calculateTotalMileagePrice(vehicule, estimatedMileage) * (vehicule.getVolumeCapacity() * 0.05);
        }
        return totalPrice;
    }
    public double calculateStartingPrice(Vehicule vehicule) {
        return vehicule.getCleaningCost() + vehicule.getApplicationFee();
    }
    public double calculateTotalMileagePrice(Vehicule vehicule, int estimatedMileage) {
        return vehicule.getMileagePrice() * estimatedMileage;
    }
    public List<VehiculesDTO> convertToDto(List<Vehicule> vehicules, int estimatedMileage) {
        List<VehiculesDTO> vehiculesDTO = new ArrayList<>();
        for (Vehicule vehicule: vehicules) {
            VehiculesDTO vehiculeToDisplay = new VehiculesDTO();
            vehiculeToDisplay.setType(vehicule.getType());
            vehiculeToDisplay.setBrand(vehicule.getBrand());
            vehiculeToDisplay.setModel(vehicule.getModel());
            vehiculeToDisplay.setColor(vehicule.getModel());
            vehiculeToDisplay.setDisplacement(vehicule.getDisplacement());
            vehiculeToDisplay.setVolumeCapacity(vehicule.getVolumeCapacity());
            vehiculeToDisplay.setHorsepowerTax(vehicule.getHorsepowerTax());
            vehiculeToDisplay.setMileagePrice(vehicule.getMileagePrice());
            vehiculeToDisplay.setTotalPrice(calculateTotalPrice(vehicule, estimatedMileage));
            vehiculesDTO.add(vehiculeToDisplay);
        }
        return vehiculesDTO;
    }

}