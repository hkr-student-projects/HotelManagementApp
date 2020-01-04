package hkrFX;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config implements ISerializable<String>{

    public String languageCode;
    public String DatabaseAddress;
    public String DatabaseUsername;
    public String DatabasePassword;
    public String DatabaseName;
    public int DatabasePort;

    public Config(){

    }

    public void Deserialize(FileReader reader) {
        try
        {
//            JSONParser jsonParser = new JSONParser();
//            Object obj = jsonParser.parse(reader);

            JSONArray json = (JSONArray) new JSONParser().parse(reader);
//            json.forEach(el ->
//                    config.setLanguageCode(((JSONObject)el).get("lang").toString()), cannot because additional logic needed
//                    config.setLanguageCode(((JSONObject)el).get("lang").toString())
//            );
            for(Object field : json){
                for(Object key : ((JSONObject)field).keySet()){
                    //if(MainFX.equals(key.toString().toCharArray(), "lang".toCharArray()))
                   //   languageCode = ((JSONObject)field).get("lang").toString();
                    switch (key.toString()){
                        case "lang" :
                            languageCode = ((JSONObject)field).get("lang").toString();
                            break;
                        case "DatabaseAddress" :
                            DatabaseAddress = ((JSONObject)field).get("DatabaseAddress").toString();
                            break;
                        case "DatabaseUsername" :
                            DatabaseUsername = ((JSONObject)field).get("DatabaseUsername").toString();
                            break;
                        case "DatabasePassword" :
                            DatabasePassword = ((JSONObject)field).get("DatabasePassword").toString();
                            break;
                        case "DatabaseName" :
                            DatabaseName = ((JSONObject)field).get("DatabaseName").toString();
                            break;
                        case "DatabasePort" :
                            DatabasePort = Integer.parseInt((((JSONObject)field).get("DatabasePort")).toString());
                            break;
                        default:
                            break;
                    }
                }
            }

        }  catch (IOException | ParseException ex) {
            Logger.logException(ex);
        }
    }

    @Override
    public String Serialize() {

        JSONObject obj = new JSONObject();
        obj.put("lang", languageCode);
        obj.put("DatabaseAddress", DatabaseAddress);
        obj.put("DatabaseUsername", DatabaseUsername);
        obj.put("DatabasePassword", DatabasePassword);
        obj.put("DatabaseName", DatabaseName);
        obj.put("DatabasePort", DatabasePort);
        JSONArray json = new JSONArray();
        json.add(obj);

        return json.toJSONString();
    }

    public void loadDefaults(){
        languageCode = "en";
        DatabaseAddress = "jdbc:mysql://localhost/hotel?serverTimezone=Europe/Stockholm&allowMultiQueries=true&useSSL=false";
        DatabaseUsername = "root";
        DatabasePassword = "password";
        DatabaseName = "hotel";
        DatabasePort = 3306;
        writeConfig();
    }

    public void writeConfig(){
        try (FileWriter file = new FileWriter("config.json", false)) {
            file.write(Serialize());
            file.flush();
        } catch (IOException ex) {
            Logger.logException(ex);
        }
    }
}
