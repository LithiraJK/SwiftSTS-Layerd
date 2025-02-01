package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpenseDAO extends CrudDAO<ExpenseDto> {
    public ArrayList<ExpenseDto> getAllData() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean save(ExpenseDto expenseDto) throws SQLException;
    public boolean update(ExpenseDto expenseDto) throws SQLException;
    public boolean delete(String expenseId) throws SQLException;
    public double getMonthlyExpense(String month) throws SQLException;

}
