package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;

import java.sql.SQLException;

public interface SignInBO extends SuperBO {
    public boolean isUsernameValid(String username) throws SQLException;
    public boolean validateCredentials(String username, String password) throws SQLException;

}
