package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RouteBO extends SuperBO {
    ArrayList<RouteDto> getAllRoutes() throws SQLException, ClassNotFoundException;
    String getNewId() throws SQLException, ClassNotFoundException;
    boolean saveRoute(RouteDto routeDto) throws SQLException, ClassNotFoundException;
    boolean updateRoute(RouteDto routeDto) throws SQLException, ClassNotFoundException;
    boolean deleteRoute(String routeId) throws SQLException, ClassNotFoundException;
}
