package com.reservation.microservicesreservation.DTO;

public class VehiculesDTO {
    private String type;
    private String brand;
    private String model;
    private String color;
    private int displacement;
    private int volumeCapacity;
    private int horsepowerTax;
    private int mileagePrice;
    private double totalPrice;

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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "VehiculesDTO{" +
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", displacement=" + displacement +
                ", volumeCapacity=" + volumeCapacity +
                ", horsepowerTax=" + horsepowerTax +
                ", mileagePrice=" + mileagePrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
