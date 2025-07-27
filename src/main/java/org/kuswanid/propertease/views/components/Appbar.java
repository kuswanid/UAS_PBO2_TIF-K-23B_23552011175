package org.kuswanid.propertease.views.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.utils.Navigator;

public class Appbar extends HBox {
    public Appbar(String title) {
        ImageView backIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/icons/arrow-left.png")));
        backIcon.setOnMouseClicked(mouseEvent -> Navigator.back());

        Text screenTitle = new Text(title);
        screenTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        this.getChildren().addAll(backIcon, screenTitle);
        this.setSpacing(12);
    }
}
