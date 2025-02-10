package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.SQLException;

public interface NewRouteBO extends SuperBO {
    boolean saveNewRoute(RouteDto routeDto) throws SQLException, ClassNotFoundException;
    String getNewId() throws SQLException, ClassNotFoundException;


}
