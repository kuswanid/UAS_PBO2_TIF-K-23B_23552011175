package org.kuswanid.propertease.services;

import org.kuswanid.propertease.models.Tenant;
import org.kuswanid.propertease.repositories.TenantRepository;

import java.util.List;

public class TenantService {
    private final TenantRepository tenantRepository = new TenantRepository();

    public boolean add(String address, String email, String name, String phone) {
        return tenantRepository.add(address, email, name, phone);
    }

    public List<Tenant> getAll() {
        return tenantRepository.getAll();
    }

    public Tenant getById(int id) {
        return tenantRepository.getById(id);
    }
}
