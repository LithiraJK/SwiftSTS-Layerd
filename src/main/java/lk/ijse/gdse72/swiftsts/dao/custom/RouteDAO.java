package lk.ijse.gdse72.swiftsts.dao.custom;

import lk.ijse.gdse72.swiftsts.dao.CrudDAO;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RouteDAO extends CrudDAO<RouteDto> {
    public ArrayList<RouteDto> getAllData() throws SQLException;
    public String getNewId() throws SQLException;
    public boolean save(RouteDto routeDto) throws SQLException;
    public boolean update(RouteDto routeDto) throws SQLException;
    public boolean delete(String routeId) throws SQLException;
    public double getRouteFeeByRouteId(String routeId) throws SQLException;
    public String getRouteIdByRouteName(String routeName) throws SQLException;
    public List<String> getAllDestinations() throws SQLException;
    public List<String> getAllRouteNames() throws SQLException;
}
