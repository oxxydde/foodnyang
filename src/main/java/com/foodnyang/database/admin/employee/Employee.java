package com.foodnyang.database.admin.employee;

public class Employee {
    private Integer idPegawai, salary;
    private String name, email, phone_num, birthPlace, birthDate, gender, tanggalPerekrutan, pekerjaan, departemen;

    public Employee (int idPegawai, String name, String email, String phone_num, String birthPlace, String birthDate, String gender, String tanggalPerekrutan, String pekerjaan, String departemen, int salary) {
        this.idPegawai = idPegawai;
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.gender = gender;
        this.tanggalPerekrutan = tanggalPerekrutan;
        this.pekerjaan = pekerjaan;
        this.departemen = departemen;
        this.salary = salary;
    }

    public int getIdPegawai() {return this.idPegawai;}
    public String getName() {return this.name;}
    public String getEmail() {return this.email;}
    public String getPhone_num() {return this.phone_num;}
    public String getBirthPlace() {return this.birthPlace;}
    public String getBirthDate() {return this.birthDate;}
    public String getGender() {return this.gender;}
    public Integer getSalary() {return this.salary;}
    public String getTanggalPerekrutan() {return this.tanggalPerekrutan;}
    public String getPekerjaan() {return this.pekerjaan;}
    public String getDepartemen() {return this.departemen;}
}
