package org.kuswanid.propertease.views.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.models.Property;
import org.kuswanid.propertease.models.Tenant;
import org.kuswanid.propertease.services.LeaseService;
import org.kuswanid.propertease.services.PropertyService;
import org.kuswanid.propertease.services.TenantService;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

import java.util.List;

public class AddLeaseScreen extends HBox {
    private LeaseService leaseService = new LeaseService();
    private PropertyService propertyService = new PropertyService();
    private TenantService tenantService = new TenantService();
    private List<Property> properties;
    private List<Tenant> tenants;

    public AddLeaseScreen() {
        properties = propertyService.getAll();
        tenants = tenantService.getAll();
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Add New Lease");

        Text propertyLabel = new Text("Property");
        ComboBox<String> propertyList = new ComboBox<>();
        properties.forEach(property -> {
            propertyList.getItems().addAll(property.getName());
        });
        propertyList.setValue(properties.getFirst().getName());

        Text tenantLabel = new Text("Tenant");
        ComboBox<String> tenantList = new ComboBox<>();
        tenants.forEach(tenant -> {
            tenantList.getItems().addAll(tenant.getName());
        });
        tenantList.setValue(tenants.getFirst().getName());

        Text totalPrice = new Text();

        Text durationLabel = new Text("Duration");
        TextField durationField = new TextField();
        durationField.setPromptText("Enter duration rent");
        durationField.textProperty().addListener((observableValue, string, t1) -> {
            if (!t1.isEmpty()) {
                Property selectedProperty = properties.stream().filter(property -> property.getName().equalsIgnoreCase(propertyList.getValue())).findFirst().orElse(null);
                totalPrice.setText("Total Price: Rp" + Integer.parseInt(t1) * selectedProperty.getRentPrice());
            }
        });


        VBox formSection = new VBox(8, propertyLabel, propertyList, tenantLabel, tenantList, durationLabel, durationField);

        Button addLeaseButton = new Button("Add Lease");
        addLeaseButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        addLeaseButton.setOnMouseClicked(mouseEvent -> {
            int selectedPropertyId = properties.stream().filter(property -> property.getName().equalsIgnoreCase(propertyList.getValue())).findFirst().orElse(null).getId();
            int selectedTenantId = tenants.stream().filter(tenant -> tenant.getName().equalsIgnoreCase(tenantList.getValue())).findFirst().orElse(null).getId();

            leaseService.add(
                    selectedPropertyId,
                    selectedTenantId,
                    Integer.parseInt(durationField.getText())
            );
            Navigator.back();
            Navigator.replace(new LeasesScreen());
        });

        VBox main = new VBox(24, appbar, formSection, totalPrice, addLeaseButton);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
