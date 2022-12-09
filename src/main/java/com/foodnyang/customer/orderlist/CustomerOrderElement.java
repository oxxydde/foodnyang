package com.foodnyang.customer.orderlist;

public class CustomerOrderElement {
    private final Integer id;
    private final Integer harga;
    private final String driver;
    private final String restoran;
    private final String address;

    private final String status;

    public CustomerOrderElement(int id, String driver, String restoran, String address, int harga, String status) {
        this.id = id;
        this.driver = driver;
        this.restoran = restoran;
        this.harga = harga;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public String getDriver() {
        return this.driver;
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
