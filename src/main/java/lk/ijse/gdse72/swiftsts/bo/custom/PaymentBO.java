package lk.ijse.gdse72.swiftsts.bo.custom;

import javafx.scene.control.Label;
import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    ArrayList<String> getAllStudentNames() throws SQLException;
    String getStudentIdByName(String studentName) throws SQLException;
    double getCreditBalanceById(String studentId) throws SQLException;
    String getEmailByStudentId(String studentId) throws SQLException;
    double calculateMonthlyFee(String studentId, int dayCount) throws SQLException;
    String getNextPaymentId() throws SQLException;
    ArrayList<String> getAttendanceMonthsByStudentId(String studentId) throws SQLException ;
    String getAttendanceIdByStudentIdYearMonth(String studentId, String year, String month) throws SQLException ;
    int getDayCountByAttendanceId(String attendanceId) throws SQLException;
    List<PaymentDto> getPaymentData() throws SQLException;
    void addPayment(PaymentDto payment, String studentId, String attendanceId, double payAmount, double creditBalance, double remainingBalance, Label lblBalance, Label lblCreditBalance) throws SQLException;

}
