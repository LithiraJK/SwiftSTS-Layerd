package lk.ijse.gdse72.swiftsts.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.dao.SuperDAO;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;
import lk.ijse.gdse72.swiftsts.entity.StudentRegistration;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRegistrationDAO extends SuperDAO {
    String getNextRegistrationId() throws SQLException;
    ArrayList<String> getStudentIdsByVehicleId(String vehicleId) throws SQLException;
    boolean isStudentRegistered(String studentId) throws SQLException;
}
