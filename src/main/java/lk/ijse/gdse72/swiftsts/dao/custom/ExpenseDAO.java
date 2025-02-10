package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;
import lk.ijse.gdse72.swiftsts.entity.Expense;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpenseDAO extends CrudDAO<Expense> {
    double getMonthlyExpense(String month) throws SQLException;

}
