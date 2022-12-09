package com.foodnyang.driver.order;

public class DriverOrderElement {
    private final Integer id;
    private final Integer harga;
    private final String pelanggan;
    private final String restoran;
    private final String address;

    private final String status;

    public DriverOrderElement(int id, String pelanggan, String restoran, String address, int harga, String status) {
        this.id = id;
        this.pelanggan = pelanggan;
        this.restoran = restoran;
        this.harga = harga;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public String getPelanggan() {
        return this.pelanggan;
    }

    public String getRestoran() {
        return this.restoran;
    }

    public int getHarga() {
        return this.harga;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

}
