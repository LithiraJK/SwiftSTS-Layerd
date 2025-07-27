package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {

    String getUserPassword(String username , String email) throws SQLException;
}
