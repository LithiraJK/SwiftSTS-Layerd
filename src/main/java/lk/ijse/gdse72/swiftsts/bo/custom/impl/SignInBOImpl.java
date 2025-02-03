package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.SignInBO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;

public class SignInBOImpl implements SignInBO {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean isUsernameValid(String username) {
        return userDAO.isUsernameValid(username);
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        return userDAO.validateCredentials(username,password);
    }
}
