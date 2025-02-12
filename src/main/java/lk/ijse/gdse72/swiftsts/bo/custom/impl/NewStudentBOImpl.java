package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.NewStudentBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;
import lk.ijse.gdse72.swiftsts.entity.Student;

import java.sql.SQLException;

public class NewStudentBOImpl implements NewStudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(
                dto.getStudentId(),
                dto.getStudentName(),
                dto.getParentName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getStudentGrade(),
                dto.getPhoneNo(),
                dto.getCreditBalance()));
    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        return studentDAO.getNewId();
    }

}
