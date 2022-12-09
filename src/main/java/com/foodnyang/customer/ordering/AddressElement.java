package com.foodnyang.customer.ordering;

public class AddressElement {
    private int addressId;
    private String name, address, zipcode;
    private double latitude, longitude;

    public AddressElement(int addressId, String name, String address, String zipcode, double latitude, double longitude) {
        this.addressId = addressId;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
