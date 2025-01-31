package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dto.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO {
    public ArrayList<VehicleDto> getAllVehicles() throws SQLException;
    public String getNextVehicleId() throws SQLException;
    public boolean saveVehicle(VehicleDto vehicleDto) throws SQLException;
    public boolean updateVehicle(VehicleDto vehicleDto) throws SQLException;
    public boolean deleteVehicle(String vehicleId) throws SQLException;
    public boolean updateVehicleSeatCount(String vehicleId, int decrementBy) throws SQLException;
    public List<String> getAllVehicleIds() throws SQLException;
    public int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException;
    public String getRegistrationNoById(String vehicleId) throws SQLException;
    public int getVehicleCount() throws SQLException;
}
