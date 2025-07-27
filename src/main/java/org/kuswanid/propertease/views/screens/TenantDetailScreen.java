package org.kuswanid.propertease.views.screens;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.models.Tenant;
import org.kuswanid.propertease.services.TenantService;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

public class TenantDetailScreen extends HBox {
    private TenantService tenantService = new TenantService();
    private Tenant tenant;

    public TenantDetailScreen(int id) {
        tenant = tenantService.getById(id);
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Detail Tenant");

        Text name = new Text("Name: " + tenant.getName());
        Text email = new Text("Email: " + tenant.getEmail());
        Text phone = new Text("Phone: " + tenant.getPhone());
        Text address = new Text("Address: " + tenant.getAddress());

        VBox tenantInfo = new VBox(8, name, email, phone, address);

        VBox main = new VBox(24, appbar, tenantInfo);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
