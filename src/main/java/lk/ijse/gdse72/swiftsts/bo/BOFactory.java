package lk.ijse.gdse72.swiftsts.bo;

import lk.ijse.gdse72.swiftsts.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getInstance(){
        if(boFactory==null){
            return new BOFactory();
        }
        return boFactory;
    }

    public enum BOType{
        ATTENDANCE,DRIVER,EXPENSES,NEW_ROUTE,NEW_STUDENT,NEW_VEHICLE,OVERVIEW,PAYMENT,ROUTE,SIGN_IN,SIGNUP_SECOND,STUDENT,STUDENT_REGISTRATION,UPDATE_ATTENDANCE,VEHICLE
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case DRIVER:
                return new DriverBOImpl();
            case EXPENSES:
                return new ExpensesBOImpl();
            case NEW_ROUTE:
                return new NewRouteBOImpl();
            case NEW_STUDENT:
                return  new NewStudentBOImpl();
            case NEW_VEHICLE:
                return new NewVehicleBOImpl();
            case OVERVIEW:
                return new OverviewBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case ROUTE:
                return new RouteBOImpl();
            case SIGN_IN:
                return new SignInBOImpl();
            case SIGNUP_SECOND:
                return new SignUpSecondBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case STUDENT_REGISTRATION:
                return new StudentRegistrationBOImpl();
            case UPDATE_ATTENDANCE:
                return new UpdateAttendanceBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            default:
                return null;


        }
    }
}
