package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.SignInBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;

public class SignInBOImpl implements SignInBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean isUsernameValid(String username) throws SQLException {
        return userDAO.isUsernameValid(username);
    }

    @Override
    public boolean validateCredentials(String username, String password) throws SQLException {
        return userDAO.validateCredentials(username,password);
    }
}
