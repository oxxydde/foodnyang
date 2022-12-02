package com.foodnyang.driver.order;

public class Order {
    private Integer id, harga;
    private String pelanggan, restoran;

    public Order(int id, String pelanggan, String restoran, int harga) {
        this.id = id;
        this.pelanggan = pelanggan;
        this.restoran = restoran;
        this.harga = harga;
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
}
