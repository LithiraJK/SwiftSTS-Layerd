package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;
import lk.ijse.gdse72.swiftsts.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    List<String> getAllVehicleIds() throws SQLException;
    int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException;
    String getRegistrationNoById(String vehicleId) throws SQLException;
    int getVehicleCount() throws SQLException;
}
