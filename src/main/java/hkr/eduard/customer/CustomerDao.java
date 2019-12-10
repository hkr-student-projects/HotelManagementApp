package hkr.eduard.customer;

import hkr.eduard.database.ConnectionProvider;
import hkr.eduard.database.Dao;

public class CustomerDao extends Dao {

    public CustomerDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

}