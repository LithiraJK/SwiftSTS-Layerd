package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;
import lk.ijse.gdse72.swiftsts.entity.Route;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RouteDAO extends CrudDAO<Route> {
    double getRouteFeeByRouteId(String routeId) throws SQLException;
    String getRouteIdByRouteName(String routeName) throws SQLException;
    List<String> getAllDestinations() throws SQLException;
    List<String> getAllRouteNames() throws SQLException;
}
