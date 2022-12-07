package com.foodnyang.customer.ordering;

public class FoodMenuElement {
    private String name, description;
    private int harga, qty, stok;

    public FoodMenuElement(String name, String description, int harga, int stok) {
        this.name = name;
        this.description = description;
        this.harga = harga;
        this.qty = 0;
        this.stok = stok;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHarga() {
        return harga;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        if (0 <= qty && qty <= stok) this.qty = qty;
    }
}
