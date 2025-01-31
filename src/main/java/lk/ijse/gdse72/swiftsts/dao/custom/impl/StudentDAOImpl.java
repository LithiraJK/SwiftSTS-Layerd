package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.dto.StudentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<StudentDto> getAllStudents() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Student");
        ArrayList<StudentDto> studentDtos = new ArrayList<>();

        while (rst.next()) {
            StudentDto studentDto = new StudentDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getDouble("CreditBalance")
            );
            studentDtos.add(studentDto);
        }

        return studentDtos;
    }

    @Override
    public double getCreditBalanceById(String studentId) throws SQLException {
        String query = "SELECT CreditBalance FROM Student WHERE StudentId = ?";
        ResultSet rs = SQLUtil.execute(query, studentId);
        if (rs.next()) {
            return rs.getDouble("CreditBalance");
        }
        return 0.0;
    }

    @Override
    public boolean updateCreditBalance(String studentId, double creditBalance) throws SQLException {
        String query = "UPDATE Student SET CreditBalance = ? WHERE StudentId = ?";
        return SQLUtil.execute(query, creditBalance, studentId);
    }

    @Override
    public String getEmailByStudentId(String studentId) throws SQLException {
        String query = "SELECT Email FROM Student WHERE StudentId = ?";
        ResultSet rs = SQLUtil.execute(query, studentId);
        if (rs.next()) {
            return rs.getString("Email");
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllStudentNames() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT StudentName FROM Student");

        ArrayList<String> studentNames = new ArrayList<>();

        while (rst.next()) {
            studentNames.add(rst.getString(1));
        }

        return studentNames;
    }

    @Override
    public String getNextStudentId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT studentId FROM Student ORDER BY studentId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }

    @Override
    public boolean saveStudent(StudentDto studentDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?,?,?,?)",
                studentDto.getStudentId(),
                studentDto.getStudentName(),
                studentDto.getParentName(),
                studentDto.getAddress(),
                studentDto.getEmail(),
                studentDto.getStudentGrade(),
                studentDto.getPhoneNo(),
                studentDto.getUserId(),
                studentDto.getCreditBalance()
        );
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) throws SQLException {
        return SQLUtil.execute("UPDATE Student SET StudentName=?, ParentName=?, PickupLocation=?, Email=?, StudentGrade=?, ContactNo=?, UserId=?, CreditBalance=?  WHERE StudentId=?",
                studentDto.getStudentName(),
                studentDto.getParentName(),
                studentDto.getAddress(),
                studentDto.getEmail(),
                studentDto.getStudentGrade(),
                studentDto.getPhoneNo(),
                studentDto.getUserId(),
                studentDto.getCreditBalance(),
                studentDto.getStudentId()
        );
    }

    @Override
    public boolean deleteStudent(String studentId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Student WHERE StudentId=?", studentId);
    }

    @Override
    public String getStudentNameById(String studentId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT StudentName FROM Student WHERE StudentId=?", studentId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<String> getAllStudentIds() throws SQLException {
        List<String> studentIds = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT StudentId FROM Student");
        while (resultSet.next()) {
            studentIds.add(resultSet.getString(1));
        }
        return studentIds;
    }

    @Override
    public String getPickupLocationById(String studentId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT PickupLocation FROM Student WHERE StudentId = ?", studentId);
        if (resultSet.next()) {
            return resultSet.getString("PickupLocation");
        }
        return null;
    }

    @Override
    public String getStudentIdByName(String studentName) throws SQLException {
        String query = "SELECT StudentId FROM Student WHERE StudentName = ?";
        ResultSet rs = SQLUtil.execute(query, studentName);
        if (rs.next()) {
            return rs.getString("StudentId");
        }
        return null;
    }

    @Override
    public int getStudentCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM Student";
        ResultSet resultSet = SQLUtil.execute(query);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
