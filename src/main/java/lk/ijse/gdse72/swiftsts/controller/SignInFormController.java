package lk.ijse.gdse72.swiftsts.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse72.swiftsts.bo.BOFactory;
import lk.ijse.gdse72.swiftsts.bo.custom.SignInBO;
import lk.ijse.gdse72.swiftsts.bo.custom.impl.SignInBOImpl;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInFormController {

    SignInBO signInBO = (SignInBO) BOFactory.getInstance().getBO(BOFactory.BOType.SIGN_IN);

    @FXML
    public Label lblInvalidUserName;
    @FXML
    public Label lblInvalidPassword;
    @FXML
    private JFXButton btnSignIn;

    @FXML
    private Label lblSignUp;

    @FXML
    private Label txtSignIn;

    @FXML
    private Label txtSignUp;

    @FXML
    private AnchorPane signInPage;

    @FXML
    private JFXPasswordField txtpassword;

    @FXML
    private JFXTextField txtusername;

    @FXML
    void lblSignUpOnClicked(MouseEvent event) throws IOException {
        signInPage.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SignUpForm.fxml"));
        signInPage.getChildren().add(anchorPane);
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException {
        String username = txtusername.getText();
        String password = txtpassword.getText();


        boolean isUsernameValid = false;
        boolean validateCredentials = false;
        try {
            isUsernameValid = signInBO.isUsernameValid(username);
            validateCredentials = signInBO.validateCredentials(username, password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        if (isUsernameValid) {
            lblInvalidUserName.setVisible(false);
            if (validateCredentials) {
                lblInvalidPassword.setVisible(false);
                loadDashboard("/view/DashBoardForm.fxml");
            } else {
                lblInvalidPassword.setVisible(true);
                txtpassword.setFocusColor(Paint.valueOf("red"));
                lblInvalidPassword.setText("Invalid Password. Try Again !");
            }
        } else {
            lblInvalidUserName.setVisible(true);
            txtusername.setFocusColor(Paint.valueOf("red"));
            lblInvalidUserName.setText("Invalid Username. Try Again !");
        }
    }


    private void loadDashboard(String fxmlPath) throws IOException {
        Window window = signInPage.getScene().getWindow();
        window.hide();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setMaximized(true);
        stage.setTitle("Swift Student Transfort Service");
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }

    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }

    public void txtSignInOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        signInPage.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SignInForm.fxml"));
        signInPage.getChildren().add(anchorPane);
    }

    public void txtSignUpOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        signInPage.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SignUpForm.fxml"));
        signInPage.getChildren().add(anchorPane);
    }
}