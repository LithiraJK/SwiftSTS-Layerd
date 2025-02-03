package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.StudentBO;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = new StudentDAOImpl();
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        return userDAO.getAllUserIds();
    }

    @Override
    public ArrayList<StudentDto> getAllStudents() throws SQLException {
        return studentDAO.getAllData();
    }

    @Override
    public String getNewId() throws SQLException {
        return studentDAO.getNewId();
    }

    @Override
    public boolean saveStudent(StudentDto studentDto) throws SQLException {
        return studentDAO.save(studentDto);
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) throws SQLException {
        return studentDAO.update(studentDto);
    }

    @Override
    public boolean deleteStudent(String studentId) throws SQLException {
        return studentDAO.delete(studentId);
    }
}
