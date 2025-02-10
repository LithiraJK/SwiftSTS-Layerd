package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.DriverBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.DriverDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.DriverDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.DriverDto;
import lk.ijse.gdse72.swiftsts.entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverBOImpl implements DriverBO {

    DriverDAO driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);

    @Override
    public ArrayList<DriverDto> getAllDriver() throws SQLException, ClassNotFoundException {
        ArrayList<Driver> drivers = driverDAO.getAllData();//Entity to DTO
        ArrayList<DriverDto> driverDtos = new ArrayList<>();

        for (Driver driver : drivers) {
            driverDtos.add(new DriverDto(
                    driver.getDriverId(),
                    driver.getName(),
                    driver.getLicenseNo(),
                    driver.getNic(),
                    driver.getContactNo(),
                    driver.getAddress(),
                    driver.getEmail()
            ));
        }
        return driverDtos;
    }

    @Override
    public boolean saveDriver(DriverDto dto) throws SQLException, ClassNotFoundException { //DTO to Entity
        return driverDAO.save(new Driver(dto.getDriverId(),dto.getName(),dto.getLicenseNo(),dto.getNic(),dto.getContactNo(),dto.getAddress(),dto.getEmail()));
    }

    @Override
    public boolean updateDriver(DriverDto dto) throws SQLException, ClassNotFoundException { //DTO to Entity
        return driverDAO.update(new Driver(dto.getDriverId(),dto.getName(),dto.getLicenseNo(),dto.getNic(),dto.getContactNo(),dto.getAddress(),dto.getEmail()));
    }

    @Override
    public boolean deleteDriver(String driverId) throws SQLException, ClassNotFoundException {
        return driverDAO.delete(driverId);
    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        return driverDAO.getNewId();
    }
}
