package com.foodnyang.database.customer;

public class Restaurant {
    String name;
    String category;
    // list makanan

    public Restaurant(String name, String cat) {
        this.name = name;
        this.category = cat;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }
}
