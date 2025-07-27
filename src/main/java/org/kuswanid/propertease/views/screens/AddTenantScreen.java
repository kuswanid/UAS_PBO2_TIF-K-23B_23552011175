package org.kuswanid.propertease.views.screens;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.services.TenantService;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

public class AddTenantScreen extends HBox {
    private TenantService tenantService = new TenantService();

    public AddTenantScreen() {
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Add New Tenant");

        Text nameLabel = new Text("Name");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter tenant name");

        Text emailLabel = new Text("Email");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter tenant email");

        Text phoneLabel = new Text("Phone");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter tenant phone");

        Text addressLabel = new Text("Address");
        TextField addressField = new TextField();
        addressField.setPromptText("Enter tenant address");

        VBox formSection = new VBox(8, nameLabel, nameField, emailLabel, emailField, phoneLabel, phoneField, addressLabel, addressField);

        Button addTenantButton = new Button("Add Tenant");
        addTenantButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        addTenantButton.setOnMouseClicked(mouseEvent -> {
            tenantService.add(
                    addressField.getText(),
                    emailField.getText(),
                    nameField.getText(),
                    phoneField.getText()
            );
            Navigator.back();
            Navigator.navigate(new TenantsScreen());
        });

        VBox main = new VBox(24, appbar, formSection, addTenantButton);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
