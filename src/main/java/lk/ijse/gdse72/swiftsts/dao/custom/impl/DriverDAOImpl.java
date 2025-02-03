package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.DriverDAO;
import lk.ijse.gdse72.swiftsts.dto.DriverDto;
import lk.ijse.gdse72.swiftsts.entity.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDAOImpl implements DriverDAO {
    @Override
    public ArrayList<Driver> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Driver");
        ArrayList<Driver> driverList = new ArrayList<>();

        while (rst.next()) {
            driverList.add(new Driver(
                    rst.getString("DriverId"),
                    rst.getString("Name"),
                    rst.getString("LicenseNo"),
                    rst.getString("NIC"),
                    rst.getString("ContactNo"),
                    rst.getString("Address"),
                    rst.getString("Email")
            ));
        }

        return driverList;
    }
    @Override
    public boolean save(Driver driver) throws SQLException {
        return SQLUtil.execute("INSERT INTO Driver (DriverId, Name, LicenseNo, NIC, ContactNo, Address, Email) VALUES (?, ?, ?, ?, ?, ?, ?)",
                driver.getDriverId(),
                driver.getName(),
                driver.getLicenseNo(),
                driver.getNic(),
                driver.getContactNo(),
                driver.getAddress(),
                driver.getEmail()
        );
    }

    @Override
    public boolean update(Driver driver) throws SQLException {
        return SQLUtil.execute("UPDATE Driver SET Name=?, LicenseNo=?, NIC=?, ContactNo=?, Address=?, Email=? WHERE DriverId=?",
                driver.getName(),
                driver.getLicenseNo(),
                driver.getNic(),
                driver.getContactNo(),
                driver.getAddress(),
                driver.getEmail(),
                driver.getDriverId()
        );
    }
    @Override
    public String getNewId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT DriverId FROM Driver ORDER BY DriverId DESC LIMIT 1");

        if (rst.next()) {
            String lastiD = rst.getString(1);
            String substring = lastiD.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("D%03d", newIdIndex);

        }
        return "D001";
    }
    @Override
    public boolean delete(String driverId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Driver WHERE DriverId=?", driverId);
    }

    @Override
    public ArrayList<String> getAllDriverIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT DriverId FROM Driver");

        ArrayList<String> driverIds = new ArrayList<>();

        while (rst.next()) {
            driverIds.add(rst.getString(1));
        }

        return driverIds;
    }

    @Override
    public int getDriverCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM Driver";
        ResultSet resultSet = SQLUtil.execute(query);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

}
