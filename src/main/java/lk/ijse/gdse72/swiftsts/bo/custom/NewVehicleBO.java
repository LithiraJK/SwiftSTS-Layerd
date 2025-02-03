package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;

import java.sql.SQLException;

public interface NewVehicleBO extends SuperBO {
    public boolean saveNewVehicle(VehicleDto vehicleDto) throws SQLException;
    public String getNewId() throws SQLException;


}
