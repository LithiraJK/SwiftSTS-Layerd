package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UpdateAttendanceBO extends SuperBO {
    boolean updateAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllDriverIds() throws SQLException;
    ArrayList<String> getAllStudentNames() throws SQLException;
    String getStudentIdByName(String studentName) throws SQLException;

    String getDriverNameById(String value) throws SQLException;
}

