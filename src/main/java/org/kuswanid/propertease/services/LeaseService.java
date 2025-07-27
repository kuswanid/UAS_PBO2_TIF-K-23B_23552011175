package org.kuswanid.propertease.services;

import org.kuswanid.propertease.models.Lease;
import org.kuswanid.propertease.repositories.LeaseRepository;

import java.util.List;

public class LeaseService {
    private final LeaseRepository leaseRepository = new LeaseRepository();

    public boolean add(int propertyId, int tenantId, int duration) {
        return leaseRepository.add(propertyId, tenantId, duration, "Waiting Payment");
    }

    public List<Lease> getAll() {
        return leaseRepository.getAll();
    }
}
