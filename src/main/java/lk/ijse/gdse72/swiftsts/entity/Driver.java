package lk.ijse.gdse72.swiftsts.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Driver {
    private String driverId;
    private String name;
    private String licenseNo;
    private String nic;
    private String contactNo;
    private String address;
    private String email;
}