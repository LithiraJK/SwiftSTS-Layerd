package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.SuperDAO;
import lk.ijse.gdse72.swiftsts.dto.UserDto;
import lk.ijse.gdse72.swiftsts.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends SuperDAO {
    boolean saveUser(User user) throws SQLException;
    ArrayList<String> getAllUserIds() throws SQLException;
    String generateNextUserId() throws SQLException;
    boolean isUsernameValid(String username) throws SQLException;
    boolean validateCredentials(String username, String password) throws SQLException;
    String getUserPassword(String username , String email) throws SQLException;

}
