package lk.ijse.gdse72.swiftsts.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentRegistrationBO extends SuperBO {
    String getNextRegistrationId() throws SQLException;
    int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException;
    List<String> getAllVehicleIds() throws SQLException;
    String getStudentIdByName(String studentName) throws SQLException;
    String getPickupLocationById(String studentId) throws SQLException;
    ArrayList<String> getAllStudentNames() throws SQLException;
    String getRouteIdByRouteName(String routeName) throws SQLException;
    double getRouteFeeByRouteId(String routeId) throws SQLException;
    List<String> getAllRouteNames() throws SQLException;
    List<String> getAllDestinations() throws SQLException;
    ObservableList<StudentRegistrationDetailsTM> getAllStudentRegistrationDetails();
    boolean saveNewStudent(StudentDto studentDto) throws SQLException, ClassNotFoundException;
    void addRegistration(StudentRegistrationDto registrationDto, String vehicleId);
    StudentDto getStudentById(String studentId) throws SQLException, ClassNotFoundException;
    // In StudentRegistrationBO.java
    boolean exists(String studentId) throws SQLException;






}
