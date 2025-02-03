package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.PaymentDAO;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean savePayment(Payment payment) throws SQLException {
        String query = "INSERT INTO Payment (PaymentId, StudentId, MonthlyFee, Amount, Balance, Status, Date) VALUES (?,?,?,?,?,?,?)";
        return SQLUtil.execute(query,
                payment.getPaymentId(),
                payment.getStudentId(),
                payment.getMonthlyFee(),
                payment.getAmount(),
                payment.getBalance(),
                payment.getStatus(),
                payment.getDate()
        );
    }

    @Override
    public double getMonthlyIncome(String month) throws SQLException {
        String query = "SELECT SUM(Amount) AS TotalIncome FROM Payment WHERE Date LIKE ?";
        ResultSet rs = SQLUtil.execute(query, month + "%");

        if (rs.next()) {
            return rs.getDouble("TotalIncome");
        } else {
            return 0.0;
        }
    }
    @Override
    public double calculateMonthlyFee(String studentId, int dayCount) throws SQLException {
        String query = "SELECT DayPrice FROM StudentRegistration WHERE StudentId = ?";
        ResultSet rs = SQLUtil.execute(query, studentId);

        if (rs.next()) {
            double dayPrice = rs.getDouble("DayPrice");
            if (dayCount > 0) {
                return dayCount * dayPrice;
            }
            return 0.00;
        } else {
            throw new SQLException("Student ID not found in StudentRegistrationTable");
        }
    }

    @Override
    public String getNextPaymentId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT PaymentId FROM Payment ORDER BY PaymentId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }
}
