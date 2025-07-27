package org.kuswanid.propertease.models;

import java.util.Date;

public class Property {
    private final int id;
    private final String address;
    private final String description;
    private final String name;
    private final double rentPrice;
    private final String status;
    private final String type;

    public Property(int id, String address, String description, String name, double rentPrice, String status, String type) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.name = name;
        this.rentPrice = rentPrice;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }
}
