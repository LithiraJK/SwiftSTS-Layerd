package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceDAO extends CrudDAO<AttendanceDto> {
    public ArrayList<AttendanceDto> getAllData() throws SQLException;
    public boolean save(AttendanceDto dto) throws SQLException;
    public boolean update(AttendanceDto dto) throws SQLException;
    public boolean delete(String attendenceId) throws SQLException;
    public String getNewId() throws SQLException;
    public int getDayCountByAttendanceId(String attendanceId);
    public String getAttendanceIdByStudentIdYearMonth(String studentId, String year, String month) throws SQLException ;
    public ArrayList<String> getAttendanceMonthsByStudentId(String studentId) throws SQLException ;
}
