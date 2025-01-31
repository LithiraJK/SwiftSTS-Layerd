package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentDAO extends CrudDAO<StudentDto> {
    public ArrayList<StudentDto> getAllData() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean save(StudentDto studentDto) throws SQLException;
    public boolean update(StudentDto studentDto) throws SQLException;
    public boolean delete(String studentId) throws SQLException;
    public double getCreditBalanceById(String studentId) throws SQLException;
    public boolean updateCreditBalance(String studentId, double creditBalance) throws SQLException;
    public String getEmailByStudentId(String studentId) throws SQLException;
    public ArrayList<String> getAllStudentNames() throws SQLException;
    public String getStudentNameById(String studentId) throws SQLException;
    public String getPickupLocationById(String studentId) throws SQLException;
    public String getStudentIdByName(String studentName) throws SQLException;
    public int getStudentCount() throws SQLException;
}
