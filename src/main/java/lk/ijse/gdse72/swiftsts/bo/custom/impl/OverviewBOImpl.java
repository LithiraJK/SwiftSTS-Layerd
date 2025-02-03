package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.OverviewBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.*;
import java.sql.SQLException;

public class OverviewBOImpl implements OverviewBO {

    ExpenseDAO expenseDAO = (ExpenseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXPENSE);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    DriverDAO driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);
    VehicleDAO vehicleDAO =  (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);

    @Override
    public double getMonthlyExpense(String month) throws SQLException {
        return expenseDAO.getMonthlyExpense(month);
    }

    @Override
    public double getMonthlyIncome(String month) throws SQLException {
        return paymentDAO.getMonthlyIncome(month);
    }

    @Override
    public int getStudentCount() throws SQLException {
        return studentDAO.getStudentCount();
    }

    @Override
    public int getDriverCount() throws SQLException {
        return driverDAO.getDriverCount();
    }

    @Override
    public int getVehicleCount() throws SQLException {
        return vehicleDAO.getVehicleCount();
    }
}
