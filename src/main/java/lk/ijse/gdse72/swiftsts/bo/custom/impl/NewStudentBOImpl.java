package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.NewStudentBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;
import lk.ijse.gdse72.swiftsts.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class NewStudentBOImpl implements NewStudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        return studentDAO.getNewId();
    }

}
