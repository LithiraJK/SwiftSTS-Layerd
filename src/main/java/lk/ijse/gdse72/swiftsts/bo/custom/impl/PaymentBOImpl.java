package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.PaymentBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.PaymentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.QueryDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.entity.Payment;

import java.sql.SQLException;
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
    public boolean savePayment(PaymentDto dto) throws SQLException {
        return paymentDAO.savePayment(new Payment(dto.getPaymentId(),dto.getStudentId(),dto.getMonthlyFee(),dto.getAmount(),dto.getBalance(),dto.getCreditBalance(),dto.getStatus(),dto.getDate()));
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
}
