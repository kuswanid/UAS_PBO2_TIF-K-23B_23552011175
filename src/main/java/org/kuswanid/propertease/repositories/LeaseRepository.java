package org.kuswanid.propertease.repositories;

import org.kuswanid.propertease.models.Lease;
import org.kuswanid.propertease.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeaseRepository {
    public boolean add(int propertyId, int tenantId, int duration, String status) {
        String sql = "INSERT INTO leases (property_id, tenant_id, duration, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, propertyId);
            statement.setInt(2, tenantId);
            statement.setInt(3, duration);
            statement.setString(4, status);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Lease> getAll() {
        List<Lease> data = new ArrayList<>();
        String sql = "SELECT * FROM leases";

        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                data.add(new Lease(
                        result.getInt("id"),
                        result.getInt("property_id"),
                        result.getInt("tenant_id"),
                        result.getInt("duration"),
                        result.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
