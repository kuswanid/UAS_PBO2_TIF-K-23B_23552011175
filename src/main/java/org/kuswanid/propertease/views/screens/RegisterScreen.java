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

public class RegisterScreen extends VBox {
    private UserService userService = new UserService();

    public RegisterScreen() {
        render();
    }

    private void render() {
        Text pageTitle = new Text("Create Account");
        pageTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setMaxWidth(200);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);

        VBox formSection = new VBox(8, nameField, emailField, passwordField);
        formSection.setAlignment(Pos.CENTER);

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9;");
        registerButton.setOnMouseClicked(mouseEvent -> {
            userService.register(
                    emailField.getText(),
                    nameField.getText(),
                    passwordField.getText()
            );
            Navigator.replace(new LoginScreen());
        });

        Text alternativeText = new Text("Already have account?");
        Text alternativeButton = new Text("Login");
        alternativeButton.setStyle("-fx-fill: #0ea5e9; -fx-font-weight: bold");
        alternativeButton.setOnMouseClicked(mouseEvent -> Navigator.replace(new LoginScreen()));

        HBox alternativeSection = new HBox(4, alternativeText, alternativeButton);
        alternativeSection.setAlignment(Pos.CENTER);

        this.getChildren().addAll(pageTitle, formSection, registerButton, alternativeSection);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(24);
    }
}
