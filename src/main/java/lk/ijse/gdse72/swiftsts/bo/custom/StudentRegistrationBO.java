package lk.ijse.gdse72.swiftsts.bo.custom;

import java.sql.SQLException;
import java.util.List;

public interface StudentRegistrationBO {
    public int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException;
    public List<String> getAllVehicleIds() throws SQLException;
    public boolean updateVehicleSeatCount(String vehicleId, int decrementBy) throws SQLException;

}
