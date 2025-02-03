package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.StudentRegistrationsBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.QueryDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.QueryDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.StudentRegistrationDto;
import lk.ijse.gdse72.swiftsts.entity.StudentRegistration;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRegistrationsBOImpl implements StudentRegistrationsBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public ArrayList<StudentRegistrationDto> getAllStudentRegistrations() throws SQLException {
        ArrayList<StudentRegistration> studentRegistrations = queryDAO.getAllStudentRegistrations();
        ArrayList<StudentRegistrationDto> registrationDtos = new ArrayList<>();

        for (StudentRegistration studentRegistration : studentRegistrations){
            registrationDtos.add(new StudentRegistrationDto(
                    studentRegistration.getRegistrationId(),
                    studentRegistration.getStudentId(),
                    studentRegistration.getDistance(),
                    studentRegistration.getDayPrice(),
                    studentRegistration.getRouteId(),
                    studentRegistration.getVehicleId(),
                    studentRegistration.getRegistrationDate()
            ));
        }
        return registrationDtos;
    }
}
