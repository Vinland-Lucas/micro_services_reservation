package com.reservation.microservicesreservation.model;

import javax.persistence.*;

public class Vehicule {
    private int id;
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

    public Vehicule() {}

    public Vehicule(
            int id,
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
            int mileage
    ) {
        this.id = id;
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
        return "Vehicules{" +
                "id=" + id +
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
