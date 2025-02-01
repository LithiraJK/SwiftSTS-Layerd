package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.AttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AttendanceBO {
    public String getRegistrationNoById(String vehicleId) throws SQLException;
    public List<String> getAllVehicleIds() throws SQLException;

}
