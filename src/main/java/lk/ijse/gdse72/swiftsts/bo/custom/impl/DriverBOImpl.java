package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.DriverBO;
import lk.ijse.gdse72.swiftsts.dao.custom.DriverDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.DriverDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.DriverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverBOImpl implements DriverBO {

    DriverDAO driverDAO = new DriverDAOImpl();

    @Override
    public ArrayList<DriverDto> getAllDriver() throws SQLException {
        return driverDAO.getAllData();
    }

    @Override
    public boolean saveDriver(DriverDto dto) throws SQLException {
        return driverDAO.save(dto);
    }

    @Override
    public boolean updateDriver(DriverDto dto) throws SQLException {
        return driverDAO.update(dto);
    }

    @Override
    public boolean deleteDriver(String driverId) throws SQLException {
        return driverDAO.delete(driverId);
    }

    @Override
    public String getNewId() throws SQLException {
        return driverDAO.getNewId();
    }
}
