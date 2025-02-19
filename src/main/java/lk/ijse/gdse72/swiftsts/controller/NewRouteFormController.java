package lk.ijse.gdse72.swiftsts.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.gdse72.swiftsts.bo.BOFactory;
import lk.ijse.gdse72.swiftsts.bo.custom.NewRouteBO;
import lk.ijse.gdse72.swiftsts.bo.custom.impl.NewRouteBOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.RouteDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.RouteDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewRouteFormController implements Initializable {

    NewRouteBO newRouteBO = (NewRouteBO) BOFactory.getInstance().getBO(BOFactory.BOType.NEW_ROUTE);

    @FXML
    private JFXButton btnDiscard;

    @FXML
    private JFXButton btnSave;

    @FXML
    private Label lblRouteId;

    @FXML
    private AnchorPane paneNewRoute;

    @FXML
    private JFXTextField txtDayFee;

    @FXML
    private JFXTextField txtDestination;

    @FXML
    private JFXTextField txtRouteName;

    @FXML
    private JFXTextField txtStartPoint;

    private AnchorPane overlayPane;
    private AnchorPane paneStudent;

    public void setOverlayPane(AnchorPane overlayPane, AnchorPane paneStudent) {
        this.overlayPane = overlayPane;
        this.paneStudent = paneStudent;
    }

    @FXML
    void btnDiscardOnAction(ActionEvent event) {
        paneStudent.getChildren().remove(overlayPane);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String routeId = lblRouteId.getText();
        String routeName = txtRouteName.getText();
        String startPoint = txtStartPoint.getText();
        String destination = txtDestination.getText();
        double routeFee = Double.parseDouble(txtDayFee.getText());

        String routeNamePattern = "^[A-Za-z ]+$";
        String startPointPattern = "^[A-Za-z ]+$";
        String destinationPattern = "^[A-Za-z ]+$";
        String dayFeePattern = "^[0-9]+(\\.[0-9]{1,2})?$";

        boolean isValidRouteName = routeName.matches(routeNamePattern);
        boolean isValidStartPoint = startPoint.matches(startPointPattern);
        boolean isValidDestination = destination.matches(destinationPattern);
        boolean isValidDayFee = String.valueOf(routeFee).matches(dayFeePattern);

        txtRouteName.setFocusColor(Paint.valueOf("black"));
        txtStartPoint.setFocusColor(Paint.valueOf("black"));
        txtDestination.setFocusColor(Paint.valueOf("black"));
        txtDayFee.setFocusColor(Paint.valueOf("black"));

        if (!isValidRouteName) {
            txtRouteName.setFocusColor(Paint.valueOf("red"));
        }
        if (!isValidStartPoint) {
            txtStartPoint.setFocusColor(Paint.valueOf("red"));
        }
        if (!isValidDestination) {
            txtDestination.setFocusColor(Paint.valueOf("red"));
        }
        if (!isValidDayFee) {
            txtDayFee.setFocusColor(Paint.valueOf("red"));
        }

        if (isValidRouteName && isValidStartPoint && isValidDestination && isValidDayFee) {
            RouteDto routeDto = new RouteDto(routeId, routeName, startPoint, destination, routeFee);

            try {
                boolean isSaved = newRouteBO.saveNewRoute(routeDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Route saved successfully!").show();
                    paneStudent.getChildren().remove(overlayPane);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save route!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred while saving the route: " + e.getMessage()).show();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String nextRouteId = newRouteBO.getNewId();
            lblRouteId.setText(nextRouteId);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while generating the route ID: " + e.getMessage()).show();
        }

        addValidationListeners();
    }

    private void addValidationListeners() {
        String routeNamePattern = "^[A-Za-z ]+$";
        String startPointPattern = "^[A-Za-z ]+$";
        String destinationPattern = "^[A-Za-z ]+$";
        String routeFeePattern = "^[0-9]+(\\.[0-9]{1,2})?$";


        txtRouteName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(routeNamePattern)) {
                txtRouteName.setFocusColor(Paint.valueOf("red"));
            } else {
                txtRouteName.setFocusColor(Paint.valueOf("black"));
            }
        });

        txtStartPoint.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(startPointPattern)) {
                txtStartPoint.setFocusColor(Paint.valueOf("red"));
            } else {
                txtStartPoint.setFocusColor(Paint.valueOf("black"));
            }
        });

        txtDestination.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(destinationPattern)) {
                txtDestination.setFocusColor(Paint.valueOf("red"));
            } else {
                txtDestination.setFocusColor(Paint.valueOf("black"));
            }
        });

        txtDayFee.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(routeFeePattern)) {
                txtDayFee.setFocusColor(Paint.valueOf("red"));
            } else {
                txtDayFee.setFocusColor(Paint.valueOf("black"));
            }
        });
    }
}