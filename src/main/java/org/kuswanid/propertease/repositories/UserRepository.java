package org.kuswanid.propertease.repositories;

import org.kuswanid.propertease.models.User;
import org.kuswanid.propertease.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("email"),
                        result.getString("name"),
                        result.getString("password"),
                        result.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(String email, String name, String password, String role) {
        String sql = "INSERT INTO users (email, name, password, role) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, role);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
