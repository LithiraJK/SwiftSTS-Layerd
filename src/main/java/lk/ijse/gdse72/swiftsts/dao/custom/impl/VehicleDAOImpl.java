package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.VehicleDAO;
import lk.ijse.gdse72.swiftsts.dto.VehicleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public ArrayList<VehicleDto> getAllVehicles() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Vehicle");
        ArrayList<VehicleDto> vehicleDtos = new ArrayList<>();

        while (rst.next()) {
            VehicleDto vehicleDto = new VehicleDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getInt(8)
            );
            vehicleDtos.add(vehicleDto);
        }

        return vehicleDtos;
    }

    @Override
    public String getNextVehicleId() throws SQLException {
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
    public boolean saveVehicle(VehicleDto vehicleDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Vehicle VALUES (?,?,?,?,?,?,?,?)",
                vehicleDto.getVehicleId(),
                vehicleDto.getRegistrationNo(),
                vehicleDto.getVehicleType(),
                vehicleDto.getEngineCapacity(),
                vehicleDto.getFuelType(),
                vehicleDto.getModel(),
                vehicleDto.getSeatCount(),
                vehicleDto.getAvailableSeatCount()
        );
    }


    @Override
    public boolean updateVehicle(VehicleDto vehicleDto) throws SQLException {
        return SQLUtil.execute("UPDATE Vehicle SET registrationNo=?, vehicleType=?, engineCapacity=?, fuelType=?, model=?, seatCount=?, availableSeatCount=? WHERE vehicleId=?",
                vehicleDto.getRegistrationNo(),
                vehicleDto.getVehicleType(),
                vehicleDto.getEngineCapacity(),
                vehicleDto.getFuelType(),
                vehicleDto.getModel(),
                vehicleDto.getSeatCount(),
                vehicleDto.getAvailableSeatCount(),
                vehicleDto.getVehicleId()
        );
    }

    @Override
    public boolean deleteVehicle(String vehicleId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Vehicle WHERE vehicleId=?", vehicleId);
    }

    @Override
    public boolean updateVehicleSeatCount(String vehicleId, int decrementBy) throws SQLException {
        return SQLUtil.execute("UPDATE Vehicle SET AvailableSeatCount = AvailableSeatCount - ? WHERE VehicleId = ?", decrementBy, vehicleId);
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
