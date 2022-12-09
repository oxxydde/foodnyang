package com.foodnyang.login;

public class AccountInfo {
    private final int id;
    private final String nama, jenisKelamin, nomorTelp, email;

    public AccountInfo(int id, String nama, String jenisKelamin, String nomorTelp, String email) {
        this.id = id;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.nomorTelp = nomorTelp;
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
