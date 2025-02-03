package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UpdateAttendanceBO extends SuperBO {
    public boolean updateAttendance(AttendanceDto dto) throws SQLException;
    public ArrayList<String> getAllDriverIds() throws SQLException;
    public ArrayList<String> getAllStudentNames() throws SQLException;

}
