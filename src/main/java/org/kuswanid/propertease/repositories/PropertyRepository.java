package org.kuswanid.propertease.repositories;

import org.kuswanid.propertease.models.Property;
import org.kuswanid.propertease.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {
    public boolean add(String address, String description, String name, double rentPrice, String status, String type) {
        String sql = "INSERT INTO properties (address, description, name, rent_price, status, type) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, address);
            statement.setString(2, description);
            statement.setString(3, name);
            statement.setDouble(4, rentPrice);
            statement.setString(5, status);
            statement.setString(6, type);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM properties WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Property> getAll() {
        List<Property> data = new ArrayList<>();
        String sql = "SELECT * FROM properties";

        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                data.add(new Property(
                        result.getInt("id"),
                        result.getString("address"),
                        result.getString("description"),
                        result.getString("name"),
                        result.getDouble("rent_price"),
                        result.getString("status"),
                        result.getString("type")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public Property getById(int id) {
        String sql = "SELECT * FROM properties WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Property(
                        result.getInt("id"),
                        result.getString("address"),
                        result.getString("description"),
                        result.getString("name"),
                        result.getDouble("rent_price"),
                        result.getString("status"),
                        result.getString("type")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(int id, String address, String description, String name, double rentPrice, String status, String type) {
        String sql = "UPDATE properties SET address = ?, description = ?, name = ?, rent_price = ?, status = ?, type = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, address);
            statement.setString(2, description);
            statement.setString(3, name);
            statement.setDouble(4, rentPrice);
            statement.setString(5, status);
            statement.setString(6, type);
            statement.setInt(7, id);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
