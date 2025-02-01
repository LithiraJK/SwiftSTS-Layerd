package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO extends CrudDAO<VehicleDto> {
    public ArrayList<VehicleDto> getAllData() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean save(VehicleDto vehicleDto) throws SQLException;
    public boolean update(VehicleDto vehicleDto) throws SQLException;
    public boolean delete(String vehicleId) throws SQLException;
    public boolean updateVehicleSeatCount(String vehicleId, int decrementBy) throws SQLException;
    public List<String> getAllVehicleIds() throws SQLException;
    public int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException;
    public String getRegistrationNoById(String vehicleId) throws SQLException;
    public int getVehicleCount() throws SQLException;
}
