package com.foodnyang.customer.ordering;

public class AddressElement {
    private int addressId;
    private String name;

    public AddressElement(int addressId, String name) {
        this.addressId = addressId;
        this.name = name;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getName() {
        return name;
    }
}
