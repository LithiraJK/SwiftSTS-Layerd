package lk.ijse.gdse72.swiftsts.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.dao.SuperDAO;
import lk.ijse.gdse72.swiftsts.dto.PaymentDto;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;
import lk.ijse.gdse72.swiftsts.entity.Payment;
import lk.ijse.gdse72.swiftsts.entity.StudentRegistration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    ObservableList<StudentRegistrationDetailsTM> getAllStudentRegistrationDetails();
    List<Payment> getPaymentData() throws SQLException;
}
