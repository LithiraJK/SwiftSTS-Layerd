package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.PaymentDto;

import java.sql.SQLException;

public interface PaymentBO {
    public boolean savePayment(PaymentDto paymentDto) throws SQLException;
    public double getMonthlyIncome(String month) throws SQLException;
    public double calculateMonthlyFee(String studentId, int dayCount) throws SQLException;
    public String getNextPaymentId() throws SQLException;
}
