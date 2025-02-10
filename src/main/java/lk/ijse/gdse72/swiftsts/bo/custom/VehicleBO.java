package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleBO extends SuperBO {
    ArrayList<VehicleDto> getAllVehicles() throws SQLException, ClassNotFoundException;
    String getNewId() throws SQLException, ClassNotFoundException;
    boolean updateVehicle(VehicleDto vehicleDto) throws SQLException, ClassNotFoundException;
    boolean saveVehicle(VehicleDto vehicleDto) throws SQLException, ClassNotFoundException;
    boolean deleteVehicle(String vehicleId) throws SQLException, ClassNotFoundException;

}
