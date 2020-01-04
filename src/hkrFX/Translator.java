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

    private static Map<String, String> translations;

    static {
        translations = new HashMap<String, String>();
        loadDefaults();
        loadTranslation();
        //runs before static methods
    }

//    public Translator(){
//
//    }

    public static String translate(String keyID, Object... args){
        for (Map.Entry<String, String> pair : translations.entrySet())
            if(pair.getKey() == keyID)
                return String.format(pair.getValue(), args);

        Logger.logError("KeyID "+ keyID +" was not found");
        return null;
    }

    //@Override
    private static String Serialize() {

        JSONObject obj = new JSONObject();
        translations.entrySet().forEach(pair -> obj.put(pair.getKey(), pair.getValue()));
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
                    translations.put((String)key, (String)value);
                });
            } );

        }  catch (IOException | ParseException ex) {
            Logger.logException(ex);
        }

        //return tran;
    }

    private static void loadTranslation(){

        File f = new File(""+ MainFX.config.languageCode +".translation.json");
        if(!f.exists()){
            Logger.logError("Unable to load language pack: "+ MainFX.config.languageCode +", because pack does not exist");
        }
        else {

            try (FileReader reader = new FileReader("en.translation.json"))
            {
                Deserialize(reader);
            }
            catch (IOException ex) {
                Logger.logException(ex);
                return;
            }
        }
    }

    private static void loadDefaults(){
        try (FileWriter file = new FileWriter("en.translation.json", false)) {

            translations.put("button_not_found", "Unable to find the button %s in %s");
            translations.put("json_format_fail", "Incorrect json in %s");
            translations.put("field_format_fail", "The field %s in %s is empty");
            translations.put("field_empty", "The field %s in %s is empty");
            translations.put("login_password_fail", "Login: %s or password: %s is incorrect");
            translations.put("keyid_not_found", "KeyID %s was not found");
            translations.put("localization_not_found", "Language pack %s was not found");
            file.write(Serialize());
            file.flush();

        } catch (IOException ex) {
            Logger.logException(ex);
        }
    }
}
