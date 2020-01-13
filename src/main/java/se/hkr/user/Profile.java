package se.hkr.user;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Profile {
    private int aId;
    private int cId;
    private String name;
    private String surname;
    private String ssn;
    private String phone;
    private String addrs;
    private String email;

    private Profile(int aId, int cId, String name, String surname, String ssn, String phone, String addrs, String email) {
        this.aId = aId;
        this.cId = cId;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.phone = phone;
        this.addrs = addrs;
        this.email = email;
    }

    public Profile(ResultSet resultSet) throws SQLException {
        this(
                resultSet.getInt("aid"),
                resultSet.getInt("cid"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("ssn"),
                resultSet.getString("phone"),
                resultSet.getString("address"),
                ""
        );
    }

    public int getaId() {
        return aId;
    }

    public int getcId() {
        return cId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSsn() {
        return ssn;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddrs() {
        return addrs;
    }

    public String getEmail() {
        return email;
    }
}
