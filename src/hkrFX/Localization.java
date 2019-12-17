package hkrFX;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Localization {

    private static String[] _bookingStage;
    private static String[] _mainMenu;
    private static String[] _bookingInfo;
    private static String[] _adminPanel;

    static {

    }

    public static void load(String langCode){

        File f = new File(""+ MainFX.config.getLanguageCode() +".translation.json");
        if(!f.exists()){
            Logger.logError("Unable to load language pack: "+ MainFX.config.getLanguageCode() +", because pack does not exist");
        }
        else {

            try (FileReader reader = new FileReader(""+ MainFX.config.getLanguageCode() +".translation.json"))
            {
                Deserialize(reader);
            }
            catch (IOException e) {
                Logger.logException(e.getMessage());
                return;
            }
        }
    }

    private static String Serialize() {

        JSONObject obj = new JSONObject();
        //_translations.entrySet().forEach(pair -> obj.put(pair.getKey(), pair.getValue()));
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
                    //_translations.put((String)key, (String)value);
                });
            } );

        }  catch (IOException | ParseException e) {
            Logger.logException(e.getMessage());
        }

        //return tran;
    }

    private static void loadLocalization(){
        File d = new File("Localization");
        if(!d.exists())
            d.mkdir();
        File[] fs = new File[] {
                new File("Localization//"+ MainFX.config.getLanguageCode() +".menu.json"),
                new File("Localization//"+ MainFX.config.getLanguageCode() +".bookinginfo.json"),
                new File("Localization//"+ MainFX.config.getLanguageCode() +".bookingform.json"),
                new File("Localization//"+ MainFX.config.getLanguageCode() +".adminpanel.json")
        };
        for(File f : fs){
            if(!f.exists()){
                Logger.logError("Unable to load localization language pack: "+ MainFX.config.getLanguageCode() +", because pack does not exist");
                return;
            }
            else {

                try (FileReader reader = new FileReader(""+ MainFX.config.getLanguageCode() +".translation.json"))
                {
                    Deserialize(reader);
                }
                catch (IOException e) {
                    Logger.logException(e.getMessage());
                    return;
                }
            }
        }
    }

//    private static void loadDefaults(){
//        try (FileWriter file = new FileWriter("en.translation.json", false)) {
//
//            _translations.put("button_not_found", "Unable to find the button {0} in {1}");
//            _translations.put("json_format_fail", "Incorrect json in {0}");
//            _translations.put("field_format_fail", "The field {0} in {1} is empty");
//            _translations.put("field_empty", "The field {0} in {1} is empty");
//            _translations.put("login_password_fail", "Login: {0} or password: {1} is incorrect");
//            _translations.put("keyid_not_found", "KeyID {0} was not found");
//            file.write(Serialize());
//            file.flush();
//
//        } catch (IOException e) {
//            Logger.logException(e.getMessage());
//        }
//    }

}
