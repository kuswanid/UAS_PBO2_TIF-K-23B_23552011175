package org.kuswanid.propertease.services;

import org.kuswanid.propertease.models.Property;
import org.kuswanid.propertease.repositories.PropertyRepository;

import java.util.List;

public class PropertyService {
    private final PropertyRepository propertyRepository = new PropertyRepository();

    public boolean add(String address, String description, String name, double rentPrice, String status, String type) {
        return propertyRepository.add(address, description, name, rentPrice, status, type);
    }

    public boolean delete(int id) {
        return propertyRepository.delete(id);
    }

    public List<Property> getAll() {
        return propertyRepository.getAll();
    }

    public Property getById(int id) {
        return propertyRepository.getById(id);
    }

    public boolean update(int id, String address, String description, String name, double rentPrice, String status, String type) {
        return propertyRepository.update(id, address, description, name, rentPrice, status, type);
    }
}
