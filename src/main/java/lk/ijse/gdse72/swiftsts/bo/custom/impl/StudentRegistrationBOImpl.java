package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.gdse72.swiftsts.bo.custom.StudentRegistrationBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.*;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.*;
import lk.ijse.gdse72.swiftsts.db.DBConnection;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;
import lk.ijse.gdse72.swiftsts.entity.Student;
import lk.ijse.gdse72.swiftsts.entity.StudentRegistration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationBOImpl implements StudentRegistrationBO {

    StudentRegistrationDAO studentRegistrationDAO = (StudentRegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENTREGISTRATION);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    RouteDAO routeDAO = (RouteDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROUTE);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public String getNextRegistrationId() throws SQLException {
        return studentRegistrationDAO.getNextRegistrationId();
    }

    @Override
    public int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException {
        return vehicleDAO.getAvailableSeatCountByVehicleId(vehicleId);
    }

    @Override
    public List<String> getAllVehicleIds() throws SQLException {
        return vehicleDAO.getAllVehicleIds();
    }

    @Override
    public String getStudentIdByName(String studentName) throws SQLException {
        return studentDAO.getStudentIdByName(studentName);
    }

    @Override
    public String getPickupLocationById(String studentId) throws SQLException {
        return studentDAO.getPickupLocationById(studentId);
    }

    @Override
    public ArrayList<String> getAllStudentNames() throws SQLException {
        return studentDAO.getAllStudentNames();
    }

    @Override
    public String getRouteIdByRouteName(String routeName) throws SQLException {
        return routeDAO.getRouteIdByRouteName(routeName);
    }

    @Override
    public double getRouteFeeByRouteId(String routeId) throws SQLException {
        return routeDAO.getRouteFeeByRouteId(routeId);
    }

    @Override
    public List<String> getAllRouteNames() throws SQLException {
        return routeDAO.getAllRouteNames();
    }

    @Override
    public List<String> getAllDestinations() throws SQLException {
        return routeDAO.getAllDestinations();
    }

    @Override
    public ObservableList<StudentRegistrationDetailsTM> getAllStudentRegistrationDetails()  {
        return queryDAO.getAllStudentRegistrationDetails();
    }


    @Override
    public boolean exists(String studentId) throws SQLException {
        return studentRegistrationDAO.isStudentRegistered(studentId);
    }

    @Override
    public void addRegistration(StudentRegistrationDto registrationDto, String vehicleId) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isStudentRegistrationSaved = SQLUtil.execute("INSERT INTO StudentRegistration (StudentRegistrationId, StudentId, Distance, DayPrice, Date, RouteId, VehicleId) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    registrationDto.getRegistrationId(),
                    registrationDto.getStudentId(),
                    registrationDto.getDistance(),
                    registrationDto.getDayPrice(),
                    registrationDto.getRegistrationDate(),
                    registrationDto.getRouteId(),
                    registrationDto.getVehicleId()
            );
            if (!isStudentRegistrationSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException("Failed to insert into StudentRegistration");
            }

            boolean isVehicleUpdated = SQLUtil.execute("UPDATE Vehicle SET AvailableSeatCount = AvailableSeatCount - ? WHERE VehicleId = ?", 1, vehicleId);
            if (!isVehicleUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException("Failed to update Vehicle seat count");
            }

            connection.commit();
            connection.setAutoCommit(true);

            new Alert(Alert.AlertType.INFORMATION, "Student registered successfully!").show();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            new Alert(Alert.AlertType.ERROR, "Failed to register student: " + e.getMessage()).show();
        }
    }
}
