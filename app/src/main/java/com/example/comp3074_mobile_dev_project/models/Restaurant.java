package com.example.comp3074_mobile_dev_project.models;


import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String name;
    private double rating;
    private String image;
    private String address;
    private double latitude;
    private double longitude;

    public Restaurant(UUID id, String name, double rating, String image, String address, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.image = image;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        longitude = longitude;
    }

}
