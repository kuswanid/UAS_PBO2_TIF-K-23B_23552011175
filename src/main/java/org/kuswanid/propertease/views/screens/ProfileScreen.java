package org.kuswanid.propertease.views.screens;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.models.User;
import org.kuswanid.propertease.services.UserService;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

public class ProfileScreen extends HBox {
    private UserService userService = UserService.getInstance();
    private User currentUser;

    public ProfileScreen() {
        currentUser = userService.getCurrentUser();
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Profile");

        Text name = new Text("Name: " + currentUser.getName());
        Text email = new Text("Email: " + currentUser.getEmail());
        Text role = new Text("Role: " + currentUser.getRole());

        VBox main = new VBox(24, appbar, name, email, role);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
