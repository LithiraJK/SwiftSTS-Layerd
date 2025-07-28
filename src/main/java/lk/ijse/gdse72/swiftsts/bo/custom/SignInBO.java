package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;

import java.sql.SQLException;

public interface SignInBO extends SuperBO {
    boolean isUsernameValid(String username) throws SQLException;
    boolean validateCredentials(String username, String password) throws SQLException;

    String getUserRole(String username) throws SQLException;
}
