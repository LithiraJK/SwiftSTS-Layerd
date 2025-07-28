package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dto.UserDto;
import lk.ijse.gdse72.swiftsts.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean saveUser(User user) throws SQLException {
        return SQLUtil.execute("INSERT INTO User(UserId, UserName, Password, RoleType, Email) VALUES (?, ?, ?, ?, ?)",
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getEmail()
        );
    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT UserId FROM User");
        ArrayList<String> userIds = new ArrayList<>();

        while (rst.next()) {
            userIds.add(rst.getString(1));
        }

        return userIds;
    }

    @Override
    public String generateNextUserId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT UserId FROM User ORDER BY UserId DESC LIMIT 1");
        if (resultSet.next()) {
            String currentId = resultSet.getString("UserId");
            if (currentId != null) {
                int id = Integer.parseInt(currentId.substring(1)) + 1;
                return String.format("U%03d", id);
            }
        }
        return "U001";
    }

    @Override

    public boolean isUsernameValid(String username) throws SQLException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE username=?", username);
            return resultSet.next();
    }


    @Override

    public boolean validateCredentials(String username, String password) throws SQLException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE username=? AND password=?", username, password);
            return resultSet.next();
    }

    @Override
    public String getUserPassword(String username, String email) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Password FROM User WHERE UserName=? AND email=?", username, email);
        if (resultSet.next()) {
            return resultSet.getString("Password");
        }
        return null;
    }

    @Override
    public String getUserRole(String username) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT RoleType FROM User WHERE UserName=?", username);
        if (resultSet.next()) {
            return resultSet.getString("RoleType");
        }
        return null;
    }
}
