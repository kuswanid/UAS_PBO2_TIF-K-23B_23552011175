package org.kuswanid.propertease.views.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

public class EditPropertyScreen extends HBox{
    private PropertyService propertyService = new PropertyService();
    private Property property;

    public EditPropertyScreen(int id) {
        property = propertyService.getById(id);
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Edit Property");

        Text nameLabel = new Text("Name");
        TextField nameField = new TextField(property.getName());
        nameField.setPromptText("Enter property name");

        Text typeLabel = new Text("Type");
        ComboBox<String> typeList = new ComboBox<>();
        typeList.getItems().addAll(List.of("Apartment", "House", "Office", "Studio", "Villa"));
        typeList.setValue(property.getType());

        Text descriptionLabel = new Text("Description");
        TextField descriptionField = new TextField(property.getDescription());
        descriptionField.setPromptText("Short description of the property");

        Text addressLabel = new Text("Address");
        TextField addressField = new TextField(property.getAddress());
        addressField.setPromptText("Enter property address");

        Text rentPriceLabel = new Text("Rent Price");
        TextField rentPriceField = new TextField(String.valueOf(property.getRentPrice()));
        rentPriceField.setPromptText("Enter monthly rent price");

        Text statusLabel = new Text("Status");
        ComboBox<String> statusList = new ComboBox<>();
        statusList.getItems().addAll(List.of("Available", "Not Available"));
        statusList.setValue(property.getStatus());

        VBox formSection = new VBox(8, nameLabel, nameField, typeLabel, typeList, descriptionLabel, descriptionField, addressLabel, addressField, rentPriceLabel, rentPriceField, statusLabel, statusList);

        Button editPropertyButton = new Button("Edit Property");
        editPropertyButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        editPropertyButton.setOnMouseClicked(mouseEvent -> {
            propertyService.update(
                    property.getId(),
                    addressField.getText(),
                    descriptionField.getText(),
                    nameField.getText(),
                    Double.parseDouble(rentPriceField.getText()),
                    statusList.getValue(),
                    typeList.getValue()
            );
            Navigator.back();
            Navigator.replace(new PropertyDetailScreen(property.getId()));
        });

        VBox main = new VBox(24, appbar, formSection, editPropertyButton);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
