package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.SignUpSecondBO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.UserDto;

import java.sql.SQLException;

public class SignUpSecondBOImpl implements SignUpSecondBO {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public String generateNextUserId() throws SQLException {
        return userDAO.generateNextUserId();
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return userDAO.saveUser(dto);
    }
}
