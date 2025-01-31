package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.QueryDAO;
import lk.ijse.gdse72.swiftsts.db.DBConnection;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ObservableList<StudentRegistrationDetailsTM> getAllStudentRegistrationDetails() {
        ObservableList<StudentRegistrationDetailsTM> list = FXCollections.observableArrayList();
        String query = "SELECT " +
                "sr.StudentRegistrationId AS 'Registration ID', " +
                "s.StudentId AS 'Student ID', " +
                "s.StudentName AS 'Student Name', " +
                "s.PickupLocation AS 'Pickup Location', " +
                "r.Destination AS 'Destination', " +
                "sr.Distance AS 'Distance', " +
                "sr.DayPrice AS 'Day Price', " +
                "sr.RouteId AS 'Route ID', " +
                "sr.VehicleId AS 'Vehicle ID', " +
                "sr.Date AS 'Registration Date' " +
                "FROM StudentRegistration sr " +
                "JOIN Student s ON sr.StudentId = s.StudentId " +
                "JOIN Route r ON sr.RouteId = r.RouteId " +
                "JOIN Vehicle v ON sr.VehicleId = v.VehicleId";

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new StudentRegistrationDetailsTM(
                        rs.getString("Registration ID"),
                        rs.getString("Student ID"),
                        rs.getString("Student Name"),
                        rs.getString("Pickup Location"),
                        rs.getString("Destination"),
                        rs.getDouble("Distance"),
                        rs.getDouble("Day Price"),
                        rs.getString("Route ID"),
                        rs.getString("Vehicle ID"),
                        rs.getDate("Registration Date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    @Override
    public ArrayList<StudentRegistrationDto> getAllStudentRegistrations() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT StudentRegistrationId, " +
                "StudentId, Distance,DayPrice,RouteName,VehicleId,Date " +
                "FROM StudentRegistration sr " +
                "join Route r " +
                "on sr.RouteId = r.RouteId");
        ArrayList<StudentRegistrationDto> studentRegistrationList = new ArrayList<>();

        while (rst.next()) {
            StudentRegistrationDto dto = new StudentRegistrationDto(
                    rst.getString("StudentRegistrationId"),
                    rst.getString("StudentId"),
                    rst.getDouble("DayPrice"),
                    rst.getDouble("Distance"),
                    rst.getString("RouteName"),
                    rst.getString("VehicleId"),
                    rst.getDate("Date")

            );
            studentRegistrationList.add(dto);
        }

        return studentRegistrationList;
    }

    @Override
    public List<PaymentDto> getPaymentData() {
        List<PaymentDto> paymentData = new ArrayList<>();
        String query = """
                SELECT p.PaymentId, s.StudentId, p.MonthlyFee, p.Amount,
                       p.Balance, s.CreditBalance, p.Status, p.Date
                FROM Payment p
                INNER JOIN Student s ON p.StudentId = s.StudentId
                """;

        try {
            ResultSet rs = SQLUtil.execute(query);
            while (rs.next()) {
                String paymentId = rs.getString(1);
                String studentId = rs.getString(2);
                double monthlyFee = rs.getDouble(3);
                double amount = rs.getDouble(4);
                double balance = rs.getDouble(5);
                double creditBalance = rs.getDouble(6);
                String status = rs.getString(7);
                String date = rs.getString(8);

                paymentData.add(new PaymentDto(paymentId, studentId, monthlyFee, amount, balance, creditBalance, status, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paymentData;
    }
}
