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

    private int userId; // user qui ont dejà réservés
    private int vehiculeId; // véhicules déjà réservés
    private Date startingDate;
    private Date endingDate;
    private int estimatedDistance;

    public Reservation() {

    }

    public Reservation(int id, int userId, int vehiculeId, Date startingDate, Date endingDate, int estimatedDistance) {
        this.id = id;
        this.userId = userId;
        this.vehiculeId = vehiculeId;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.estimatedDistance = estimatedDistance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(int vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public int getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(int estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", vehiculeId=" + vehiculeId +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", estimatedDistance=" + estimatedDistance +
                '}';
    }
}
