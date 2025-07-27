package org.kuswanid.propertease.services;

import org.kuswanid.propertease.models.User;
import org.kuswanid.propertease.repositories.UserRepository;

public class UserService {
    private static UserService instance;
    private User currentUser;
    private final UserRepository userRepository = new UserRepository();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean login(String email, String password) {
        User userResult = userRepository.login(email, password);
        if (userResult != null) {
            currentUser = userResult;
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        currentUser = null;
    }

    public boolean register(String email, String name, String password) {
        return userRepository.register(email, name, password, "Admin");
    }
}
