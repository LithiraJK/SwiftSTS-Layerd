package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceDAO {
    public ArrayList<AttendanceDto> getAllAttendances() throws SQLException;
    public int getDayCountByAttendanceId(String attendanceId);
    public String getAttendanceIdByStudentIdYearMonth(String studentId, String year, String month) throws SQLException ;
    public ArrayList<String> getAttendanceMonthsByStudentId(String studentId) throws SQLException ;
    public boolean saveAttendance(AttendanceDto dto) throws SQLException;
    public boolean updateAttendance(AttendanceDto dto) throws SQLException;
    public boolean deleteAttendence(String attendenceId) throws SQLException;
    public String getNextAttendanceId() throws SQLException;
}
