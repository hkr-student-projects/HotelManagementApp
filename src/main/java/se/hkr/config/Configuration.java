package se.hkr.config;

import java.io.Closeable;

public interface Configuration extends Closeable {
    String getString(String key);

    int getInt(String key);

    double getDouble(String key);

    boolean getBoolean(String key);
}
