package com.reservation.microservicesreservation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private int user_id; // user qui ont dejà réservés
    private int vehicule_id; // véhicules déjà réservés
    private Date starting_date;
    private Date ending_date;
    private int estimate_distance;

    public Reservation() {

    }

    public Reservation(int id, int user_id, int vehicule_id, Date starting_date, Date ending_date, int estimate_distance) {
        this.id = id;
        this.user_id = user_id;
        this.vehicule_id = vehicule_id;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.estimate_distance = estimate_distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVehicule_id() {
        return vehicule_id;
    }

    public void setVehicule_id(int vehicule_id) {
        this.vehicule_id = vehicule_id;
    }

    public Date getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(Date starting_date) {
        this.starting_date = starting_date;
    }

    public Date getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(Date ending_date) {
        this.ending_date = ending_date;
    }

    public int getEstimate_distance() {
        return estimate_distance;
    }

    public void setEstimate_distance(int estimate_distance) {
        this.estimate_distance = estimate_distance;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", vehicule_id=" + vehicule_id +
                ", starting_date=" + starting_date +
                ", ending_date=" + ending_date +
                ", estimate_distance=" + estimate_distance +
                '}';
    }
}
