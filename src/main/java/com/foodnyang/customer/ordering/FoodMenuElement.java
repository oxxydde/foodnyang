package com.foodnyang.customer.ordering;

public class FoodMenuElement {
    private final String name, description, urlImage;
    private final int id, harga, stok;
    private int qty, totalHarga;

    public FoodMenuElement(int id, String name, String description, int harga, int stok, String urlImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.harga = harga;
        this.qty = 0;
        this.stok = stok;
        this.urlImage = urlImage;
    }

    public int getId() {
        return id;
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

    public String getUrlImage() {
        return urlImage;
    }

    public int getStok() {
        return stok;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setQty(int qty) {
        if (0 <= qty && qty <= stok) {
            this.qty = qty;
            this.totalHarga = this.harga * this.qty;
        }
    }
}
