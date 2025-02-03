package lk.ijse.gdse72.swiftsts.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentRegistrationBO extends SuperBO {
    public String getNextRegistrationId() throws SQLException;
    public int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException;
    public List<String> getAllVehicleIds() throws SQLException;
    public String getStudentIdByName(String studentName) throws SQLException;
    public String getPickupLocationById(String studentId) throws SQLException;
    public ArrayList<String> getAllStudentNames() throws SQLException;
    public String getRouteIdByRouteName(String routeName) throws SQLException;
    public double getRouteFeeByRouteId(String routeId) throws SQLException;
    public List<String> getAllRouteNames() throws SQLException;
    public List<String> getAllDestinations() throws SQLException;
    public ObservableList<StudentRegistrationDetailsTM> getAllStudentRegistrationDetails();
    public void addRegistration(StudentRegistrationDto registrationDto, String vehicleId);








}
