package com.foodnyang.database.admin.customer;

public class Customer {
    private Integer idCustomer;
    private String name, email, phone_num, gender, birthPlace, birthDate, jenisMembership;

    public Customer (int idCustomer, String name, String email, String phone_num, String birthPlace, String birthDate, String gender, String jenisMembership) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.gender = gender;
        this.jenisMembership = jenisMembership;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getJenisMembership() {
        return jenisMembership;
    }
}
