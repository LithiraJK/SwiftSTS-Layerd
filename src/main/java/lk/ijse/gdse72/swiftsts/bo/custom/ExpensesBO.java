package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpensesBO extends SuperBO {
    ArrayList<ExpenseDto> getAllExpenses() throws SQLException, ClassNotFoundException;
    String getNewId() throws SQLException, ClassNotFoundException;
    boolean saveExpense(ExpenseDto expenseDto) throws SQLException, ClassNotFoundException;
    boolean updateExpense(ExpenseDto expenseDto) throws SQLException, ClassNotFoundException;
    boolean deleteExpense(String expenseId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllUserIds() throws SQLException;

}
