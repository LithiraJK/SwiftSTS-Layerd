package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.PaymentBO;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.PaymentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.QueryDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.QueryDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    StudentDAO studentDAO = new StudentDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();

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
    public boolean updateCreditBalance(String studentId, double creditBalance) throws SQLException {
        return studentDAO.updateCreditBalance(studentId,creditBalance);
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
    public boolean savePayment(PaymentDto paymentDto) throws SQLException {
        return paymentDAO.savePayment(paymentDto);
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
    public int getDayCountByAttendanceId(String attendanceId) {
        return attendanceDAO.getDayCountByAttendanceId(attendanceId);
    }

    @Override
    public List<PaymentDto> getPaymentData() {
        return queryDAO.getPaymentData();
    }
}
