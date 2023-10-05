package com.reservation.microservicesreservation.web.controller;

import com.reservation.microservicesreservation.DTO.ReservationDTO;
import com.reservation.microservicesreservation.DTO.VehiculesDTO;
import com.reservation.microservicesreservation.model.Customer;
import com.reservation.microservicesreservation.model.Reservation;
import com.reservation.microservicesreservation.model.Vehicule;
import com.reservation.microservicesreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping("/reservations")
@CrossOrigin
@RestController
public class ReservationController {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ReservationRepository reservationRepository;
    private final String uriAPIVehicules = "http://localhost:9092/vehicules";
    private final String uriAPICustomers = "http://192.168.1.230:9090/customers";

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public ArrayList<Reservation> displayAllOccupiedVehicules() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ReservationDTO displayReservationById(@PathVariable int id) {
        Reservation reservation = reservationRepository.findById(id);
        // Ajouter DTO réseumé de réservation : infos client + infos véhicule
        int userId = reservation.getUserId();
        Customer customer = restTemplate.getForObject(uriAPICustomers + "/" + userId, Customer.class);
        int vehiculeId = reservation.getVehiculeId();
        Vehicule vehicule = restTemplate.getForObject(uriAPIVehicules + "/" + vehiculeId, Vehicule.class);
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartingDate(reservation.getStartingDate());
        reservationDTO.setEndingDate(reservation.getEndingDate());
        reservationDTO.setEstimatedDistance(reservation.getEstimatedDistance());
        reservationDTO.setUserId(reservation.getUserId());
        reservationDTO.setLastName(customer.getLastName());
        reservationDTO.setFirstName(customer.getFirstName());
        reservationDTO.setBirthDate(customer.getBirthDate());
        reservationDTO.setEmail(customer.getEmail());
        reservationDTO.setLicenceNumber(customer.getLicenceNumber());
        reservationDTO.setLicenceDate(customer.getLicenceDate());
        reservationDTO.setVehiculeId(reservation.getVehiculeId());
        reservationDTO.setRegistration(vehicule.getRegistration());
        reservationDTO.setType(vehicule.getType());
        reservationDTO.setBrand(vehicule.getBrand());
        reservationDTO.setModel(vehicule.getModel());
        reservationDTO.setColor(vehicule.getColor());
        reservationDTO.setDisplacement(vehicule.getDisplacement());
        reservationDTO.setVolumeCapacity(vehicule.getVolumeCapacity());
        reservationDTO.setHorsepowerTax(vehicule.getHorsepowerTax());
        reservationDTO.setMileagePrice(vehicule.getMileagePrice());
        reservationDTO.setApplicationFee(vehicule.getApplicationFee());
        reservationDTO.setCleaningCost(vehicule.getCleaningCost());
        reservationDTO.setMileage(vehicule.getMileage());

        return reservationDTO;

//        return reservationRepository.findById(id);
    }

    @PostMapping
    public @ResponseBody Reservation addReservation(@RequestBody Reservation reservation) {
        int userId = reservation.getUserId();
        int vehiculeId = reservation.getVehiculeId();
        if (checkAgeVsHptax(userId, vehiculeId)) {
            // Http Status 200 OK ?
            return reservationRepository.save(reservation);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Véhicule non autorisé : vous êtes trop jeune !"
            ); // httpresponse 400 badrequest
        }
    }

    @PutMapping("/{id}")
    public Reservation modifyReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        reservation.setUserId(id); // récupérer l'Id du client
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
            vehiculeToDisplay.setId(vehicule.getId());
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
    public boolean checkAgeVsHptax(int userId, int vehiculeId) {
        Vehicule vehiculeChosen = restTemplate.getForObject(uriAPIVehicules + "/" + vehiculeId, Vehicule.class);
        Customer customer = restTemplate.getForObject(uriAPICustomers + "/" + userId, Customer.class);
        Date currentDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int date1 = Integer.parseInt(formatter.format(customer.getBirthDate()));
        int date2 = Integer.parseInt(formatter.format(currentDate));
        int age = (date2 - date1) / 10000;
        // si âge < 21 => vehicule avec hptaxes < 8
        // si âge < 25 => vehicule avec hptaxes < 13
        // sinon => tous les véhicules ok
        if ((age < 21 && vehiculeChosen.getHorsepowerTax() < 8)
                || (age <= 25 && vehiculeChosen.getHorsepowerTax() < 13)
                || age > 25) {
            return true;
        } else {
            return false;
        }
    }
}