package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.RouteBO;
import lk.ijse.gdse72.swiftsts.dao.custom.RouteDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.RouteDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class RouteBOImpl implements RouteBO {

    RouteDAO routeDAO =  new RouteDAOImpl();

    @Override
    public ArrayList<RouteDto> getAllRoutes() throws SQLException {
        return routeDAO.getAllData();
    }

    @Override
    public String getNewId() throws SQLException {
        return routeDAO.getNewId();
    }

    @Override
    public boolean saveRoute(RouteDto routeDto) throws SQLException {
        return routeDAO.save(routeDto);
    }

    @Override
    public boolean updateRoute(RouteDto routeDto) throws SQLException {
        return routeDAO.update(routeDto);
    }

    @Override
    public boolean deleteRoute(String routeId) throws SQLException {
        return routeDAO.delete(routeId);
    }
}
