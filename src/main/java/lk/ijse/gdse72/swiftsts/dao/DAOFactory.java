package lk.ijse.gdse72.swiftsts.dao;

import lk.ijse.gdse72.swiftsts.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance (){
        if(daoFactory == null){
            return new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType{
        ATTENDANCE,DRIVER,EXPENSE,PAYMENT,QUERY,ROUTE,STUDENT,STUDENTREGISTRATION,USER,VEHICLE
    }

    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case ATTENDANCE :
                return new AttendanceDAOImpl();
            case DRIVER:
                return new DriverDAOImpl();
            case EXPENSE:
                return new ExpenseDAOImpl();
            case ROUTE:
                return new RouteDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case USER:
                return new UserDAOImpl();
            case STUDENTREGISTRATION:
                return new StudentRegistrationDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }

    }
}
