package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.SuperDAO;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends SuperDAO {
    boolean savePayment(Payment paymentDto) throws SQLException;
    double getMonthlyIncome(String month) throws SQLException;
    double calculateMonthlyFee(String studentId, int dayCount) throws SQLException;
    String getNextPaymentId() throws SQLException;

}
