package lk.ijse.gdse72.swiftsts.bo.custom;

import javafx.scene.control.Label;
import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public ArrayList<String> getAllStudentNames() throws SQLException;
    public String getStudentIdByName(String studentName) throws SQLException;
    public double getCreditBalanceById(String studentId) throws SQLException;
    public String getEmailByStudentId(String studentId) throws SQLException;
    public double calculateMonthlyFee(String studentId, int dayCount) throws SQLException;
    public String getNextPaymentId() throws SQLException;
    public ArrayList<String> getAttendanceMonthsByStudentId(String studentId) throws SQLException ;
    public String getAttendanceIdByStudentIdYearMonth(String studentId, String year, String month) throws SQLException ;
    public int getDayCountByAttendanceId(String attendanceId) throws SQLException;
    public List<PaymentDto> getPaymentData() throws SQLException;
    public void addPayment(PaymentDto payment, String studentId, String attendanceId, double payAmount, double creditBalance, double remainingBalance, Label lblBalance, Label lblCreditBalance) throws SQLException;

}
