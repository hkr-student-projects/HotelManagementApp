package hkrFX;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Config implements ISerializable<String>, IDeserializable<Config>{
    private String _languageCode;

    public Config(){
    }

    @Override
    public Config Deserialize(FileReader reader) {
        Config config = new Config();
        try
        {
            JSONArray json = (JSONArray)new JSONParser().parse(reader);
//            json.forEach(el ->
//                    config.setLanguageCode(((JSONObject)el).get("lang").toString()), cannot because additional logic needed
//                    config.setLanguageCode(((JSONObject)el).get("lang").toString())
//            );
            for(Object field : json){
                for(Object key : ((JSONObject)field).keySet()){
                    if(MainFX.equals(key.toString().toCharArray(), "lang".toCharArray()))
                        config.setLanguageCode(((JSONObject)field).get("lang").toString());
                    //other config fields to retrieve  ?switch
                }
            }

        }  catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return config;
    }

    @Override
    public String Serialize() {

        JSONObject obj = new JSONObject();
        obj.put("lang", _languageCode);
        JSONArray json = new JSONArray();
        json.add(obj);

        return json.toJSONString();
    }

    public String getLanguageCode(){
        return _languageCode;
    }

    public void setLanguageCode(String langCode){
        _languageCode = langCode;
    }

    private void loadDefaults(){
        this._languageCode = "en";
    }
}
