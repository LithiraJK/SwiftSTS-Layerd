package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO {
    public ArrayList<String> getAllUserIds() throws SQLException;
    public ArrayList<StudentDto> getAllStudents() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean saveStudent(StudentDto studentDto) throws SQLException;
    public boolean updateStudent(StudentDto studentDto) throws SQLException;
    public boolean deleteStudent(String studentId) throws SQLException;
}
