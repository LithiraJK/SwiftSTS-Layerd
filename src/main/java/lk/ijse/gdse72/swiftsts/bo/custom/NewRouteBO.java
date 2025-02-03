package lk.ijse.gdse72.swiftsts.bo.custom;

import lk.ijse.gdse72.swiftsts.bo.SuperBO;
import lk.ijse.gdse72.swiftsts.dto.RouteDto;

import java.sql.SQLException;

public interface NewRouteBO extends SuperBO {

    public boolean saveNewRoute(RouteDto routeDto) throws SQLException;
    public String getNewId() throws SQLException;


}
