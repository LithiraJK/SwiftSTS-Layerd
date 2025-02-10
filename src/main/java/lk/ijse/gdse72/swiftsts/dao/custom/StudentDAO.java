package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;
import lk.ijse.gdse72.swiftsts.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    double getCreditBalanceById(String studentId) throws SQLException;
    boolean updateCreditBalance(String studentId, double creditBalance) throws SQLException;
    String getEmailByStudentId(String studentId) throws SQLException;
    ArrayList<String> getAllStudentNames() throws SQLException;
    String getStudentNameById(String studentId) throws SQLException;
    String getPickupLocationById(String studentId) throws SQLException;
    String getStudentIdByName(String studentName) throws SQLException;
    int getStudentCount() throws SQLException;
}
