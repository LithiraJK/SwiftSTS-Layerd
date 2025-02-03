package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.DriverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverBO {
    public ArrayList<DriverDto> getAllDriver() throws SQLException;
    public boolean saveDriver(DriverDto dto) throws SQLException;
    public boolean updateDriver(DriverDto dto) throws SQLException;
    public boolean deleteDriver(String driverId) throws SQLException;
    public String getNewId() throws SQLException;
}
