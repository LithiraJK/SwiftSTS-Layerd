package lk.ijse.gdse72.swiftsts.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.dao.SuperDAO;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;
import lk.ijse.gdse72.swiftsts.entity.StudentRegistration;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRegistrationDAO extends SuperDAO {
    public boolean insertStudentRegistration(StudentRegistration studentRegistration) throws SQLException;
    public String getNextRegistrationId() throws SQLException;
    public ArrayList<String> getStudentIdsByVehicleId(String vehicleId) throws SQLException;

}
