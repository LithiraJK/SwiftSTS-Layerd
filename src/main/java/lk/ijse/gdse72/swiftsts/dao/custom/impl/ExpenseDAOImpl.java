package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.ExpenseDAO;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenseDAOImpl implements ExpenseDAO {
    @Override
    public ArrayList<ExpenseDto> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Expense");
        ArrayList<ExpenseDto> expenseDtos = new ArrayList<>();

        while (rst.next()) {
            ExpenseDto expenseDto = new ExpenseDto(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            expenseDtos.add(expenseDto);
        }

        return expenseDtos;
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
    public boolean save(ExpenseDto expenseDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Expense VALUES (?,?,?,?,?)",
                expenseDto.getExpenseId(),
                expenseDto.getDate(),
                expenseDto.getAmount(),
                expenseDto.getDescription(),
                expenseDto.getUserId()
        );
    }
    @Override
    public boolean update(ExpenseDto expenseDto) throws SQLException {
        return SQLUtil.execute("UPDATE Expense SET Date=?, Amount=?, Description=?, UserId=? WHERE ExpenseId=?",
                expenseDto.getDate(),
                expenseDto.getAmount(),
                expenseDto.getDescription(),
                expenseDto.getUserId(),
                expenseDto.getExpenseId()
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
