package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.StudentDAO;
import lk.ijse.gdse72.swiftsts.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Student");
        ArrayList<Student> students = new ArrayList<>();

        while (rst.next()) {
            students.add(new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8)
            ));
        }
        return students;
    }

    @Override
    public String getNewId() throws SQLException {
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
    public boolean save(Student student) throws SQLException {
        return SQLUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?,?,?)",
                student.getStudentId(),
                student.getStudentName(),
                student.getParentName(),
                student.getAddress(),
                student.getEmail(),
                student.getStudentGrade(),
                student.getPhoneNo(),
                student.getCreditBalance()
        );
    }

    @Override
    public boolean update(Student student) throws SQLException {
        return SQLUtil.execute("UPDATE Student SET StudentName=?, ParentName=?, PickupLocation=?, Email=?, StudentGrade=?, ContactNo=?, CreditBalance=?  WHERE StudentId=?",
                student.getStudentName(),
                student.getParentName(),
                student.getAddress(),
                student.getEmail(),
                student.getStudentGrade(),
                student.getPhoneNo(),
                student.getCreditBalance(),
                student.getStudentId()
        );
    }

    @Override
    public Student find(String studentId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Student WHERE StudentId = ?";
        ResultSet resultSet = SQLUtil.execute(query, studentId);
        if (resultSet.next()) {
            return new Student(
                    resultSet.getString("StudentId"),
                    resultSet.getString("StudentName"),
                    resultSet.getString("ParentName"),
                    resultSet.getString("PickupLocation"),
                    resultSet.getString("Email"),
                    resultSet.getString("StudentGrade"),
                    resultSet.getString("ContactNo"),
                    resultSet.getDouble("CreditBalance")
            );
        }
        return null; // or throw an exception if student not found
    }

    @Override
    public boolean delete(String studentId) throws SQLException {
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
