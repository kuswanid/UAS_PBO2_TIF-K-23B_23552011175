package org.kuswanid.propertease.models;


public class Lease {
    private int id;
    private int propertyId;
    private int tenantId;
    private int duration;
    private String status;

    public Lease(int id, int propertyId, int tenantId, int duration, String status) {
        this.id = id;
        this.propertyId = propertyId;
        this.tenantId = tenantId;
        this.duration = duration;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }
}
