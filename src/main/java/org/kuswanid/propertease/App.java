package org.kuswanid.propertease;

import javafx.application.Application;
import javafx.stage.Stage;
import org.kuswanid.propertease.utils.Navigator;
import org.kuswanid.propertease.views.screens.LoginScreen;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("PropertEase");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();

        Navigator.init(stage);
        Navigator.navigate(new LoginScreen());
    }

    public static void main(String[] args) { launch(); }
}
