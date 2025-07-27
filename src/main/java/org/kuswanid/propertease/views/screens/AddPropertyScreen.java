package org.kuswanid.propertease.views.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.services.PropertyService;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

import java.util.List;

public class AddPropertyScreen extends HBox {
    private PropertyService propertyService = new PropertyService();

    public AddPropertyScreen() {
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Add New Property");

        Text nameLabel = new Text("Name");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter property name");

        Text typeLabel = new Text("Type");
        ComboBox<String> typeList = new ComboBox<>();
        typeList.getItems().addAll(List.of("Apartment", "House", "Office", "Studio", "Villa"));
        typeList.setValue("Apartment");

        Text descriptionLabel = new Text("Description");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Short description of the property");

        Text addressLabel = new Text("Address");
        TextField addressField = new TextField();
        addressField.setPromptText("Enter property address");

        Text rentPriceLabel = new Text("Rent Price");
        TextField rentPriceField = new TextField();
        rentPriceField.setPromptText("Enter monthly rent price");

        Text statusLabel = new Text("Status");
        ComboBox<String> statusList = new ComboBox<>();
        statusList.getItems().addAll(List.of("Available", "Not Available"));
        statusList.setValue("Available");

        VBox formSection = new VBox(8, nameLabel, nameField, typeLabel, typeList, descriptionLabel, descriptionField, addressLabel, addressField, rentPriceLabel, rentPriceField, statusLabel, statusList);

        Button addPropertyButton = new Button("Add Property");
        addPropertyButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        addPropertyButton.setOnMouseClicked(mouseEvent -> {
            propertyService.add(
                    addressField.getText(),
                    descriptionField.getText(),
                    nameField.getText(),
                    Double.parseDouble(rentPriceField.getText()),
                    statusList.getValue(),
                    typeList.getValue()
            );
            Navigator.back();
            Navigator.replace(new PropertiesScreen());
        });

        VBox main = new VBox(24, appbar, formSection, addPropertyButton);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
