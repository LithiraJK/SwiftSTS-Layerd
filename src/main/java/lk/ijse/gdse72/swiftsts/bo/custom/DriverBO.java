package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.DriverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverBO extends SuperBO {
    ArrayList<DriverDto> getAllDriver() throws SQLException, ClassNotFoundException;
    boolean saveDriver(DriverDto dto) throws SQLException, ClassNotFoundException;
    boolean updateDriver(DriverDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteDriver(String driverId) throws SQLException, ClassNotFoundException;
    String getNewId() throws SQLException, ClassNotFoundException;
}
