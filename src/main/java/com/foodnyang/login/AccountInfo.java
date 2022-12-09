package com.foodnyang.login;

public class AccountInfo {
    private int id;
    private String nama, jenisKelamin, nomorTelp, email;

    public AccountInfo(int id, String nama, String jenisKelamin, String nomorTelp, String email) {
        this.id = id;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.nomorTelp = nomorTelp;
        this.email = email;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setNomorTelp(String nomorTelp) {
        this.nomorTelp = nomorTelp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getNomorTelp() {
        return nomorTelp;
    }

    public String getEmail() {
        return email;
    }

}
