package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpensesBO extends SuperBO {
    public ArrayList<ExpenseDto> getAllExpenses() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean saveExpense(ExpenseDto expenseDto) throws SQLException;
    public boolean updateExpense(ExpenseDto expenseDto) throws SQLException;
    public boolean deleteExpense(String expenseId) throws SQLException;
    public ArrayList<String> getAllUserIds() throws SQLException;

}
