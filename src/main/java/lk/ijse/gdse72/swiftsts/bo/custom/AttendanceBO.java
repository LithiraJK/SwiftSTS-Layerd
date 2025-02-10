package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AttendanceBO extends SuperBO {
    ArrayList<AttendanceDto> getAllAttendance() throws SQLException, ClassNotFoundException;
    boolean saveAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException;
    boolean updateAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteAttendance(String attendenceId) throws SQLException, ClassNotFoundException;
    String getNewId() throws SQLException, ClassNotFoundException;
    String getStudentNameById(String studentId) throws SQLException;
    String getStudentIdByName(String studentName) throws SQLException;
    List<String> getAllVehicleIds() throws SQLException;
    String getRegistrationNoById(String vehicleId) throws SQLException;
    ArrayList<String> getStudentIdsByVehicleId(String vehicleId) throws SQLException;


}
