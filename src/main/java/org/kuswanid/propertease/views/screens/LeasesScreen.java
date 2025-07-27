package org.kuswanid.propertease.views.screens;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.models.Lease;
import org.kuswanid.propertease.services.LeaseService;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

import java.util.List;

public class LeasesScreen extends HBox {
    private LeaseService leaseService = new LeaseService();
    private List<Lease> leases;

    public LeasesScreen() {
        leases = leaseService.getAll();
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Leases");

        Button addLeaseButton = new Button("Add New Lease");
        addLeaseButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        addLeaseButton.setOnMouseClicked(mouseEvent -> Navigator.navigate(new AddLeaseScreen()));

        HBox leaseList = new HBox();
        leases.forEach(lease -> {
            Text duration = new Text(lease.getDuration() + " Days");
            Text status = new Text(lease.getStatus());

            VBox leaseCard = new VBox(duration, status);
            leaseCard.setStyle("-fx-background-color: #ffffff; -fx-padding: 8px");
            leaseCard.setOnMouseClicked(mouseEvent -> Navigator.navigate(new LeaseDetailScreen(lease.getId())));

            leaseList.getChildren().addAll(leaseCard);
        });

        VBox main = new VBox(24, appbar, addLeaseButton, new ScrollPane(leaseList));
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
