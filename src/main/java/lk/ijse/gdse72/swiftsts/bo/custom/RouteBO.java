package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RouteBO {
    public ArrayList<RouteDto> getAllRoutes() throws SQLException;
    public String getNextRouteId() throws SQLException;
    public boolean saveRoute(RouteDto routeDto) throws SQLException;
    public boolean updateRoute(RouteDto routeDto) throws SQLException;
    public boolean deleteRoute(String routeId) throws SQLException;
    public double getRouteFeeByRouteId(String routeId) throws SQLException;
    public String getRouteIdByRouteName(String routeName) throws SQLException;
    public List<String> getAllDestinations() throws SQLException;
    public List<String> getAllRouteNames() throws SQLException;
}
