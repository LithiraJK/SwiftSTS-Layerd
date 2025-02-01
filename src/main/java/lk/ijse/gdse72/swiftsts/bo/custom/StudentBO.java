package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO {
    public ArrayList<StudentDto> getAllStudents() throws SQLException;
    public double getCreditBalanceById(String studentId) throws SQLException;
    public boolean updateCreditBalance(String studentId, double creditBalance) throws SQLException;
    public String getEmailByStudentId(String studentId) throws SQLException;
    public ArrayList<String> getAllStudentNames() throws SQLException;
    public String getNextStudentId() throws SQLException;
    public boolean saveStudent(StudentDto studentDto) throws SQLException;
    public boolean updateStudent(StudentDto studentDto) throws SQLException;
    public boolean deleteStudent(String studentId) throws SQLException;
    public String getStudentNameById(String studentId) throws SQLException;
    public List<String> getAllStudentIds() throws SQLException;
    public String getPickupLocationById(String studentId) throws SQLException;
    public String getStudentIdByName(String studentName) throws SQLException;
    public int getStudentCount() throws SQLException;
}
