package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NewStudentBO extends SuperBO {
    boolean saveNewStudent(StudentDto studentDto) throws SQLException, ClassNotFoundException;
    String getNewId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllUserIds() throws SQLException;


}
