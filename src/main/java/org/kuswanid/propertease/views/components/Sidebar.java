package org.kuswanid.propertease.views.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.screens.*;

public class Sidebar extends VBox {
    public Sidebar() {
        ImageView homeIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/icons/house.png")));
        homeIcon.setFitWidth(18);
        homeIcon.setPreserveRatio(true);
        Text homeLabel = new Text("Home");
        homeLabel.setStyle("-fx-font-size: 14px");
        HBox homeMenu = new HBox(12, homeIcon, homeLabel);
        homeMenu.setOnMouseClicked(mouseEvent -> Navigator.navigate(new HomeScreen()));

        ImageView propertiesIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/icons/layout-grid.png")));
        propertiesIcon.setFitWidth(18);
        propertiesIcon.setPreserveRatio(true);
        Text propertiesLabel = new Text("Properties");
        propertiesLabel.setStyle("-fx-font-size: 14px");
        HBox propertiesMenu = new HBox(12, propertiesIcon, propertiesLabel);
        propertiesMenu.setOnMouseClicked(mouseEvent -> Navigator.navigate(new PropertiesScreen()));

        ImageView tenantsIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/icons/users.png")));
        tenantsIcon.setFitWidth(18);
        tenantsIcon.setPreserveRatio(true);
        Text tenantsLabel = new Text("Tenants");
        tenantsLabel.setStyle("-fx-font-size: 14px");
        HBox tenantsMenu = new HBox(12, tenantsIcon, tenantsLabel);
        tenantsMenu.setOnMouseClicked(mouseEvent -> Navigator.navigate(new TenantsScreen()));

        ImageView leasesIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/icons/circle-dollar-sign.png")));
        leasesIcon.setFitWidth(18);
        leasesIcon.setPreserveRatio(true);
        Text leasesLabel = new Text("Leases");
        leasesLabel.setStyle("-fx-font-size: 14px");
        HBox leasesMenu = new HBox(12, leasesIcon, leasesLabel);
        leasesMenu.setOnMouseClicked(mouseEvent -> Navigator.navigate(new LeasesScreen()));

        ImageView profileIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/icons/user.png")));
        profileIcon.setFitWidth(18);
        profileIcon.setPreserveRatio(true);
        Text profileLabel = new Text("Profile");
        profileLabel.setStyle("-fx-font-size: 14px");
        HBox profileMenu = new HBox(12, profileIcon, profileLabel);
        profileMenu.setOnMouseClicked(mouseEvent -> Navigator.navigate(new ProfileScreen()));

        ImageView logoutIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/icons/log-out.png")));
        logoutIcon.setFitWidth(18);
        logoutIcon.setPreserveRatio(true);
        Text logoutLabel = new Text("Logout");
        logoutLabel.setStyle("-fx-font-size: 14px");
        HBox logoutMenu = new HBox(12, logoutIcon, logoutLabel);
        logoutMenu.setOnMouseClicked(mouseEvent -> {
            Navigator.replace(new LoginScreen());
        });

        this.getChildren().addAll(homeMenu, propertiesMenu, tenantsMenu, leasesMenu, profileMenu, logoutMenu);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");
    }
}
