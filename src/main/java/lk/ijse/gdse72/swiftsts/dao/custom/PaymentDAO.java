package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.SuperDAO;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends SuperDAO {
    public boolean savePayment(Payment paymentDto) throws SQLException;
    public double getMonthlyIncome(String month) throws SQLException;
    public double calculateMonthlyFee(String studentId, int dayCount) throws SQLException;
    public String getNextPaymentId() throws SQLException;

}
