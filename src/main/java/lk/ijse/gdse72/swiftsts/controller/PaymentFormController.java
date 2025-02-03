package lk.ijse.gdse72.swiftsts.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse72.swiftsts.bo.BOFactory;
import lk.ijse.gdse72.swiftsts.bo.custom.PaymentBO;
import lk.ijse.gdse72.swiftsts.bo.custom.impl.PaymentBOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.PaymentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.QueryDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.QueryDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.db.DBConnection;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.dto.tm.PaymentTM;
import lk.ijse.gdse72.swiftsts.util.SendMailUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class PaymentFormController implements Initializable {

//    StudentModel studentDAO = new StudentModel();
//    PaymentModel paymentDAO = new PaymentModel();
//    AttendanceModel attendanceDAO = new AttendanceModel();

//    StudentDAO studentDAO = new StudentDAOImpl();
//    PaymentDAO paymentDAO = new PaymentDAOImpl();
//    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
//    QueryDAO queryDAO = new QueryDAOImpl();

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);


    @FXML
    public JFXButton btnPaymentReceipt;
    @FXML
    public JFXToggleButton trbtnSendMail;

    @FXML
    private JFXButton btnCalculatepayment;

    @FXML
    private JFXButton btnMakePayment;

    @FXML
    private JFXComboBox<String> cmbAttendanceId;

    @FXML
    private JFXComboBox<String> cmbStudentNames;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentId;

    @FXML
    private TableColumn<PaymentTM, String> colStudentId;

    @FXML
    private TableColumn<PaymentTM, Double> colMonthlyFee;

    @FXML
    private TableColumn<PaymentTM, Double> colCreditBalance;

    @FXML
    private TableColumn<PaymentTM, Double> colAmount;

    @FXML
    private TableColumn<PaymentTM, Double> colBalance;

    @FXML
    private TableColumn<PaymentTM, String> colStatus;

    @FXML
    private TableColumn<PaymentTM, String> colDate;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblCreditBalance;

    @FXML
    private Label lblMonthlyFee;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblStudentId;

    @FXML
    private AnchorPane panePayment;

    @FXML
    private TableView<PaymentTM> tblPayments;

    @FXML
    private JFXTextField txtPayAmount;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
            colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            colMonthlyFee.setCellValueFactory(new PropertyValueFactory<>("monthlyFee"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
            colCreditBalance.setCellValueFactory(new PropertyValueFactory<>("creditBalance"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            setNextPaymentId();
            loadStudentNames();
            loadAttendanceIds((String) cmbStudentNames.getValue());
            loadPaymentData();
            refreshPage();
            lblPaymentDate.setText(LocalDate.now().toString());
            btnPaymentReceipt.setDisable(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentNames() throws SQLException {
        ArrayList<String> studentNames = paymentBO.getAllStudentNames();
        ObservableList<String> observableList = FXCollections.observableArrayList(studentNames);
        cmbStudentNames.setItems(observableList);
        cmbStudentNames.setOnAction(event -> {
            try {

                String selectedStudent = cmbStudentNames.getValue();
                loadAttendanceIds(selectedStudent);
                String studentId = paymentBO.getStudentIdByName(selectedStudent);
                lblStudentId.setText(studentId);
                double creditBalance = paymentBO.getCreditBalanceById(selectedStudent);
                lblCreditBalance.setText(String.format("%.2f", creditBalance));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void setNextPaymentId() throws SQLException {
        String nextPaymentId = paymentBO.getNextPaymentId();
        lblPaymentId.setText(nextPaymentId);
    }

    private Map<String, String> attendanceMap = new HashMap<>();

    private void loadAttendanceIds(String studentName) throws SQLException {
        String studentId = paymentBO.getStudentIdByName(studentName);
        ArrayList<String> attendanceMonths = paymentBO.getAttendanceMonthsByStudentId(studentId);
        ObservableList<String> observableList = FXCollections.observableArrayList(attendanceMonths);
        cmbAttendanceId.setItems(observableList);

        // Populate the attendanceMap with year-month to AttendanceId mapping
        for (String attendanceMonth : attendanceMonths) {
            String[] parts = attendanceMonth.split("-");
            String year = parts[0];
            String month = parts[1];
            String attendanceId = paymentBO.getAttendanceIdByStudentIdYearMonth(studentId, year, month);
            attendanceMap.put(attendanceMonth, attendanceId);
        }
    }


    private void loadPaymentData() throws SQLException {
        List<PaymentDto> paymentData = paymentBO.getPaymentData();
        ObservableList<PaymentTM> paymentTMs = FXCollections.observableArrayList();

        for (PaymentDto dto : paymentData) {
            paymentTMs.add(new PaymentTM(
                    dto.getPaymentId(),
                    dto.getStudentId(),
                    dto.getMonthlyFee(),
                    dto.getAmount(),
                    dto.getBalance(),
                    dto.getCreditBalance(),
                    dto.getStatus(),
                    java.sql.Date.valueOf(dto.getDate())
            ));
        }

        tblPayments.setItems(paymentTMs);
    }

    @FXML
    void btnCalculatepaymentOnAction(ActionEvent event) {
        try {
            String selectedMonthYear = cmbAttendanceId.getValue();
            String attendanceId = attendanceMap.get(selectedMonthYear);
            if (attendanceId == null) {
                new Alert(Alert.AlertType.ERROR, "Please select an attendance ID.").show();
                return;
            }

            int dayCount = paymentBO.getDayCountByAttendanceId(attendanceId);
            double monthlyFee = paymentBO.calculateMonthlyFee(lblStudentId.getText(), dayCount);

            lblMonthlyFee.setText(String.format("%.2f", monthlyFee));

            if (trbtnSendMail.isSelected()) {
                NotifyStudentByEmail();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while calculating the monthly fee: " + e.getMessage()).show();
        }
    }

    private void NotifyStudentByEmail() {
        try {
            String studentId = (String) cmbStudentNames.getValue();
            String email = paymentBO.getEmailByStudentId(studentId);
            String subject = "Monthly Fee Notification";
            String body = "Dear Student,\n\nYour monthly fee is: RS." + lblMonthlyFee.getText() + " \n\nYour Credit Balance is: RS." + lblCreditBalance.getText() + " \n\nThank you.";

            SendMailUtil emailThread = new SendMailUtil(email, subject, body);
            emailThread.start();
            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email: " + e.getMessage()).show();
        }
    }


    @FXML
    void btnMakePaymentOnAction(ActionEvent event) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();

            String studentId = lblStudentId.getText();
            String selectedMonthYear = cmbAttendanceId.getValue();
            String attendanceId = attendanceMap.get(selectedMonthYear);
            double payAmount = Double.parseDouble(txtPayAmount.getText());
            double creditBalance = Double.parseDouble(lblCreditBalance.getText());

            if (studentId == null || attendanceId == null || payAmount <= 0) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields correctly.").show();
                return;
            }

            int dayCount = paymentBO.getDayCountByAttendanceId(attendanceId);
            double monthlyFee = paymentBO.calculateMonthlyFee(lblStudentId.getText(), dayCount);
            double totalDue = monthlyFee + creditBalance;
            double remainingBalance = totalDue - payAmount;

            double balance;
            if (remainingBalance > 0) {
                // Payment does not cover the total due, update credit balance
                balance = 0.00;
                lblBalance.setText(String.format("%.2f", balance));
                lblCreditBalance.setText(String.format("%.2f", remainingBalance));
                creditBalance = remainingBalance;
            } else {
                // Payment covers the total due, update balance
                balance = Math.abs(remainingBalance);
                lblBalance.setText(String.format("%.2f", balance));
                lblCreditBalance.setText("0.00");
                creditBalance = 0;
            }

            PaymentDto paymentDto = new PaymentDto(
                    lblPaymentId.getText(),
                    studentId,
                    Double.parseDouble(lblMonthlyFee.getText()),
                    payAmount,
                    balance,
                    creditBalance,
                    creditBalance <= 0 ? "Paid" : "Pending",
                    LocalDate.now().toString()
            );

//            CrudUtil.startTransaction();
            connection.setAutoCommit(false);

            boolean isPaymentInserted = paymentBO.savePayment(paymentDto);
            if (!isPaymentInserted) throw new SQLException("Failed to insert into Payment");

            boolean isCreditBalanceUpdated = paymentBO.updateCreditBalance(studentId, creditBalance);
            if (!isCreditBalanceUpdated) throw new SQLException("Failed to update credit balance");

//            CrudUtil.commitTransaction();
            connection.setAutoCommit(false);

            new Alert(Alert.AlertType.INFORMATION, "Payment made successfully!").show();
            loadPaymentData();
        } catch (SQLException | NumberFormatException e) {
            try {
//                CrudUtil.rollbackTransaction();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while making the payment: " + e.getMessage()).show();
        }
        refreshPage();
    }

    private void refreshPage() throws SQLException {
        cmbStudentNames.getSelectionModel().clearSelection();
        cmbAttendanceId.getSelectionModel().clearSelection();

        lblMonthlyFee.setText("0.00");
        lblCreditBalance.setText("0.00");
        lblBalance.setText("0.00");
        lblStudentId.setText("");

        txtPayAmount.clear();
        loadStudentNames();
        loadPaymentData();
        setNextPaymentId();
        btnPaymentReceipt.setDisable(true);
    }

    @FXML
    private void tblPaymentsOnClicked(MouseEvent event) {
        PaymentTM selectedPayment = tblPayments.getSelectionModel().getSelectedItem();
        if (selectedPayment != null) {
            lblPaymentId.setText(selectedPayment.getPaymentId());
            btnPaymentReceipt.setDisable(false);
        } else {
            btnPaymentReceipt.setDisable(true);
        }
    }

    @FXML
    public void btnPaymentReceipt(ActionEvent actionEvent) {
        String selectedPaymentId = lblPaymentId.getText();

        if (selectedPaymentId == null || selectedPaymentId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a payment from the table.").show();
            return;
        }

        Thread reportThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JasperReport jasperReport = JasperCompileManager.compileReport(
                            getClass().getResourceAsStream("/reports/PaymentReceipt.jrxml"));
                    Connection connection = DBConnection.getInstance().getConnection();

                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("PaymentId", selectedPaymentId);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(
                            jasperReport,
                            parameters,
                            connection
                    );
                    JasperViewer.viewReport(jasperPrint, false);
                } catch (JRException e) {
                    new Alert(Alert.AlertType.ERROR, "Failed to generate the report").show();
                    e.printStackTrace();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Failed to connect to the database").show();
                    e.printStackTrace();
                }
            }
        });
        reportThread.start();
    }

}