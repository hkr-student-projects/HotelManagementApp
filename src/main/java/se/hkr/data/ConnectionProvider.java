package se.hkr.data;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {

    Connection openConnection() throws SQLException;

}
