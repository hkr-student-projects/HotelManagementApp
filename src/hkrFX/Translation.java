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

public class Translation implements ISerializable<String>, IDeserializable<HashMap<String, String>> {

    private static Map<String, String> _translations;

    static {
        _translations = new HashMap<String, String>();
    }

    public Translation(){
        loadDefaults();
    }

    public String translate(String keyID){

        for (Map.Entry<String, String> pair : _translations.entrySet())
            if(pair.getKey() == keyID)
                return pair.getValue();

        return null;
    }

    @Override
    public String Serialize() {

        JSONObject obj = new JSONObject();
        _translations.entrySet().forEach(pair -> obj.put(pair.getKey(), pair.getValue()));
        JSONArray json = new JSONArray();
        json.add(obj);

        return json.toJSONString();
    }

    @Override
    public HashMap<String, String> Deserialize(FileReader reader) {

        HashMap<String, String> tran = new HashMap<String, String>();
        try
        {
            JSONParser jsonParser = new JSONParser();
            Object json = jsonParser.parse(reader);

            JSONArray arr = (JSONArray) json;
            arr.forEach( obj -> {
                ((JSONObject)obj).forEach((key, value) -> {
                    tran.put((String)key, (String)value);
                });
            } );

        }  catch (IOException | ParseException e) {
            System.out.println("Invalid json format");
            e.printStackTrace();
        }


        return tran;
    }

    public void loadLanguage(String code){

        File f = new File(""+ code +".translation.json");
        if(!f.exists()){

//            try (FileWriter file = new FileWriter("employees.json", false)) {
//
//                file.write(this.Serialize());
//                file.flush();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    private void loadTranslation(){
        File f = new File(""+ MainFX.getConfig().getLanguageCode() +".translation.json");
        if(!f.exists()){
            try (FileWriter file = new FileWriter("config.json", false)) {

                MainFX._config.setLanguageCode("en");
                String json = MainFX._config.Serialize();
                file.write(json);
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {

            try (FileReader reader = new FileReader("config.json"))
            {
                JSONParser jsonParser = new JSONParser();
                Object json = jsonParser.parse(reader);
                //MainFX._config = MainFX._config.Deserialize(reader);

            }
            catch (IOException | ParseException e) {
                System.out.println("Invalid json format");
                e.printStackTrace();
            }
        }
    }

    private void loadDefaults(){

        try (FileWriter file = new FileWriter("en.translation.json", false)) {

            _translations.put("button_not_found", "Unable to find the button {0} in {1}");
            _translations.put("json_format_fail", "Incorrect json in {0}");
            _translations.put("field_format_fail", "The field {0} in {1} is empty");
            _translations.put("field_empty", "The field {0} in {1} is empty");
            _translations.put("login_password_fail", "Login: {0} or password: {1} is incorrect");
            _translations.put("keyid_not_found", "KeyID {0} was not found");
            file.write(this.Serialize());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
