package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentRegistrationDAO;
import lk.ijse.gdse72.swiftsts.db.DBConnection;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.dto.tm.StudentRegistrationDetailsTM;
import lk.ijse.gdse72.swiftsts.entity.StudentRegistration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRegistrationDAOImpl implements StudentRegistrationDAO {

    @Override
    public String getNextRegistrationId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT StudentRegistrationId FROM StudentRegistration ORDER BY StudentRegistrationId DESC LIMIT 1");

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(2); // Remove "SR" prefix
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("SR%03d", newIdIndex);
        }
        return "SR001";
    }

    @Override
    public ArrayList<String> getStudentIdsByVehicleId(String vehicleId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT StudentId FROM StudentRegistration WHERE VehicleId = ?", vehicleId);
        ArrayList<String> studentIds = new ArrayList<>();
        while (rst.next()) {
            studentIds.add(rst.getString(1));
        }
        return studentIds;
    }
    @Override
    public boolean isStudentRegistered(String studentId) throws SQLException {
        String query = "SELECT COUNT(*) FROM StudentRegistration WHERE StudentId = ?";
        ResultSet resultSet = SQLUtil.execute(query, studentId);
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;
        }
        return false;
    }
}
