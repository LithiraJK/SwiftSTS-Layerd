package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.ExpensesBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.ExpenseDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.UserDAO;
import lk.ijse.gdse72.swiftsts.dto.ExpenseDto;
import lk.ijse.gdse72.swiftsts.entity.Expense;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpensesBOImpl implements ExpensesBO {

    ExpenseDAO expenseDAO = (ExpenseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXPENSE);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public ArrayList<ExpenseDto> getAllExpenses() throws SQLException, ClassNotFoundException {
        ArrayList<Expense> expenseArrayList = expenseDAO.getAllData();
        ArrayList<ExpenseDto> expenseDtos = new ArrayList<>();
        for (Expense expense : expenseArrayList) {
            expenseDtos.add(new ExpenseDto(
                    expense.getExpenseId(),
                    expense.getDate(),
                    expense.getAmount(),
                    expense.getDescription(),
                    expense.getUserId()
            ));

        }

        return expenseDtos;

    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        return expenseDAO.getNewId();
    }

    @Override
    public boolean saveExpense(ExpenseDto dto) throws SQLException, ClassNotFoundException {
        return expenseDAO.save(new Expense(dto.getExpenseId(),dto.getDate(),dto.getAmount(),dto.getDescription(),dto.getUserId()));
    }

    @Override
    public boolean updateExpense(ExpenseDto dto) throws SQLException, ClassNotFoundException {
        return expenseDAO.update(new Expense(dto.getExpenseId(),dto.getDate(),dto.getAmount(),dto.getDescription(),dto.getUserId()));
    }

    @Override
    public boolean deleteExpense(String expenseId) throws SQLException, ClassNotFoundException {
        return expenseDAO.delete(expenseId);
    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        return userDAO.getAllUserIds();
    }
}
