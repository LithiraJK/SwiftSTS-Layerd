package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.VehicleBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.VehicleDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);

    @Override
    public ArrayList<VehicleDto> getAllVehicles() throws SQLException {
        return vehicleDAO.getAllData();
    }

    @Override
    public String getNewId() throws SQLException {
        return vehicleDAO.getNewId();
    }

    @Override
    public boolean updateVehicle(VehicleDto vehicleDto) throws SQLException {
        return vehicleDAO.update(vehicleDto);
    }

    @Override
    public boolean saveVehicle(VehicleDto vehicleDto) throws SQLException {
        return vehicleDAO.save(vehicleDto);
    }

    @Override
    public boolean deleteVehicle(String vehicleId) throws SQLException {
        return vehicleDAO.delete(vehicleId);
    }
}
