package lk.ijse.gdse72.swiftsts.dto.tm;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleTM {
    private String vehicleId ;
    private String registrationNo ;
    private String vehicleType ;
    private double EngineCapacity ;
    private String fuelType ;
    private String model ;
    private int seatCount ;
    private int availableSeatCount ;
}
