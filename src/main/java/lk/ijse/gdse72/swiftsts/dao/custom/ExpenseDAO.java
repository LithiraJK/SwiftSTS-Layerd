package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpenseDAO {
    public ArrayList<ExpenseDto> getAllExpenses() throws SQLException;
    public String getNextExpenseId() throws SQLException;
    public double getMonthlyExpense(String month) throws SQLException;
    public boolean saveExpense(ExpenseDto expenseDto) throws SQLException;
    public boolean updateExpense(ExpenseDto expenseDto) throws SQLException;
    public boolean deleteExpense(String expenseId) throws SQLException;
}
