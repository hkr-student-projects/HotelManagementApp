package hkr.eduard.database;

public interface ResultReader {

    boolean hasNext();

    String readString(String key);

    int readInt(String key);

    double readDouble(String key);

}
