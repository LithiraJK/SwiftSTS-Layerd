package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public ArrayList<AttendanceDto> getAllAttendances() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Attendance");
        ArrayList<AttendanceDto> attendenceList = new ArrayList<>();

        while (rst.next()) {
            AttendanceDto dto = new AttendanceDto(
                    rst.getString("AttendanceId"),
                    rst.getString("StudentId"),
                    rst.getString("VehicleId"),
                    rst.getInt("Year"),
                    rst.getString("Month"),
                    rst.getInt("DayCount")
            );
            attendenceList.add(dto);
        }

        return attendenceList;
    }
    @Override
    public int getDayCountByAttendanceId(String attendanceId) {
        try {
            ResultSet rst = SQLUtil.execute("SELECT DayCount FROM Attendance WHERE AttendanceId = ?", attendanceId);
            if (rst.next()) {
                return rst.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }
    @Override
    public String getAttendanceIdByStudentIdYearMonth(String studentId, String year, String month) throws SQLException {
        String query = "SELECT AttendanceId FROM Attendance WHERE StudentId = ? AND Year = ? AND Month = ?";
        ResultSet rst = SQLUtil.execute(query, studentId, year, month);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
    @Override
    public ArrayList<String> getAttendanceMonthsByStudentId(String studentId) throws SQLException {
        String query = "SELECT Year, Month FROM Attendance WHERE StudentId = ?";
        ResultSet rst = SQLUtil.execute(query, studentId);
        ArrayList<String> attendanceMonths = new ArrayList<>();
        while (rst.next()) {
            String year = rst.getString("Year");
            String month = rst.getString("Month");
            attendanceMonths.add(year + "-" + month);
        }
        return attendanceMonths;
    }
    @Override
    public boolean saveAttendance(AttendanceDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Attendance (AttendanceId, StudentId, VehicleId, Year, Month, DayCount) VALUES (?, ?, ?, ?, ?, ?)",
                dto.getAttendanceId(),
                dto.getStudentId(),
                dto.getVehicleId(),
                dto.getYear(),
                dto.getMonth(),
                dto.getDayCount()
        );
    }
    @Override
    public boolean updateAttendance(AttendanceDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE Attendance SET StudentId=?, VehicleId=?, Year=?, Month=?, DayCount=? WHERE AttendanceId=?",
                dto.getStudentId(),
                dto.getVehicleId(),
                dto.getYear(),
                dto.getMonth(),
                dto.getDayCount(),
                dto.getAttendanceId()
        );
    }

    @Override
    public boolean deleteAttendence(String attendenceId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Attendance WHERE AttendanceId=?", attendenceId);
    }
    @Override
    public String getNextAttendanceId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT AttendanceId FROM Attendance ORDER BY AttendanceId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("A%03d", newIdIndex);
        }
        return "A001";
    }

}
