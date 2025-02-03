package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;

import java.sql.SQLException;

public interface OverviewBO extends SuperBO {
    public double getMonthlyExpense(String month) throws SQLException;
    public double getMonthlyIncome(String month) throws SQLException;
    public int getStudentCount() throws SQLException;
    public int getDriverCount() throws SQLException;
    public int getVehicleCount() throws SQLException;

}
