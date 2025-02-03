package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.ExpensesBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.ExpenseDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.ExpenseDAOImpl;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.UserDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpensesBOImpl implements ExpensesBO {

    ExpenseDAO expenseDAO = (ExpenseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXPENSE);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public ArrayList<ExpenseDto> getAllExpenses() throws SQLException {
        return expenseDAO.getAllData();
    }

    @Override
    public String getNewId() throws SQLException {
        return expenseDAO.getNewId();
    }

    @Override
    public boolean saveExpense(ExpenseDto expenseDto) throws SQLException {
        return expenseDAO.save(expenseDto);
    }

    @Override
    public boolean updateExpense(ExpenseDto expenseDto) throws SQLException {
        return expenseDAO.update(expenseDto);
    }

    @Override
    public boolean deleteExpense(String expenseId) throws SQLException {
        return expenseDAO.delete(expenseId);
    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        return userDAO.getAllUserIds();
    }
}
