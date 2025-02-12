package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    ArrayList<String> getAllUserIds() throws SQLException;
    ArrayList<StudentDto> getAllStudents() throws SQLException, ClassNotFoundException;
    String getNewId() throws SQLException, ClassNotFoundException;
    boolean updateStudent(StudentDto studentDto) throws SQLException, ClassNotFoundException;
    boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException;
}
