package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.NewRouteBO;
import lk.ijse.gdse72.swiftsts.dao.DAOFactory;
import lk.ijse.gdse72.swiftsts.dao.custom.RouteDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.RouteDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;
import lk.ijse.gdse72.swiftsts.entity.Route;

import java.sql.SQLException;

public class NewRouteBOImpl implements NewRouteBO {

    RouteDAO routeDAO = (RouteDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROUTE);

    @Override
    public boolean saveNewRoute(RouteDto routeDto) throws SQLException {
        return routeDAO.save(new Route(routeDto.getRouteId(),routeDto.getRouteName(),routeDto.getStartPoint(),routeDto.getDestination(),routeDto.getRouteFee()));
    }

    @Override
    public String getNewId() throws SQLException {
        return routeDAO.getNewId();
    }
}
