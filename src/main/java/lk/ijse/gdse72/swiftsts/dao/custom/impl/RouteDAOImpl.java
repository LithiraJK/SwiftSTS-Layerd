package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.RouteDAO;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    @Override
    public ArrayList<RouteDto> getAllRoutes() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Route");
        ArrayList<RouteDto> routeDtos = new ArrayList<>();

        while (rst.next()) {
            RouteDto routeDto = new RouteDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)
            );
            routeDtos.add(routeDto);
        }

        return routeDtos;
    }
    @Override
    public String getNextRouteId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT routeId FROM Route ORDER BY routeId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("R%03d", newIdIndex);
        }
        return "R001";
    }

    @Override
    public boolean saveRoute(RouteDto routeDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Route VALUES (?,?,?,?,?)",
                routeDto.getRouteId(),
                routeDto.getRouteName(),
                routeDto.getStartPoint(),
                routeDto.getDestination(),
                routeDto.getRouteFee()
        );
    }

    @Override
    public boolean updateRoute(RouteDto routeDto) throws SQLException {
        return SQLUtil.execute("UPDATE Route SET routeName=?, startPoint=?, destination=?, routeFee=? WHERE routeId=?",
                routeDto.getRouteName(),
                routeDto.getStartPoint(),
                routeDto.getDestination(),
                routeDto.getRouteFee(),
                routeDto.getRouteId()
        );
    }

    @Override
    public boolean deleteRoute(String routeId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Route WHERE routeId=?", routeId);
    }

    @Override
    public double getRouteFeeByRouteId(String routeId) throws SQLException {
        String query = "SELECT RouteFee FROM Route WHERE RouteId = ?";
        ResultSet rs = SQLUtil.execute(query, routeId);

        if (rs.next()) {
            return rs.getDouble("RouteFee");
        } else {
            throw new SQLException("Route ID not found in Route table");
        }
    }

    @Override
    public String getRouteIdByRouteName(String routeName) throws SQLException {
        String query = "SELECT RouteId FROM Route WHERE RouteName = ?";
        ResultSet rs = SQLUtil.execute(query, routeName);

        if (rs.next()) {
            return rs.getString("RouteId");
        } else {
            throw new SQLException("Route Name not found in Route table");
        }
    }

    @Override
    public List<String> getAllDestinations() throws SQLException {
        List<String> destinations = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT Destination FROM Route");
        while (resultSet.next()) {
            destinations.add(resultSet.getString("Destination"));
        }
        return destinations;
    }

    @Override
    public List<String> getAllRouteNames() throws SQLException {
        List<String> routes = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT RouteName FROM Route");
        while (resultSet.next()) {
            routes.add(resultSet.getString("RouteName"));
        }
        return routes;
    }
}
