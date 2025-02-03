package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;
import lk.ijse.gdse72.swiftsts.entity.Attendance;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceDAO extends CrudDAO<Attendance> {
    public ArrayList<Attendance> getAllData() throws SQLException;
    public boolean save(Attendance attendance) throws SQLException;
    public boolean update(Attendance attendance) throws SQLException;
    public boolean delete(String attendenceId) throws SQLException;
    public String getNewId() throws SQLException;
    public int getDayCountByAttendanceId(String attendanceId);
    public String getAttendanceIdByStudentIdYearMonth(String studentId, String year, String month) throws SQLException ;
    public ArrayList<String> getAttendanceMonthsByStudentId(String studentId) throws SQLException ;
}
