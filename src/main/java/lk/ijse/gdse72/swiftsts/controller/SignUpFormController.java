package lk.ijse.gdse72.swiftsts.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.gdse72.swiftsts.bo.BOFactory;
import lk.ijse.gdse72.swiftsts.bo.custom.SignInBO;
import lk.ijse.gdse72.swiftsts.bo.custom.SignUpBO;
import lk.ijse.gdse72.swiftsts.util.SendMailUtil;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class SignUpFormController implements Initializable {

    SignUpBO signUpBO = (SignUpBO) BOFactory.getInstance().getBO(BOFactory.BOType.SIGN_UP);

    @FXML
    public JFXTextField txtUsername;

    @FXML
    public JFXTextField txtEmail;

    @FXML
    public JFXButton btnSendPassword;
    @FXML
    public JFXButton btnBack;

    @FXML
    private Label lblAlert;

    @FXML
    private AnchorPane paneSignUp;

    private String getUserPassword() {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        try {
            String password = signUpBO.getUserPassword(username, email);
            return password != null ? password : "No password found for given credentials.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving password.";
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isValidEmail(newValue)) {
                    txtEmail.setFocusColor(Paint.valueOf("#ffd600"));
                } else {
                    txtEmail.setFocusColor(Paint.valueOf("red"));
                }
            }
        });
    }

    @FXML
    public void btnSendPasswordOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String email = txtEmail.getText();

        if (username.isEmpty() || email.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter both username and email.").show();
            return;
        }

        if (!isValidEmail(email)) {
            txtEmail.setFocusColor(Paint.valueOf("red"));
            new Alert(Alert.AlertType.ERROR, "Invalid email format.").show();
            return;
        }

        String userPassword =  getUserPassword(); // Replace with actual password retrieval logic
        String subject = "Your Password";
        String messageBody = "Hello " + username + ",\n\nYour password is: " + userPassword + "\n\nThank you.";
        try {
            SendMailUtil.sendEmail(email, subject, messageBody);
            new Alert(Alert.AlertType.INFORMATION, "Password sent successfully!").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send password: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnBackOnAction(ActionEvent actionEvent) {
        paneSignUp.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SignInForm.fxml"));
            paneSignUp.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}