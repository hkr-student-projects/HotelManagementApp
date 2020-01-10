package se.hkr.config;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class JsonConfiguration implements Configuration {

    private String path;
    private Map<String, Object> values;
    private Gson gson;

    public JsonConfiguration(String filePath) throws Exception {
        this.values = new HashMap<>();
        this.gson = new Gson();
        File file = new File(filePath);
        if (file.exists()) {
            byte[] data = Files.readAllBytes(file.toPath());
            String value = new String(data);
            this.values = this.gson.fromJson(value, this.values.getClass());
            this.path = filePath;
        } else {
            throw new FileNotFoundException("The configuration file: " + filePath + " was not found!");
        }
    }

    @Override
    public String getString(String key) {
        return (String) this.values.get(key);
    }

    @Override
    public int getInt(String key) {
        //For some reason GSON parses integer values as double so store the value as a double then cast.
        double value = (double) this.values.get(key);
        return (int) value;
    }

    @Override
    public double getDouble(String key) {
        return (double) this.values.get(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return (boolean) this.values.get(key);
    }

    @Override
    public void close() throws IOException {
        try (FileWriter file = new FileWriter(this.path, false)) {
            file.write(this.gson.toJson(this.values));
            file.flush();
        } catch (IOException ex) {
            //TODO: Logger.logException(ex);
        }
    }
}
