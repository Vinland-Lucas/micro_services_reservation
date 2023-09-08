package com.reservation.microservicesreservation.model;

import java.util.Date;

public class Customer {
    private int id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String email;
    private String licenceNumber;
    private Date licenceDate;

    public Customer(){}

    public Customer(int id, String lastName, String firstName, Date birthDate, String email, String licenceNumber, Date licenceDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.email = email;
        this.licenceNumber = licenceNumber;
        this.licenceDate = licenceDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", licenceNumber='" + licenceNumber + '\'' +
                ", licenceDate=" + licenceDate +
                '}';
    }
}
