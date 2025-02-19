package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.UpdateAttendanceBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.AttendanceDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.DriverDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;
import lk.ijse.gdse72.swiftsts.entity.Attendance;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateAttendanceBOImpl implements UpdateAttendanceBO {

    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ATTENDANCE);
    DriverDAO driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean updateAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException {
        return attendanceDAO.update(new Attendance(dto.getAttendanceId(), dto.getStudentId(), dto.getVehicleId(),dto.getYear(),dto.getMonth(),dto.getDayCount()));
    }

    @Override
    public ArrayList<String> getAllDriverIds() throws SQLException {
        return driverDAO.getAllDriverIds();
    }

    @Override
    public ArrayList<String> getAllStudentNames() throws SQLException {
        return studentDAO.getAllStudentNames();
    }

    @Override
    public String getStudentIdByName(String studentName) throws SQLException {
        return studentDAO.getStudentIdByName(studentName);
    }

    @Override
    public String getDriverNameById(String value) throws SQLException {
        return driverDAO.getDriverIdByName(value);
    }
}
