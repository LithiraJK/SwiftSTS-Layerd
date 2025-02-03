package lk.ijse.gdse72.swiftsts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Expense {
    private String expenseId;
    private Date date;
    private double amount;
    private String description;
    private String userId;
}