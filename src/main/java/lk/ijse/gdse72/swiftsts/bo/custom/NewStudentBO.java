package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NewStudentBO extends SuperBO {
    public boolean saveNewStudent(StudentDto studentDto) throws SQLException;
    public String getNewId() throws SQLException;
    public ArrayList<String> getAllUserIds() throws SQLException;


}
