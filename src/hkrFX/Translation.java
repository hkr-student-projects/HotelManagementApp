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

    }

    public String translate(String keyID){
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
            try (FileWriter file = new FileWriter("employees.json", false)) {

                file.write(this.Serialize());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadDefaults(){

        try (FileWriter file = new FileWriter("en.translation.json", false)) {

            _translations.put("find_button_fail", "Unable to find the button {0} in {1}");
            _translations.put("json_format_fail", "");
            file.write(this.Serialize());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
