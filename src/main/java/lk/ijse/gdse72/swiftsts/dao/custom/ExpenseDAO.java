package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;
import lk.ijse.gdse72.swiftsts.entity.Expense;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpenseDAO extends CrudDAO<Expense> {
    public ArrayList<Expense> getAllData() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean save(Expense expense) throws SQLException;
    public boolean update(Expense expense) throws SQLException;
    public boolean delete(String expenseId) throws SQLException;
    public double getMonthlyExpense(String month) throws SQLException;

}
