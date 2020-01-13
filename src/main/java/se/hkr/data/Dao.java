package se.hkr.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.function.Consumer;

public abstract class Dao {

    private final ConnectionProvider connectionProvider;

    protected Dao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    protected void select(Consumer<ResultSet> resultReader, String query, Object... parameters) {
        try {

            Connection connection = this.connectionProvider.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement = setParameters(preparedStatement, parameters);
            System.out.println(preparedStatement);
            resultReader.accept(preparedStatement.executeQuery());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void delete(String query, Object... parameters) {
        try {
            Connection connection = this.connectionProvider.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = setParameters(preparedStatement, parameters);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void insert(String query, Object... parameters) {
        insert(query, null, parameters);
    }

    protected void insert(String query, Consumer<Integer> callback, Object... parameters) {
        try {
            Connection connection = this.connectionProvider.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = setParameters(preparedStatement, parameters);
            preparedStatement.executeUpdate();

            if (callback != null) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int last_inserted_id = rs.getInt(1);
                    callback.accept(last_inserted_id);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void update(String query, Object... parameters) {
        try {

            Connection connection = this.connectionProvider.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement = setParameters(preparedStatement, parameters);
            preparedStatement.execute();
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
