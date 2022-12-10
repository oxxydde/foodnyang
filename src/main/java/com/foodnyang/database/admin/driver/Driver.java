package com.foodnyang.database.admin.driver;

public class Driver {
    private Integer idDriver;
    private String name, email, phone_num, jenisKendaraan, gender, birthPlace, birthDate, tanggalBergabung, warnaKendaraan, nomorPolisi;

    public Driver (int idDriver, String name, String email, String phone_num, String birthPlace, String birthDate, String gender, String tanggalBergabung, String jenisKendaraan, String warnaKendaraan, String nomorPolisi) {
        this.idDriver = idDriver;
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.gender = gender;
        this.tanggalBergabung = tanggalBergabung;
        this.jenisKendaraan = jenisKendaraan;
        this.warnaKendaraan = warnaKendaraan;
        this.nomorPolisi = nomorPolisi;
    }

    public Integer getIdDriver() {
        return this.idDriver;
    }
    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }

    public String getPhone_num() {
        return this.phone_num;
    }

    public String getJenisKendaraan() {
        return this.jenisKendaraan;
    }

    public String getGender() {
        return this.gender;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getTanggalBergabung() {
        return this.tanggalBergabung;
    }

    public String getWarnaKendaraan() {
        return this.warnaKendaraan;
    }

    public String getNomorPolisi() {
        return this.nomorPolisi;
    }
}
