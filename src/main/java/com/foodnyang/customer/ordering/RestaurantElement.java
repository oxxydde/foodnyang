package com.foodnyang.customer.ordering;

public class RestaurantElement {
    private int id;
    private String nama, tipe, alamat;
    private double latitude, longitude, rating;

    public RestaurantElement(int id, String nama, String tipe, String alamat, double latitude, double longitude, double rating) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getTipe() {
        return tipe;
    }

    public String getAlamat() {
        return alamat;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRating() {
        return rating;
    }
}
