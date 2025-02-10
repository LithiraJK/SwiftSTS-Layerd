package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;
import lk.ijse.gdse72.swiftsts.entity.Attendance;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceDAO extends CrudDAO<Attendance> {
    int getDayCountByAttendanceId(String attendanceId) throws SQLException;
    String getAttendanceIdByStudentIdYearMonth(String studentId, String year, String month) throws SQLException ;
    ArrayList<String> getAttendanceMonthsByStudentId(String studentId) throws SQLException ;
}
