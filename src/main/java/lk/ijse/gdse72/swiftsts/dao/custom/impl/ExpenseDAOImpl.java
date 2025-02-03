package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.ExpenseDAO;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;
import lk.ijse.gdse72.swiftsts.entity.Expense;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenseDAOImpl implements ExpenseDAO {
    @Override
    public ArrayList<Expense> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Expense");
        ArrayList<Expense> expenseArrayList = new ArrayList<>();

        while (rst.next()) {
            expenseArrayList.add(new Expense(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }

        return expenseArrayList;
    }
    @Override
    public String getNewId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT expenseId FROM Expense ORDER BY expenseId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String numericPart = lastId.replaceAll("\\D+", ""); // Extract numeric part
            int i = Integer.parseInt(numericPart);
            int newIdIndex = i + 1;
            return String.format("E%03d", newIdIndex);
        }
        return "E001";
    }

    @Override
    public boolean save(Expense expense) throws SQLException {
        return SQLUtil.execute("INSERT INTO Expense VALUES (?,?,?,?,?)",
                expense.getExpenseId(),
                expense.getDate(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getUserId()
        );
    }
    @Override
    public boolean update(Expense expense) throws SQLException {
        return SQLUtil.execute("UPDATE Expense SET Date=?, Amount=?, Description=?, UserId=? WHERE ExpenseId=?",
                expense.getDate(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getUserId(),
                expense.getExpenseId()
        );
    }
    @Override
    public boolean delete(String expenseId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Expense WHERE ExpenseId=?", expenseId);
    }
    @Override
    public double getMonthlyExpense(String month) throws SQLException {
        String query = "SELECT SUM(Amount) AS TotalExpense FROM Expense WHERE Date LIKE ?";
        ResultSet rs = SQLUtil.execute(query, month + "%");

        if (rs.next()) {
            return rs.getDouble("TotalExpense");
        } else {
            return 0.0;
        }
    }
}
