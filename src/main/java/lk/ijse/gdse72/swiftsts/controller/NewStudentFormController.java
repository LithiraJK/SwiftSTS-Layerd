package lk.ijse.gdse72.swiftsts.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.gdse72.swiftsts.bo.BOFactory;
import lk.ijse.gdse72.swiftsts.bo.custom.NewStudentBO;
import lk.ijse.gdse72.swiftsts.bo.custom.StudentRegistrationBO;
import lk.ijse.gdse72.swiftsts.bo.custom.impl.NewStudentBOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewStudentFormController implements Initializable {

    NewStudentBO newStudentBO = (NewStudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.NEW_STUDENT);
    StudentRegistrationBO studentRegistrationBO = (StudentRegistrationBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT_REGISTRATION);


    @FXML
    public AnchorPane paneRegister;
    @FXML
    public JFXButton btnSave;
    @FXML
    public Label lblStudentId;
    @FXML
    public JFXTextField txtStudentGrade;
    @FXML
    public JFXTextField txtPhoneNo;
    @FXML
    public JFXTextField txtStudentName;
    @FXML
    public JFXTextField txtParentName;

    @FXML
    public JFXButton btnDiscard;
    @FXML
    public JFXTextField txtAddress;
    @FXML
    public JFXTextField txtEmail;
    @FXML
    private AnchorPane overlayPane;
    @FXML
    private AnchorPane paneStudent;



    public void setOverlayPane(AnchorPane overlayPane, AnchorPane paneStudent) {
        this.overlayPane = overlayPane;
        this.paneStudent = paneStudent;
    }

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String studentId = lblStudentId.getText();
        String studentName = txtStudentName.getText();
        String parentName = txtParentName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String studentGrade = txtStudentGrade.getText();
        String phoneNo = txtPhoneNo.getText();

        // Define regex patterns for validation
        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)$";
        String gradePattern = "^[a-zA-Z0-9]+$";

        boolean isValidName = studentName.matches(namePattern);
        boolean isValidParentName = parentName.matches(namePattern);
        boolean isValidAddress = address.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhoneNo = phoneNo.matches(phonePattern);
        boolean isValidGrade = studentGrade.matches(gradePattern);

        txtStudentName.setFocusColor(Paint.valueOf("black"));
        txtParentName.setFocusColor(Paint.valueOf("black"));
        txtAddress.setFocusColor(Paint.valueOf("black"));
        txtEmail.setFocusColor(Paint.valueOf("black"));
        txtPhoneNo.setFocusColor(Paint.valueOf("black"));
        txtStudentGrade.setFocusColor(Paint.valueOf("black"));

        if (!isValidName) {
            txtStudentName.setFocusColor(Paint.valueOf("red"));
        }
        if (!isValidParentName) {
            txtParentName.setFocusColor(Paint.valueOf("red"));
        }
        if (!isValidAddress) {
            txtAddress.setFocusColor(Paint.valueOf("red"));
        }
        if (!isValidEmail) {
            txtEmail.setFocusColor(Paint.valueOf("red"));
        }
        if (!isValidPhoneNo) {
            txtPhoneNo.setFocusColor(Paint.valueOf("red"));
        }
        if (!isValidGrade) {
            txtStudentGrade.setFocusColor(Paint.valueOf("red"));
        }

        if (isValidName && isValidParentName && isValidAddress && isValidEmail && isValidPhoneNo && isValidGrade) {
            StudentDto studentDto = new StudentDto(studentId, studentName, parentName, address, email, studentGrade, phoneNo, 0.0);
            boolean isSaved = studentRegistrationBO.saveNewStudent(studentDto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student saved successfully!").show();
                // Close the current form and return to the root page
                paneStudent.getChildren().clear();
                AnchorPane rootPane = FXMLLoader.load(getClass().getResource("/view/StudentRegistration.fxml"));
                paneStudent.getChildren().add(rootPane);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save student!").show();
            }
        }
    }

    @FXML
    public void btnDiscardOnAction(ActionEvent actionEvent) {
        paneStudent.getChildren().remove(overlayPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String nextStudentId = newStudentBO.getNewId();
            lblStudentId.setText(nextStudentId);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while generating the student ID: " + e.getMessage()).show();
        }

        addValidationListeners();
    }

    private void addValidationListeners() {
        // Define regex patterns
        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)$";
        String gradePattern = "^[a-zA-Z0-9]+$";

        // Add listener for each field
        txtStudentName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(namePattern)) {
                txtStudentName.setFocusColor(Paint.valueOf("red"));
            } else {
                txtStudentName.setFocusColor(Paint.valueOf("black"));
            }
        });

        txtParentName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(namePattern)) {
                txtParentName.setFocusColor(Paint.valueOf("red"));
            } else {
                txtParentName.setFocusColor(Paint.valueOf("black"));
            }
        });

        txtAddress.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(namePattern)) {
                txtAddress.setFocusColor(Paint.valueOf("red"));
            } else {
                txtAddress.setFocusColor(Paint.valueOf("black"));
            }
        });

        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(emailPattern)) {
                txtEmail.setFocusColor(Paint.valueOf("red"));
            } else {
                txtEmail.setFocusColor(Paint.valueOf("black"));
            }
        });

        txtPhoneNo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(phonePattern)) {
                txtPhoneNo.setFocusColor(Paint.valueOf("red"));
            } else {
                txtPhoneNo.setFocusColor(Paint.valueOf("black"));
            }
        });

        txtStudentGrade.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(gradePattern)) {
                txtStudentGrade.setFocusColor(Paint.valueOf("red"));
            } else {
                txtStudentGrade.setFocusColor(Paint.valueOf("black"));
            }
        });
    }
}