package hkr.eduard.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionProvider implements ConnectionProvider {

    private final String host;
    private final String user;
    private final String pass;
    private final String db;
    private final int port;

    public MySQLConnectionProvider(String host, String user, String pass, String db, int port) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.db = db;
        this.port = port;
    }

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db, this.user, this.pass);
    }
}
