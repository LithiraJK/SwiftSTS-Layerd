package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.SignUpBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public String getUserPassword(String username, String email) throws SQLException {
        return userDAO.getUserPassword(username, email);

    }
}
