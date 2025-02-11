package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.QueryDAO;
import lk.ijse.gdse72.swiftsts.db.DBConnection;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;
import lk.ijse.gdse72.swiftsts.entity.Payment;
import lk.ijse.gdse72.swiftsts.entity.StudentRegistration;

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
                "JOIN Vehicle v ON sr.VehicleId = v.VehicleId " +
                "ORDER BY sr.StudentRegistrationId ASC";

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
    public List<Payment> getPaymentData() throws SQLException {
        List<Payment> paymentList = new ArrayList<>();
        String query = """
            SELECT p.PaymentId, s.StudentId, p.MonthlyFee, p.Amount,
                   p.Balance, s.CreditBalance, p.Status, p.Date
            FROM Payment p
            INNER JOIN Student s ON p.StudentId = s.StudentId
            """;

        ResultSet rs = SQLUtil.execute(query);
        while (rs.next()) {
            paymentList.add(new Payment(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    rs.getString(7),
                    rs.getString(8)
            ));
        }

        return paymentList;
    }
}