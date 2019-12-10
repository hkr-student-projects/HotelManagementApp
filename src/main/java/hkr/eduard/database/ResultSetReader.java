package hkr.eduard.database;

import java.sql.ResultSet;

public class ResultSetReader implements ResultReader {

    private ResultSet resultSet;

    public ResultSetReader(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public boolean hasNext() {
        try {
            return this.resultSet.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public String readString(String key) {
        try {
            return this.resultSet.getString(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
