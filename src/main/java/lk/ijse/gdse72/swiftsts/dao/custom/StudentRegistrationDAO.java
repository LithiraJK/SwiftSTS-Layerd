package lk.ijse.gdse72.swiftsts.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRegistrationDAO {
    public boolean insertStudentRegistration(StudentRegistrationDto studentRegistrationDto) throws SQLException;
    public String getNextRegistrationId() throws SQLException;
    public ArrayList<String> getStudentIdsByVehicleId(String vehicleId) throws SQLException;

}
