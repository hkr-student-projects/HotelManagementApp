package hkrFX;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Translator{// implements ISerializable<String>, IDeserializable<HashMap<String, String>> {

    private static Map<String, String> _translations;

    static {
        _translations = new HashMap<String, String>();
        loadDefaults();
        loadTranslation();
    }

//    public Translator(){
//
//    }

    public static String translate(String keyID){

        for (Map.Entry<String, String> pair : _translations.entrySet())
            if(pair.getKey() == keyID)
                return pair.getValue();

        Logger.LogError("KeyID "+ keyID +" was not found");
        return null;
    }

    //@Override
    private static String Serialize() {

        JSONObject obj = new JSONObject();
        _translations.entrySet().forEach(pair -> obj.put(pair.getKey(), pair.getValue()));
        JSONArray json = new JSONArray();
        json.add(obj);

        return json.toJSONString();
    }

    //@Override
    private static void Deserialize(FileReader reader) {

        //HashMap<String, String> tran = new HashMap<String, String>();
        try
        {
            JSONParser jsonParser = new JSONParser();
            Object json = jsonParser.parse(reader);

            JSONArray arr = (JSONArray) json;
            arr.forEach( obj -> {
                ((JSONObject)obj).forEach((key, value) -> {
                    _translations.put((String)key, (String)value);
                });
            } );

        }  catch (IOException | ParseException e) {
            Logger.LogException(e.getMessage());
        }

        //return tran;
    }

    private static void loadTranslation(){

        File f = new File(""+ MainFX.getConfig().getLanguageCode() +".translation.json");
        if(!f.exists()){
            Logger.LogError("Unable to load language pack: "+ MainFX.getConfig().getLanguageCode() +", because pack does not exist");
        }
        else {

            try (FileReader reader = new FileReader(""+ MainFX.getConfig().getLanguageCode() +".translation.json"))
            {
                JSONParser jsonParser = new JSONParser();
                Object json = jsonParser.parse(reader);
                Deserialize(reader);
            }
            catch (IOException | ParseException e) {
                Logger.LogException(e.getMessage());
                return;
            }
        }
    }

    private static void loadDefaults(){
        try (FileWriter file = new FileWriter("en.translation.json", false)) {

            _translations.put("button_not_found", "Unable to find the button {0} in {1}");
            _translations.put("json_format_fail", "Incorrect json in {0}");
            _translations.put("field_format_fail", "The field {0} in {1} is empty");
            _translations.put("field_empty", "The field {0} in {1} is empty");
            _translations.put("login_password_fail", "Login: {0} or password: {1} is incorrect");
            _translations.put("keyid_not_found", "KeyID {0} was not found");
            file.write(Serialize());
            file.flush();

        } catch (IOException e) {
            Logger.LogException(e.getMessage());
        }
    }
}
