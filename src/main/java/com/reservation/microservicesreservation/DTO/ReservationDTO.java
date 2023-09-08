package com.reservation.microservicesreservation.DTO;

import java.util.Date;

public class ReservationDTO {
    private int id;
    private Date startingDate;
    private Date endingDate;
    private int estimatedDistance;

    private int userId; // user qui ont dejà réservés
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String email;
    private String licenceNumber;
    private Date licenceDate;

    private int vehiculeId; // véhicules déjà réservés
    private String registration;
    private String type;
    private String brand;
    private String model;
    private String color;
    private int displacement;
    private int volumeCapacity;
    private int horsepowerTax;
    private int mileagePrice;
    private int applicationFee;
    private int cleaningCost;
    private int mileage;

    public ReservationDTO() {
    }

    public ReservationDTO(int id,
                          Date startingDate,
                          Date endingDate,
                          int estimatedDistance,
                          int userId,
                          String lastName,
                          String firstName,
                          Date birthDate,
                          String email,
                          String licenceNumber,
                          Date licenceDate,
                          int vehiculeId,
                          String registration,
                          String type,
                          String brand,
                          String model,
                          String color,
                          int displacement,
                          int volumeCapacity,
                          int horsepowerTax,
                          int mileagePrice,
                          int applicationFee,
                          int cleaningCost,
                          int mileage) {
        this.id = id;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.estimatedDistance = estimatedDistance;
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.email = email;
        this.licenceNumber = licenceNumber;
        this.licenceDate = licenceDate;
        this.vehiculeId = vehiculeId;
        this.registration = registration;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.displacement = displacement;
        this.volumeCapacity = volumeCapacity;
        this.horsepowerTax = horsepowerTax;
        this.mileagePrice = mileagePrice;
        this.applicationFee = applicationFee;
        this.cleaningCost = cleaningCost;
        this.mileage = mileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Date getLicenceDate() {
        return licenceDate;
    }

    public void setLicenceDate(Date licenceDate) {
        this.licenceDate = licenceDate;
    }

    public int getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(int vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(int volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public int getHorsepowerTax() {
        return horsepowerTax;
    }

    public void setHorsepowerTax(int horsepowerTax) {
        this.horsepowerTax = horsepowerTax;
    }

    public int getMileagePrice() {
        return mileagePrice;
    }

    public void setMileagePrice(int mileagePrice) {
        this.mileagePrice = mileagePrice;
    }

    public int getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(int applicationFee) {
        this.applicationFee = applicationFee;
    }

    public int getCleaningCost() {
        return cleaningCost;
    }

    public void setCleaningCost(int cleaningCost) {
        this.cleaningCost = cleaningCost;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", estimatedDistance=" + estimatedDistance +
                ", userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", licenceNumber='" + licenceNumber + '\'' +
                ", licenceDate=" + licenceDate +
                ", vehiculeId=" + vehiculeId +
                ", registration='" + registration + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", displacement=" + displacement +
                ", volumeCapacity=" + volumeCapacity +
                ", horsepowerTax=" + horsepowerTax +
                ", mileagePrice=" + mileagePrice +
                ", applicationFee=" + applicationFee +
                ", cleaningCost=" + cleaningCost +
                ", mileage=" + mileage +
                '}';
    }
}
