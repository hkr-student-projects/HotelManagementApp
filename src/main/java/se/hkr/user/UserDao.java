package se.hkr.user;

import se.hkr.data.ConnectionProvider;
import se.hkr.data.Dao;

import java.util.function.Consumer;

public class UserDao extends Dao {
    public UserDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void tryLogin(String email, String password, Consumer<Boolean> loginResult) {
        select(resultSet -> {
            try {
                if (resultSet.next()) {
                    loginResult.accept(true);
                } else {
                    loginResult.accept(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, "SELECT 1 FROM Account WHERE Account.email = '?' AND Account.password = SHA1('?');", email, password);
    }

}
