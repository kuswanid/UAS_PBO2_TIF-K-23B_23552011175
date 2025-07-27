package org.kuswanid.propertease.repositories;

import org.kuswanid.propertease.models.Tenant;
import org.kuswanid.propertease.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TenantRepository {
    public boolean add(String address, String email, String name, String phone) {
        String sql = "INSERT INTO tenants (address, email, name, phone) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, address);
            statement.setString(2, email);
            statement.setString(3, name);
            statement.setString(4, phone);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Tenant> getAll() {
        List<Tenant> data = new ArrayList<>();
        String sql = "SELECT * FROM tenants";

        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                data.add(new Tenant(
                        result.getInt("id"),
                        result.getString("address"),
                        result.getString("email"),
                        result.getString("name"),
                        result.getString("phone")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public Tenant getById(int id) {
        String sql = "SELECT * FROM tenants WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Tenant(
                        result.getInt("id"),
                        result.getString("address"),
                        result.getString("email"),
                        result.getString("name"),
                        result.getString("phone")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
