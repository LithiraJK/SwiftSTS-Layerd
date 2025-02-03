package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.NewVehicleBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.VehicleDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;

import java.sql.SQLException;

public class NewVehicleBOImpl implements NewVehicleBO {

    VehicleDAO vehicleDAO =(VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);

    @Override
    public boolean saveNewVehicle(VehicleDto vehicleDto) throws SQLException {
        return vehicleDAO.save(vehicleDto);
    }

    @Override
    public String getNewId() throws SQLException {
        return vehicleDAO.getNewId();
    }
}
