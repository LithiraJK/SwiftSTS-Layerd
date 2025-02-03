package lk.ijse.gdse72.swiftsts.dao.custom.impl;

import lk.ijse.gdse72.swiftsts.dao.SQLUtil;
import lk.ijse.gdse72.swiftsts.dao.custom.RouteDAO;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;
import lk.ijse.gdse72.swiftsts.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    @Override
    public ArrayList<Route> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Route");
        ArrayList<Route> routes = new ArrayList<>();

        while (rst.next()) {
            routes.add(new Route(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)
            ));
        }
        return routes;
    }
    @Override
    public String getNewId() throws SQLException {
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
    public boolean save(Route route) throws SQLException {
        return SQLUtil.execute("INSERT INTO Route VALUES (?,?,?,?,?)",
                route.getRouteId(),
                route.getRouteName(),
                route.getStartPoint(),
                route.getDestination(),
                route.getRouteFee()
        );
    }

    @Override
    public boolean update(Route route) throws SQLException {
        return SQLUtil.execute("UPDATE Route SET routeName=?, startPoint=?, destination=?, routeFee=? WHERE routeId=?",
                route.getRouteName(),
                route.getStartPoint(),
                route.getDestination(),
                route.getRouteFee(),
                route.getRouteId()
        );
    }

    @Override
    public boolean delete(String routeId) throws SQLException {
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
