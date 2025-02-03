package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleBO {
    public ArrayList<VehicleDto> getAllVehicles() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean updateVehicle(VehicleDto vehicleDto) throws SQLException;
    public boolean saveVehicle(VehicleDto vehicleDto) throws SQLException;
    public boolean deleteVehicle(String vehicleId) throws SQLException;

}
