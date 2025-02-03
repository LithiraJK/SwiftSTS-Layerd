package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.bo.custom.StudentRegistrationBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.*;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.*;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;

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
    public boolean insertStudentRegistration(StudentRegistrationDto studentRegistrationDto) throws SQLException {
        return studentRegistrationDAO.insertStudentRegistration(studentRegistrationDto);
    }

    @Override
    public String getNextRegistrationId() throws SQLException {
        return studentRegistrationDAO.getNextRegistrationId();
    }

    @Override
    public boolean updateVehicleSeatCount(String vehicleId, int decrementBy) throws SQLException {
        return vehicleDAO.updateVehicleSeatCount(vehicleId,decrementBy);
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
    public ObservableList<StudentRegistrationDetailsTM> getAllStudentRegistrationDetails() {
        return queryDAO.getAllStudentRegistrationDetails();
    }
}
