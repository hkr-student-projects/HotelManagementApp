package hkr.eduard.database;

public interface ResultReader {

    boolean hasNext();

    String readString(String key);

}
