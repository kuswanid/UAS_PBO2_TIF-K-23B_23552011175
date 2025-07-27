package org.kuswanid.propertease.views.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.models.Tenant;
import org.kuswanid.propertease.services.TenantService;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

import java.util.List;

public class TenantsScreen extends HBox {
    private TenantService tenantService = new TenantService();
    private List<Tenant> tenants;

    public TenantsScreen() {
        tenants = tenantService.getAll();
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Tenants");

        Button addTenantButton = new Button("Add New Tenant");
        addTenantButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        addTenantButton.setOnMouseClicked(mouseEvent -> Navigator.navigate(new AddTenantScreen()));

        HBox tenantList = new HBox();
        tenants.forEach(tenant -> {
            Text name = new Text(tenant.getName());
            Text email = new Text(tenant.getEmail());

            VBox tenantCard = new VBox(name, email);
            tenantCard.setStyle("-fx-background-color: #ffffff; -fx-padding: 8px");
            tenantCard.setOnMouseClicked(mouseEvent -> Navigator.navigate(new TenantDetailScreen(tenant.getId())));
            tenantList.getChildren().addAll(tenantCard);
        });

        VBox main = new VBox(24, appbar, addTenantButton, new ScrollPane(tenantList));
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
