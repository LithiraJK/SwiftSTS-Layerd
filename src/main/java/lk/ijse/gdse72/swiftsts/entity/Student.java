package lk.ijse.gdse72.swiftsts.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String studentId;
    private String studentName;
    private String parentName;
    private String address;
    private String email;
    private String studentGrade;
    private String phoneNo;
    private double creditBalance;

}
