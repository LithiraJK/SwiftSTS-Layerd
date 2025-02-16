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
public class StudentRegistration {
    private String registrationId;
    private String studentId;
    private double distance;
    private double dayPrice;
    private Date registrationDate;
    private String routeId;
    private String vehicleId;


}
