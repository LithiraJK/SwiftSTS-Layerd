package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.AttendanceBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
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
import lk.ijse.gdse72.swiftsts.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBOImpl implements AttendanceBO {

    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ATTENDANCE);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);
    StudentRegistrationDAO studentRegistrationDAO = (StudentRegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENTREGISTRATION);

    @Override
    public ArrayList<AttendanceDto> getAllAttendance() throws SQLException, ClassNotFoundException {
        ArrayList<Attendance> attendances = attendanceDAO.getAllData();//get all data from DAO layer
        ArrayList<AttendanceDto> attendanceDtos = new ArrayList<>();

        for (Attendance attendance : attendances) {
            attendanceDtos.add(new AttendanceDto(
                    attendance.getAttendanceId(),
                    attendance.getStudentId(),
                    attendance.getVehicleId(),
                    attendance.getYear(),
                    attendance.getMonth(),
                    attendance.getDayCount()
            ));
        }
        return attendanceDtos;
    }

    @Override
    public boolean saveAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException {
        return attendanceDAO.save(new Attendance(dto.getAttendanceId(), dto.getStudentId(), dto.getVehicleId(),dto.getYear(),dto.getMonth(),dto.getDayCount()));
    }

    @Override
    public boolean updateAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException {
        return attendanceDAO.update(new Attendance(dto.getAttendanceId(), dto.getStudentId(), dto.getVehicleId(),dto.getYear(),dto.getMonth(),dto.getDayCount()));
    }

    @Override
    public boolean deleteAttendance(String attendenceId) throws SQLException, ClassNotFoundException {
        return attendanceDAO.delete(attendenceId);
    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
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
