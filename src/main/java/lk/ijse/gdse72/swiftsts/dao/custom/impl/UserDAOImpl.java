package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl {
    public boolean saveUser(final UserDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO User(UserId, UserName, Password, RoleType, Email) VALUES (?, ?, ?, ?, ?)",
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getRole(),
                dto.getEmail()
        );
    }

    public ArrayList<String> getAllUserIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT UserId FROM User");
        ArrayList<String> userIds = new ArrayList<>();

        while (rst.next()) {
            userIds.add(rst.getString(1));
        }

        return userIds;
    }


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
}
