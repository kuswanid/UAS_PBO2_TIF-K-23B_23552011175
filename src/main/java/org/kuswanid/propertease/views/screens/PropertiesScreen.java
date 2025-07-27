package org.kuswanid.propertease.views.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.models.Property;
import org.kuswanid.propertease.services.PropertyService;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

import java.util.List;

public class PropertiesScreen extends HBox {
    private final PropertyService propertyService = new PropertyService();
    private List<Property> properties;

    public PropertiesScreen() {
        properties = propertyService.getAll();
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Properties");

        Button addPropertyButton = new Button("Add New Property");
        addPropertyButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        addPropertyButton.setOnMouseClicked(mouseEvent -> Navigator.navigate(new AddPropertyScreen()));

        HBox propertyList = new HBox();
        properties.forEach(property -> {
            Text name = new Text(property.getName());
            name.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");
            Text type = new Text(property.getType());
            Text status = new Text(property.getStatus());
            status.setStyle("-fx-fill: #0ea5e9;");
            Text rentPrice = new Text("Rp" + property.getRentPrice());

            VBox propertyCard = new VBox(name, type, rentPrice, status);
            propertyCard.setStyle("-fx-background-color: #ffffff; -fx-padding: 8px");
            propertyCard.setOnMouseClicked(mouseEvent -> Navigator.navigate(new PropertyDetailScreen(property.getId())));

            propertyList.getChildren().addAll(propertyCard);
        });

        VBox main = new VBox(24, appbar, addPropertyButton, new ScrollPane(propertyList));
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
