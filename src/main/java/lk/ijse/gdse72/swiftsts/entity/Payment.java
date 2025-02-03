package lk.ijse.gdse72.swiftsts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    private String paymentId;
    private String studentId;
    private double monthlyFee;
    private double amount;
    private double balance;
    private double creditBalance;
    private String status;
    private String date;
}