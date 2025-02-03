package lk.ijse.gdse72.swiftsts.bo.custom.impl;

import lk.ijse.gdse72.swiftsts.bo.custom.NewRouteBO;
import lk.ijse.gdse72.swiftsts.dao.custom.RouteDAO;
import lk.ijse.gdse72.swiftsts.dao.custom.impl.RouteDAOImpl;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.SQLException;

public class NewRouteBOImpl implements NewRouteBO {

    RouteDAO routeDAO = new RouteDAOImpl();

    @Override
    public boolean saveNewRoute(RouteDto routeDto) throws SQLException {
        return routeDAO.save(routeDto);
    }

    @Override
    public String getNewId() throws SQLException {
        return routeDAO.getNewId();
    }
}
