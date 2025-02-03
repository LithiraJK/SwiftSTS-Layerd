package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.NewStudentBO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class NewStudentBOImpl implements NewStudentBO {

    StudentDAO studentDAO = new StudentDAOImpl();
    UserDAO userDAO =  new UserDAOImpl();

    @Override
    public boolean saveNewStudent(StudentDto studentDto) throws SQLException {
        return studentDAO.save(studentDto);
    }

    @Override
    public String getNewId() throws SQLException {
        return studentDAO.getNewId();
    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        return userDAO.getAllUserIds();
    }
}
