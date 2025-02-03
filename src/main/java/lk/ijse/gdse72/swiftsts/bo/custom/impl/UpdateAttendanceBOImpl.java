package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.UpdateAttendanceBO;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.DriverDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.DriverDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateAttendanceBOImpl implements UpdateAttendanceBO {

    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    DriverDAO driverDAO =new DriverDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public boolean updateAttendance(AttendanceDto dto) throws SQLException {
        return attendanceDAO.update(dto);
    }

    @Override
    public ArrayList<String> getAllDriverIds() throws SQLException {
        return driverDAO.getAllDriverIds();
    }

    @Override
    public ArrayList<String> getAllStudentNames() throws SQLException {
        return studentDAO.getAllStudentNames();
    }
}
