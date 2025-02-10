package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;

import java.sql.SQLException;

public interface OverviewBO extends SuperBO {
    double getMonthlyExpense(String month) throws SQLException;
    double getMonthlyIncome(String month) throws SQLException;
    int getStudentCount() throws SQLException;
    int getDriverCount() throws SQLException;
    int getVehicleCount() throws SQLException;

}
