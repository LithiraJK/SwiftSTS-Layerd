package lk.ijse.gdse72.swiftsts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Route {
    private String routeId;
    private String routeName;
    private String startPoint;
    private String destination;
    private double routeFee;
}
