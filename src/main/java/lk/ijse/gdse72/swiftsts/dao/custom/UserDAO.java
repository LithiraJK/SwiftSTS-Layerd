package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.SuperDAO;
import lk.ijse.gdse72.swiftsts.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends SuperDAO {
    public boolean saveUser(final UserDto dto) throws SQLException;
    public ArrayList<String> getAllUserIds() throws SQLException;
    public String generateNextUserId() throws SQLException;
    public boolean isUsernameValid(String username);
    public boolean validateCredentials(String username, String password);

}
