package org.kuswanid.propertease.views.screens;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.models.User;
import org.kuswanid.propertease.services.UserService;
import org.kuswanid.propertease.views.components.Sidebar;

public class HomeScreen extends HBox {
    private UserService userService = UserService.getInstance();
    private User currentUser;

    public HomeScreen() {
        currentUser = userService.getCurrentUser();
        render();
    }

    private void render() {
        Text greeting = new Text("Hello " + currentUser.getName());
        greeting.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Text suggestion = new Text("Every journey begins with the right place.");
        VBox greetingSection = new VBox(16, greeting, suggestion);

        VBox main = new VBox(48, greetingSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
