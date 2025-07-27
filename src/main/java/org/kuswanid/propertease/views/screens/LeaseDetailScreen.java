package org.kuswanid.propertease.views.screens;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.kuswanid.propertease.views.components.Appbar;
import org.kuswanid.propertease.views.components.Sidebar;

public class LeaseDetailScreen extends HBox {
    public LeaseDetailScreen(int id) {
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Detail Lease");

        VBox main = new VBox(24, appbar);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
