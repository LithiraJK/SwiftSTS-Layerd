package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RouteBO extends SuperBO {
    public ArrayList<RouteDto> getAllRoutes() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean saveRoute(RouteDto routeDto) throws SQLException;
    public boolean updateRoute(RouteDto routeDto) throws SQLException;
    public boolean deleteRoute(String routeId) throws SQLException;
}
