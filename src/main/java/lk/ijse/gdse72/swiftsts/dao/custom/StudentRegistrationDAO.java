package lk.ijse.gdse72.swiftsts.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRegistrationDAO {
    public boolean insertStudentRegistration(String studentRegId, String studentId, double distance, double dayPrice, String registrationDate, String routeId, String vehicleId) throws SQLException;
    public String getNextRegistrationId() throws SQLException;
    public ArrayList<String> getStudentIdsByVehicleId(String vehicleId) throws SQLException;

}
