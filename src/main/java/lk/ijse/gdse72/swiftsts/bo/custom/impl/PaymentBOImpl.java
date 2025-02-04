package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.gdse72.swiftsts.bo.custom.PaymentBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.PaymentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.QueryDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.db.DBConnection;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ATTENDANCE);
    QueryDAO queryDAO =(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public ArrayList<String> getAllStudentNames() throws SQLException {
        return studentDAO.getAllStudentNames();
    }

    @Override
    public String getStudentIdByName(String studentName) throws SQLException {
        return studentDAO.getStudentIdByName(studentName);
    }

    @Override
    public double getCreditBalanceById(String studentId) throws SQLException {
        return studentDAO.getCreditBalanceById(studentId);
    }

    @Override
    public String getEmailByStudentId(String studentId) throws SQLException {
        return studentDAO.getEmailByStudentId(studentId);
    }

    @Override
    public double calculateMonthlyFee(String studentId, int dayCount) throws SQLException {
        return paymentDAO.calculateMonthlyFee(studentId,dayCount);
    }

    @Override
    public String getNextPaymentId() throws SQLException {
        return paymentDAO.getNextPaymentId();
    }

    @Override
    public ArrayList<String> getAttendanceMonthsByStudentId(String studentId) throws SQLException {
        return attendanceDAO.getAttendanceMonthsByStudentId(studentId);
    }

    @Override
    public String getAttendanceIdByStudentIdYearMonth(String studentId, String year, String month) throws SQLException {
        return attendanceDAO.getAttendanceIdByStudentIdYearMonth(studentId,year,month);
    }

    @Override
    public int getDayCountByAttendanceId(String attendanceId) throws SQLException {
        return attendanceDAO.getDayCountByAttendanceId(attendanceId);
    }

    @Override
    public List<PaymentDto> getPaymentData() throws SQLException {
        List<Payment> paymentData = queryDAO.getPaymentData();
        List<PaymentDto> paymentDtos = new ArrayList<>();
        for (Payment payment : paymentData){
            paymentDtos.add(new PaymentDto(
                    payment.getPaymentId(),
                    payment.getStudentId(),
                    payment.getMonthlyFee(),
                    payment.getAmount(),
                    payment.getBalance(),
                    payment.getCreditBalance(),
                    payment.getStatus(),
                    payment.getDate()
            ));
        }
        return paymentDtos;
    }

    public void addPayment(PaymentDto payment, String studentId, String attendanceId, double payAmount, double creditBalance, double remainingBalance, Label lblBalance, Label lblCreditBalance) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();

            if (studentId == null || attendanceId == null || payAmount <= 0) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields correctly.").show();
                return;
            }

            double balance;
            String status;
            if (remainingBalance >= 0) {
                // Payment does not cover the total due, update credit balance
                balance = 0.00;
                lblBalance.setText(String.format("%.2f", balance));
                lblCreditBalance.setText(String.format("%.2f", remainingBalance));
                creditBalance = remainingBalance;
                status = "Pending";
            } else {
                // Payment covers the total due, update balance
                balance = Math.abs(remainingBalance);
                lblBalance.setText(String.format("%.2f", balance));
                lblCreditBalance.setText("0.00");
                creditBalance = 0;
                status = "Paid";
            }

            connection.setAutoCommit(false);

            boolean isPaymentInserted = SQLUtil.execute("INSERT INTO Payment (PaymentId, StudentId, MonthlyFee, Amount, Balance, Status, Date) VALUES (?,?,?,?,?,?,?)",
                    payment.getPaymentId(),
                    payment.getStudentId(),
                    payment.getMonthlyFee(),
                    payment.getAmount(),
                    balance,
                    status,
                    payment.getDate()
            );
            if (!isPaymentInserted) throw new SQLException("Failed to insert into Payment");

            boolean isCreditBalanceUpdated = SQLUtil.execute("UPDATE Student SET CreditBalance = ? WHERE StudentId = ?", creditBalance, studentId);
            if (!isCreditBalanceUpdated) throw new SQLException("Failed to update credit balance");

            connection.commit();
            connection.setAutoCommit(true);

            new Alert(Alert.AlertType.INFORMATION, "Payment made successfully!").show();
            getPaymentData();
        } catch (SQLException | NumberFormatException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while making the payment: " + e.getMessage()).show();
        }
    }
}
