package lk.ijse.gdse72.swiftsts.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.gdse72.swiftsts.bo.BOFactory;
import lk.ijse.gdse72.swiftsts.bo.custom.StudentRegistrationBO;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class StudentRegistrationController implements Initializable {

    StudentRegistrationBO studentRegistrationBO = (StudentRegistrationBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT_REGISTRATION);

    @FXML
    public Label lblManageStudentOnClick;
    @FXML
    public JFXComboBox<String> cmbDestination;
    @FXML
    public JFXComboBox<String> cmbVehicle;
    @FXML
    public JFXButton btnReset;
    @FXML
    public Label txtDayPrice;


    @FXML
    private JFXButton btnNewRoute;

    @FXML
    private JFXButton btnNewStudent;

    @FXML
    private JFXButton btnNewVehicle;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXComboBox<String> cmbRoute;

    @FXML
    private TableColumn<StudentRegistrationDetailsTM, Double> colDayPrice;

    @FXML
    private TableColumn<StudentRegistrationDetailsTM, String> colDestination;

    @FXML
    private TableColumn<StudentRegistrationDetailsTM, Double> colDistance;

    @FXML
    private TableColumn<StudentRegistrationDetailsTM, String> colRegId;

    @FXML
    private TableColumn<StudentRegistrationDetailsTM, String> colPickupLocation;

    @FXML
    private TableColumn<StudentRegistrationDetailsTM, Date> colRegistrationDate;

    @FXML
    private TableColumn<StudentRegistrationDetailsTM, String> colRouteId;

    @FXML
    public TableColumn<StudentRegistrationDetailsTM, String> colStudentName;

    @FXML
    private TableColumn<StudentRegistrationDetailsTM, String> colVehicleID;

    @FXML
    private TableView<StudentRegistrationDetailsTM> tblStudentRegistration;

    @FXML
    public Label lableStudentId;

    @FXML
    private Label lblAvailableSeat;

    @FXML
    private Label lblDate;

    @FXML
    public Label lblPickupLocation;

    @FXML
    public Label lblRegistrationId;

    @FXML
    private JFXToggleButton trbtnCalculateDistance;

    @FXML
    public JFXTextField txtDistance;

    @FXML
    public JFXComboBox<String> cmbStudentName;

    @FXML
    private AnchorPane paneRegistration;



    public static double dayPrice = 0.00;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colRegId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colRouteId.setCellValueFactory(new PropertyValueFactory<>("routeId"));
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colDayPrice.setCellValueFactory(new PropertyValueFactory<>("dayPrice"));
        colDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        colPickupLocation.setCellValueFactory(new PropertyValueFactory<>("pickupLocation"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));


        try {
            loadLastStudentName();
            loadRoutes();
            loadDestinations();
            loadVehicleIds();
            loadStudentRegistrationDetails();
            cmbStudentName.getSelectionModel().select(studentRegistrationBO.getAllStudentNames().get(studentRegistrationBO.getAllStudentNames().size() - 1));
            String nextRegistrationId = studentRegistrationBO.getNextRegistrationId();
            lblRegistrationId.setText(nextRegistrationId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        lblDate.setText(currentDate.format(formatter));

        cmbStudentName.setOnAction(event -> {

            String studentName = cmbStudentName.getSelectionModel().getSelectedItem();
            String selectedStudentId = null;
            try {
                selectedStudentId = studentRegistrationBO.getStudentIdByName(studentName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (selectedStudentId != null) {
                try {

                    lableStudentId.setText(selectedStudentId);
                    lblPickupLocation.setText(studentRegistrationBO.getPickupLocationById(selectedStudentId));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        cmbVehicle.setOnAction(event -> {
            String selectedVehicleId = cmbVehicle.getSelectionModel().getSelectedItem();
            if (selectedVehicleId != null) {
                try {
                    int availableSeats = studentRegistrationBO.getAvailableSeatCountByVehicleId(selectedVehicleId);
                    lblAvailableSeat.setText(String.valueOf(availableSeats));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean validateDistance() {
        try {
            double distance = Double.parseDouble(txtDistance.getText());
            if (distance > 0) {
                txtDistance.setStyle("-fx-focus-color: black;");
                return true;
            } else {
                txtDistance.setStyle("-fx-focus-color: red;");
                return false;
            }
        } catch (NumberFormatException e) {
            txtDistance.setStyle("-fx-focus-color: red;");
            return false;
        }
    }

    private boolean validateSeatCount() {
        try {
            int seatCount = Integer.parseInt(lblAvailableSeat.getText());
            if (seatCount > 0) {
                lblAvailableSeat.setTextFill(Paint.valueOf("black"));
                return true;
            } else {
                lblAvailableSeat.setTextFill(Paint.valueOf("red"));
                new Alert(Alert.AlertType.ERROR, "No Seats Available !").show();
                return false;
            }
        } catch (NumberFormatException e) {
            lblAvailableSeat.setTextFill(Paint.valueOf("red"));
            return false;
        }
    }

    @FXML
    void btnNewRouteOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewRouteForm.fxml"));
        AnchorPane anchorPane = loader.load();

        AnchorPane overlayPane = new AnchorPane();
        overlayPane.setStyle("-fx-background-color: rgba(255,255,255, 0.5);");
        overlayPane.setPrefSize(paneRegistration.getWidth(), paneRegistration.getHeight());

        anchorPane.setLayoutX((overlayPane.getPrefWidth() - anchorPane.getPrefWidth()) / 2);
        anchorPane.setLayoutY((overlayPane.getPrefHeight() - anchorPane.getPrefHeight()) / 2);

        overlayPane.getChildren().add(anchorPane);
        paneRegistration.getChildren().add(overlayPane);

        NewRouteFormController controller = loader.getController();
        controller.setOverlayPane(overlayPane, paneRegistration);
    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewStudentForm.fxml"));
        AnchorPane anchorPane = loader.load();

        AnchorPane overlayPane = new AnchorPane();
        overlayPane.setStyle("-fx-background-color: rgba(255,255,255, 0.5);");
        overlayPane.setPrefSize(paneRegistration.getWidth(), paneRegistration.getHeight());

        anchorPane.setLayoutX((overlayPane.getPrefWidth() - anchorPane.getPrefWidth()) / 2);
        anchorPane.setLayoutY((overlayPane.getPrefHeight() - anchorPane.getPrefHeight()) / 2);

        overlayPane.getChildren().add(anchorPane);
        paneRegistration.getChildren().add(overlayPane);

        NewStudentFormController controller = loader.getController();
        controller.setOverlayPane(overlayPane, paneRegistration);
    }

    @FXML
    void btnNewVehicleOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewVehicleForm.fxml"));
        AnchorPane anchorPane = loader.load();

        AnchorPane overlayPane = new AnchorPane();
        overlayPane.setStyle("-fx-background-color: rgba(255,255,255, 0.5);");
        overlayPane.setPrefSize(paneRegistration.getWidth(), paneRegistration.getHeight());

        overlayPane.getChildren().add(anchorPane);
        paneRegistration.getChildren().add(overlayPane);

        NewVehicleFormController controller = loader.getController();
        controller.setOverlayPane(overlayPane, paneRegistration);
    }

    @FXML
    void DistanceOnKeyReleased(KeyEvent event) {
        if (!validateDistance()) {
            return;
        }
        String selectedRouteName = cmbRoute.getSelectionModel().getSelectedItem();
        if (selectedRouteName != null && !txtDistance.getText().isEmpty()) {
            try {
                String selectedRouteId = studentRegistrationBO.getRouteIdByRouteName(selectedRouteName);
                double routeFee = studentRegistrationBO.getRouteFeeByRouteId(selectedRouteId);
                double distance = Double.parseDouble(txtDistance.getText());
                dayPrice = routeFee * distance;
                txtDayPrice.setText(String.format("%.2f", dayPrice));
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to retrieve route fee: " + e.getMessage()).show();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Invalid distance value: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws SQLException {
        if (!validateSeatCount()) {
            return;
        }
        String studentId = studentRegistrationBO.getStudentIdByName(cmbStudentName.getSelectionModel().getSelectedItem());
        String studentRegId = lblRegistrationId.getText();
        String routeId = studentRegistrationBO.getRouteIdByRouteName(cmbRoute.getSelectionModel().getSelectedItem());
        String vehicleId = cmbVehicle.getSelectionModel().getSelectedItem();
        dayPrice = Double.parseDouble(txtDayPrice.getText());
        Date registrationDate = Date.valueOf(lblDate.getText());
        double distance = Double.parseDouble(txtDistance.getText());

        if(studentRegistrationBO.exists(studentId)){
            new Alert(Alert.AlertType.ERROR, "Student is already registered !").show();
            return;

        }

        studentRegistrationBO.addRegistration(new StudentRegistrationDto(studentRegId,studentId,distance,dayPrice,routeId,vehicleId,registrationDate),vehicleId);
        refreshPage();
    }


    @FXML
    void onClickTable(MouseEvent event) {
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    private void refreshPage() throws SQLException {
        refreshTable();
        cmbStudentName.getSelectionModel().clearSelection();
        cmbRoute.getSelectionModel().clearSelection();
        cmbVehicle.getSelectionModel().clearSelection();
        cmbDestination.getSelectionModel().clearSelection();
        txtDayPrice.setText("00.00");
        txtDistance.clear();
        lblRegistrationId.setText(studentRegistrationBO.getNextRegistrationId());
        lableStudentId.setText("Student Id");
        lblPickupLocation.setText("Pickup Location");
        lblAvailableSeat.setText("00");
        lblAvailableSeat.setTextFill(Paint.valueOf("black"));
        btnRegister.setDisable(false);

    }

    private void refreshTable() {
        tblStudentRegistration.getItems().clear();
        try {
            loadStudentRegistrationDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void lblManageStudentOnClick(MouseEvent mouseEvent) throws IOException {
        paneRegistration.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/StudentForm.fxml"));
        paneRegistration.getChildren().add(anchorPane);
    }


    private void loadStudentRegistrationDetails() throws SQLException {
        ObservableList<StudentRegistrationDetailsTM> studentRegistrationDetails = studentRegistrationBO.getAllStudentRegistrationDetails();
        tblStudentRegistration.setItems(studentRegistrationDetails);
    }

    private void loadLastStudentName() throws SQLException {
        List<String> studentNames = studentRegistrationBO.getAllStudentNames();
        if (!studentNames.isEmpty()) {
            cmbStudentName.getItems().addAll(studentNames);
        }
    }

    private void loadVehicleIds() throws SQLException {
        List<String> vehicleIds = studentRegistrationBO.getAllVehicleIds();
        cmbVehicle.getItems().addAll(vehicleIds);
    }

    private void loadRoutes() throws SQLException {
        List<String> routes = studentRegistrationBO.getAllRouteNames();
        cmbRoute.getItems().addAll(routes);
    }

    private void loadDestinations() throws SQLException {
        List<String> destinations = studentRegistrationBO.getAllDestinations();
        cmbDestination.getItems().addAll(destinations);
    }

}