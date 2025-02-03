package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.StudentRegistrationsBO;
import lk.ijse.gdse72.swiftsts.dao.custom.QueryDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.QueryDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRegistrationsBOImpl implements StudentRegistrationsBO {
    QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public ArrayList<StudentRegistrationDto> getAllStudentRegistrations() throws SQLException {
        return queryDAO.getAllStudentRegistrations();
    }
}
