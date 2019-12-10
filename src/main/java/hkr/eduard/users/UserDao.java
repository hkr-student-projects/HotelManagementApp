package hkr.eduard.users;

import hkr.eduard.database.ConnectionProvider;
import hkr.eduard.database.Dao;

import java.util.function.Consumer;

public class UserDao extends Dao {
    public UserDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void login(String login, String password, Consumer<Person> reader) {
        select(resultReader -> {
            if (resultReader.hasNext()) {
                if (resultReader.readString("password").equals(password)) {
                    EmployeeRole role = EmployeeRole.valueOf(resultReader.readString("role"));
                    switch (role) {
                        case EMPLOYEE:
                            reader.accept(new Employee(
                                    resultReader.readString("name"),
                                    resultReader.readString("last_name"),
                                    resultReader.readString("pid"),
                                    resultReader.readString("phone_number"),
                                    resultReader.readString("password"),
                                    0,
                                    role));
                        case ADMIN:
                            reader.accept(new Admin(
                                    resultReader.readString("name"),
                                    resultReader.readString("last_name"),
                                    resultReader.readString("pid"),
                                    resultReader.readString("phone_number"),
                                    resultReader.readString("password"),
                                    0,
                                    role));
                        default:
                            System.out.println();
                    }
                }
            }
        }, "SELECT * FROM users WHERE name = ? LIMIT 1", login);
    }

}
