package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AttendanceBO {
    public ArrayList<AttendanceDto> getAllAttendance() throws SQLException;
    public boolean saveAttendance(AttendanceDto dto) throws SQLException;
    public boolean updateAttendance(AttendanceDto dto) throws SQLException;
    public boolean deleteAttendance(String attendenceId) throws SQLException;
    public String getNewId() throws SQLException;
    public String getStudentNameById(String studentId) throws SQLException;
    public String getStudentIdByName(String studentName) throws SQLException;
    public List<String> getAllVehicleIds() throws SQLException;
    public String getRegistrationNoById(String vehicleId) throws SQLException;
    public ArrayList<String> getStudentIdsByVehicleId(String vehicleId) throws SQLException;


}
