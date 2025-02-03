package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.OverviewBO;
import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.*;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OverviewBOImpl implements OverviewBO {

    ExpenseDAO expenseDAO = new ExpenseDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    DriverDAO driverDAO = new DriverDAOImpl();
    VehicleDAO vehicleDAO =  new VehicleDAOImpl();

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
