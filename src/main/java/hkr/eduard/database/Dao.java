package hkr.eduard.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

public abstract class Dao {

    private final ConnectionProvider connectionProvider;

    protected Dao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    protected void select(Consumer<ResultReader> resultReader, String query, Object... parameters) {
        try {

            Connection connection = this.connectionProvider.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement = setParameters(preparedStatement, parameters);
            resultReader.accept(new ResultSetReader(preparedStatement.executeQuery()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static PreparedStatement setParameters(PreparedStatement preparedStatement, Object... parameters) throws Exception {
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i + 1, parameters[i]);
        }

        return preparedStatement;
    }

}
