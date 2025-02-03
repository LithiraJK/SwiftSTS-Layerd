package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.RouteBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.RouteDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.RouteDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;
import lk.ijse.gdse72.swiftsts.entity.Route;

import java.sql.SQLException;
import java.util.ArrayList;

public class RouteBOImpl implements RouteBO {

    RouteDAO routeDAO = (RouteDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROUTE);

    @Override
    public ArrayList<RouteDto> getAllRoutes() throws SQLException {
        ArrayList<Route> routes = routeDAO.getAllData();
        ArrayList<RouteDto> routeDtos = new ArrayList<>();

        for (Route route : routes){
            routeDtos.add(new RouteDto(
                    route.getRouteId(),
                    route.getRouteName(),
                    route.getStartPoint(),
                    route.getDestination(),
                    route.getRouteFee()
            ));
        }
        return routeDtos;
    }

    @Override
    public String getNewId() throws SQLException {
        return routeDAO.getNewId();
    }

    @Override
    public boolean saveRoute(RouteDto dto) throws SQLException {
        return routeDAO.save(new Route( dto.getRouteId(),
                dto.getRouteName(),
                dto.getStartPoint(),
                dto.getDestination(),
                dto.getRouteFee()
        ));
    }

    @Override
    public boolean updateRoute(RouteDto dto) throws SQLException {
        return routeDAO.update(new Route( dto.getRouteId(),
                dto.getRouteName(),
                dto.getStartPoint(),
                dto.getDestination(),
                dto.getRouteFee()
        ));
    }

    @Override
    public boolean deleteRoute(String routeId) throws SQLException {
        return routeDAO.delete(routeId);
    }
}
