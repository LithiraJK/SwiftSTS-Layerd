package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.VehicleDAO;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;
import lk.ijse.gdse72.swiftsts.entity.Student;
import lk.ijse.gdse72.swiftsts.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public ArrayList<Vehicle> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Vehicle");
        ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();

        while (rst.next()) {
            vehicleArrayList.add( new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getInt(8))
            );
        }
        return vehicleArrayList;
    }

    @Override
    public String getNewId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT vehicleId FROM Vehicle ORDER BY vehicleId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("V%03d", newIdIndex);
        }
        return "V001";
    }

    @Override
    public boolean save(Vehicle vehicle) throws SQLException {
        return SQLUtil.execute("INSERT INTO Vehicle VALUES (?,?,?,?,?,?,?,?)",
                vehicle.getVehicleId(),
                vehicle.getRegistrationNo(),
                vehicle.getVehicleType(),
                vehicle.getEngineCapacity(),
                vehicle.getFuelType(),
                vehicle.getModel(),
                vehicle.getSeatCount(),
                vehicle.getAvailableSeatCount()
        );
    }


    @Override
    public boolean update(Vehicle vehicle) throws SQLException {
        return SQLUtil.execute("UPDATE Vehicle SET registrationNo=?, vehicleType=?, engineCapacity=?, fuelType=?, model=?, seatCount=?, availableSeatCount=? WHERE vehicleId=?",
                vehicle.getRegistrationNo(),
                vehicle.getVehicleType(),
                vehicle.getEngineCapacity(),
                vehicle.getFuelType(),
                vehicle.getModel(),
                vehicle.getSeatCount(),
                vehicle.getAvailableSeatCount(),
                vehicle.getVehicleId()
        );
    }

    @Override
    public boolean delete(String vehicleId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Vehicle WHERE vehicleId=?", vehicleId);
    }

    @Override
    public List<String> getAllVehicleIds() throws SQLException {
        List<String> vehicleIds = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT VehicleId FROM Vehicle");
        while (resultSet.next()) {
            vehicleIds.add(resultSet.getString("VehicleId"));
        }
        return vehicleIds;
    }

    @Override
    public int getAvailableSeatCountByVehicleId(String vehicleId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT AvailableSeatCount FROM Vehicle WHERE VehicleId = ?", vehicleId);
        if (resultSet.next()) {
            return resultSet.getInt("AvailableSeatCount");
        }
        return 0;
    }

    @Override
    public String getRegistrationNoById(String vehicleId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT RegistrationNo FROM Vehicle WHERE VehicleId=?", vehicleId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public int getVehicleCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM Vehicle";
        ResultSet resultSet = SQLUtil.execute(query);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
