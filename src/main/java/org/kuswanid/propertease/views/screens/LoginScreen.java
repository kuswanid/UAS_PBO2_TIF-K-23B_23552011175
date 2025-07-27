package org.kuswanid.propertease.views.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kuswanid.propertease.services.UserService;
import org.kuswanid.propertease.utils.Navigator;

public class LoginScreen extends VBox {
    private UserService userService = UserService.getInstance();

    public LoginScreen() {
        render();
    }

    private void render() {
        Text pageTitle = new Text("Welcome Back");
        pageTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);

        VBox formSection = new VBox(8, emailField, passwordField);
        formSection.setAlignment(Pos.CENTER);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        loginButton.setOnMouseClicked(mouseEvent -> {
            userService.login(
                    emailField.getText(),
                    passwordField.getText()
            );
            Navigator.replace(new HomeScreen());
        });

        Text alternativeText = new Text("Don't have an account?");
        Text alternativeButton = new Text("Register");
        alternativeButton.setStyle("-fx-fill: #0ea5e9; -fx-font-weight: bold");
        alternativeButton.setOnMouseClicked(mouseEvent -> Navigator.replace(new RegisterScreen()));

        HBox alternativeSection = new HBox(4, alternativeText, alternativeButton);
        alternativeSection.setAlignment(Pos.CENTER);

        this.getChildren().addAll(pageTitle, formSection, loginButton, alternativeSection);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(24);
    }
}
