package org.kuswanid.propertease.views.screens;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.models.Property;
import org.kuswanid.propertease.services.PropertyService;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

public class PropertyDetailScreen extends HBox {
    private PropertyService propertyService = new PropertyService();
    private Property property;

    public PropertyDetailScreen(int id) {
        property = propertyService.getById(id);
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Detail Property");

        Text name = new Text("Name: " + property.getName());
        Text type = new Text("Type: " + property.getType());
        Text description = new Text("Description: " + property.getDescription());
        Text address = new Text("Address: " + property.getDescription());
        Text rentPrice = new Text("Rent price: Rp" + property.getRentPrice());
        Text status = new Text("Status: " + property.getStatus());

        VBox propertyInfo = new VBox(8, name, type, description, address, rentPrice, status);

        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        editButton.setOnMouseClicked(mouseEvent -> Navigator.navigate(new EditPropertyScreen(property.getId())));

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        deleteButton.setOnMouseClicked(mouseEvent -> {
            propertyService.delete(property.getId());
            Navigator.back();
            Navigator.replace(new PropertiesScreen());
        });

        HBox actionSection = new HBox(8, editButton, deleteButton);

        VBox main = new VBox(24, appbar, propertyInfo, actionSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
