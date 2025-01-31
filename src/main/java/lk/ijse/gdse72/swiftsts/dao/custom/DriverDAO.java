package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dto.DriverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverDAO {
    public ArrayList<DriverDto> getAllDrivers() throws SQLException;
    public boolean saveDriver(DriverDto dto) throws SQLException;
    public boolean updateDriver(DriverDto dto) throws SQLException;
    public String getNextDriverId() throws SQLException;
    public ArrayList<String> getAllDriverIds() throws SQLException;
    public boolean deleteDriver(String driverId) throws SQLException;
    public int getDriverCount() throws SQLException;



}
