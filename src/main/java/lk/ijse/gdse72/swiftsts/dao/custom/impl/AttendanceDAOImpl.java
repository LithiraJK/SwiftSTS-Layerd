package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;
import lk.ijse.gdse72.swiftsts.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public ArrayList<Attendance> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Attendance");
        ArrayList<Attendance> attendenceList = new ArrayList<>();

        while (rst.next()) {
            attendenceList.add(new Attendance(
                    rst.getString("AttendanceId"),
                    rst.getString("StudentId"),
                    rst.getString("VehicleId"),
                    rst.getInt("Year"),
                    rst.getString("Month"),
                    rst.getInt("DayCount")
            ));
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
    public boolean save(Attendance attendance) throws SQLException {
        return SQLUtil.execute("INSERT INTO Attendance (AttendanceId, StudentId, VehicleId, Year, Month, DayCount) VALUES (?, ?, ?, ?, ?, ?)",
                attendance.getAttendanceId(),
                attendance.getStudentId(),
                attendance.getVehicleId(),
                attendance.getYear(),
                attendance.getMonth(),
                attendance.getDayCount()
        );
    }
    @Override
    public boolean update(Attendance attendance) throws SQLException {
        return SQLUtil.execute("UPDATE Attendance SET StudentId=?, VehicleId=?, Year=?, Month=?, DayCount=? WHERE AttendanceId=?",
                attendance.getStudentId(),
                attendance.getVehicleId(),
                attendance.getYear(),
                attendance.getMonth(),
                attendance.getDayCount(),
                attendance.getAttendanceId()
        );
    }

    @Override
    public boolean delete(String attendenceId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Attendance WHERE AttendanceId=?", attendenceId);
    }
    @Override
    public String getNewId() throws SQLException {
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
