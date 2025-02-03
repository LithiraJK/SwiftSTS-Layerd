package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;
import lk.ijse.gdse72.swiftsts.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    public ArrayList<Vehicle> getAllData() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean save(Vehicle vehicle) throws SQLException;
    public boolean update(Vehicle vehicle) throws SQLException;
    public boolean delete(String vehicleId) throws SQLException;
    public boolean updateVehicleSeatCount(String vehicleId, int decrementBy) throws SQLException;
    public List<String> getAllVehicleIds() throws SQLException;
    public int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException;
    public String getRegistrationNoById(String vehicleId) throws SQLException;
    public int getVehicleCount() throws SQLException;
}
