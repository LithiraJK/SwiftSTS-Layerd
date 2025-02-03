package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.DriverDto;
import lk.ijse.gdse72.swiftsts.entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverDAO extends CrudDAO<Driver> {
    public ArrayList<Driver> getAllData() throws SQLException;
    public boolean save(Driver driver) throws SQLException;
    public boolean update(Driver driver) throws SQLException;
    public boolean delete(String driverId) throws SQLException;
    public String getNewId() throws SQLException;
    public ArrayList<String> getAllDriverIds() throws SQLException;

    public int getDriverCount() throws SQLException;



}
