package lk.ijse.gdse72.swiftsts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vehicle {
    private String vehicleId ;
    private String registrationNo ;
    private String vehicleType ;
    private double EngineCapacity ;
    private String fuelType ;
    private String model ;
    private int seatCount ;
    private int availableSeatCount ;
}
