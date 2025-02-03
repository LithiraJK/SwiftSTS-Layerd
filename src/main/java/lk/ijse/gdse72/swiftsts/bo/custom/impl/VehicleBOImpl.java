package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.VehicleBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.VehicleDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;
import lk.ijse.gdse72.swiftsts.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);

    @Override
    public ArrayList<VehicleDto> getAllVehicles() throws SQLException {
        ArrayList<Vehicle> vehicles = vehicleDAO.getAllData();
        ArrayList<VehicleDto> vehicleDtos =  new ArrayList<>();

        for (Vehicle vehicle : vehicles){
            vehicleDtos.add(new VehicleDto(
                    vehicle.getVehicleId(),
                    vehicle.getRegistrationNo(),
                    vehicle.getVehicleType(),
                    vehicle.getEngineCapacity(),
                    vehicle.getFuelType(),
                    vehicle.getModel(),
                    vehicle.getSeatCount(),
                    vehicle.getAvailableSeatCount()
            ));
        }
        return vehicleDtos;
    }

    @Override
    public String getNewId() throws SQLException {
        return vehicleDAO.getNewId();
    }

    @Override
    public boolean updateVehicle(VehicleDto dto) throws SQLException {
        return vehicleDAO.update(new Vehicle(
                dto.getVehicleId(),
                dto.getRegistrationNo(),
                dto.getVehicleType(),
                dto.getEngineCapacity(),
                dto.getFuelType(),
                dto.getModel(),
                dto.getSeatCount(),
                dto.getAvailableSeatCount()));
    }

    @Override
    public boolean saveVehicle(VehicleDto dto) throws SQLException {
        return vehicleDAO.save(new Vehicle(
                dto.getVehicleId(),
                dto.getRegistrationNo(),
                dto.getVehicleType(),
                dto.getEngineCapacity(),
                dto.getFuelType(),
                dto.getModel(),
                dto.getSeatCount(),
                dto.getAvailableSeatCount()));
    }

    @Override
    public boolean deleteVehicle(String vehicleId) throws SQLException {
        return vehicleDAO.delete(vehicleId);
    }
}
