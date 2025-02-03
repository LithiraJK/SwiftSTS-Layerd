package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.StudentBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;
import lk.ijse.gdse72.swiftsts.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        return userDAO.getAllUserIds();
    }

    @Override
    public ArrayList<StudentDto> getAllStudents() throws SQLException {
        ArrayList<Student> students = studentDAO.getAllData(); // Get Entity DATA
        ArrayList<StudentDto> studentDtos = new ArrayList<>();

        for (Student student : students){
            studentDtos.add(new StudentDto(
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getParentName(),
                    student.getAddress(),
                    student.getEmail(),
                    student.getStudentGrade(),
                    student.getPhoneNo(),
                    student.getUserId(),
                    student.getCreditBalance()
            ));
        }
        return studentDtos;
    }

    @Override
    public String getNewId() throws SQLException {
        return studentDAO.getNewId();//
    }

    @Override
    public boolean saveStudent(StudentDto dto) throws SQLException {
        return studentDAO.save(new Student(
                dto.getStudentId(),
                dto.getStudentName(),
                dto.getParentName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getStudentGrade(),
                dto.getPhoneNo(),
                dto.getUserId(),
                dto.getCreditBalance()));//DTO converted to Entity
    }

    @Override
    public boolean updateStudent(StudentDto dto) throws SQLException {
        return studentDAO.update(new Student(
                dto.getStudentId(),
                dto.getStudentName(),
                dto.getParentName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getStudentGrade(),
                dto.getPhoneNo(),
                dto.getUserId(),
                dto.getCreditBalance()));//DTO converted to Entity
    }

    @Override
    public boolean deleteStudent(String studentId) throws SQLException {
        return studentDAO.delete(studentId);
    }
}
