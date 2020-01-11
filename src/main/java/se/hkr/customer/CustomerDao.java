package se.hkr.customer;

import se.hkr.data.ConnectionProvider;
import se.hkr.data.Dao;

import java.util.function.Consumer;

public class CustomerDao extends Dao {
    public CustomerDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    public void getProfile(String email, Consumer<Profile> profileConsumer) {
        select(resultSet -> {
            try {
                if (resultSet.next()) {
                    profileConsumer.accept(new Profile(resultSet));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, "SELECT `Account`.`id` AS aid,`Customer`.`id` AS cid,`name`,`surname`,`ssn`,`phone`,`address` " +
                "FROM hotel.Customer,hotel.Account " +
                "WHERE '?' IN (SELECT `email` FROM hotel.Account) AND " +
                "hotel.Account.id = hotel.Customer.account_id;", email);
    }

}
