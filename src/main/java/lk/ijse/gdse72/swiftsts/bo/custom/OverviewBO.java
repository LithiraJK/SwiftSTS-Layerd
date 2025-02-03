package lk.ijse.gdse72.swiftsts.bo.custom;

import java.sql.SQLException;

public interface OverviewBO {
    public double getMonthlyExpense(String month) throws SQLException;
    public double getMonthlyIncome(String month) throws SQLException;
    public int getStudentCount() throws SQLException;
    public int getDriverCount() throws SQLException;
    public int getVehicleCount() throws SQLException;

}
