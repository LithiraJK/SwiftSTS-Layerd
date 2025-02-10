package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.DriverDto;
import lk.ijse.gdse72.swiftsts.entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverDAO extends CrudDAO<Driver> {
    ArrayList<String> getAllDriverIds() throws SQLException;
    int getDriverCount() throws SQLException;



}
