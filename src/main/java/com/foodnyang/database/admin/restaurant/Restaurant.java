package com.foodnyang.database.admin.restaurant;

public class Restaurant {
    private Integer idResto, idMitra, rating;
    private String namaResto, namaOwner, tipeResto, alamat;

    public Restaurant(int idResto, int idMitra, String namaResto, String namaOwner, String tipeResto, String alamat, int rating) {
        this.idResto = idResto;
        this.idMitra = idMitra;
        this.namaResto = namaResto;
        this.namaOwner = namaOwner;
        this.tipeResto = tipeResto;
        this.alamat = alamat;
        this.rating = rating;
    }

    public Integer getIdMitra() {
        return idMitra;
    }

    public Integer getIdResto() {
        return idResto;
    }

    public String getNamaResto() {
        return namaResto;
    }

    public String getNamaOwner() {
        return namaOwner;
    }

    public String getTipeResto() {
        return tipeResto;
    }

    public String getAlamat() {
        return alamat;
    }

    public Integer getRating() {
        return rating;
    }
}
