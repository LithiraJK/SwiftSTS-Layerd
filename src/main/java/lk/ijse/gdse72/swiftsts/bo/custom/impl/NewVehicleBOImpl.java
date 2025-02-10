package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.NewVehicleBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.VehicleDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;
import lk.ijse.gdse72.swiftsts.entity.Vehicle;

import java.sql.SQLException;

public class NewVehicleBOImpl implements NewVehicleBO {

    VehicleDAO vehicleDAO =(VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);

    @Override
    public boolean saveNewVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
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
    public String getNewId() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getNewId();
    }
}
