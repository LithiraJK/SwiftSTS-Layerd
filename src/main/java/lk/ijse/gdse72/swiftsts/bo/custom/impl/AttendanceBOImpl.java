package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.AttendanceBO;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentRegistrationDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.VehicleDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentRegistrationDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBOImpl implements AttendanceBO {

    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    VehicleDAO vehicleDAO = new VehicleDAOImpl();
    StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAOImpl();

    @Override
    public ArrayList<AttendanceDto> getAllAttendance() throws SQLException {
        return attendanceDAO.getAllData();
    }

    @Override
    public boolean saveAttendance(AttendanceDto dto) throws SQLException {
        return attendanceDAO.save(dto);
    }

    @Override
    public boolean updateAttendance(AttendanceDto dto) throws SQLException {
        return attendanceDAO.update(dto);
    }

    @Override
    public boolean deleteAttendance(String attendenceId) throws SQLException {
        return attendanceDAO.delete(attendenceId);
    }

    @Override
    public String getNewId() throws SQLException {
        return attendanceDAO.getNewId();
    }

    @Override
    public String getStudentNameById(String studentId) throws SQLException {
        return studentDAO.getStudentNameById(studentId);
    }

    @Override
    public String getStudentIdByName(String studentName) throws SQLException {
        return studentDAO.getStudentIdByName(studentName);
    }

    @Override
    public List<String> getAllVehicleIds() throws SQLException {
        return vehicleDAO.getAllVehicleIds();
    }

    @Override
    public String getRegistrationNoById(String vehicleId) throws SQLException {
        return vehicleDAO.getRegistrationNoById(vehicleId);
    }

    @Override
    public ArrayList<String> getStudentIdsByVehicleId(String vehicleId) throws SQLException {
        return studentRegistrationDAO.getStudentIdsByVehicleId(vehicleId);
    }
}
