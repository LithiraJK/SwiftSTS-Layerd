package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.SignUpSecondBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.UserDto;
import lk.ijse.gdse72.swiftsts.entity.User;

import java.sql.SQLException;

public class SignUpSecondBOImpl implements SignUpSecondBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public String generateNextUserId() throws SQLException {
        return userDAO.generateNextUserId();
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return userDAO.saveUser(new User(dto.getId(),dto.getUsername(),dto.getPassword(),dto.getRole(),dto.getEmail()));
    }
}
