package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRegistrationsBO {
    public ArrayList<StudentRegistrationDto> getAllStudentRegistrations() throws SQLException;

}
