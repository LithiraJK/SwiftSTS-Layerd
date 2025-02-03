package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.UserDto;

import java.sql.SQLException;

public interface SignUpSecondBO {
    public String generateNextUserId() throws SQLException;
    public boolean saveUser(final UserDto dto) throws SQLException;


}
