package hkrFX;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FxmlConverter {

    public static void documentBuild(String xmlPath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Node entry = db.parse(xmlPath);
        //out.println(entry.getBaseURI());
        String[] name = entry.getBaseURI().split("/");
        String out = name[name.length - 1].split("\\.")[0];
        File f = new File(""+ out +".txt");
        if(f.exists())
            f.delete();
        getChildRecursive(entry, out);

        //out.println(entry.getFirstChild().getAttributes().item(0));

    }

    private static void getChildRecursive(Node node, String path) throws IOException {
        NodeList childs = node.getChildNodes();
        //String fullPath = "";
        for (short i = 0; i < childs.getLength(); i++){

            Node child = childs.item(i);

            if(child.getBaseURI() == null)
                continue;

            //out.println("\n");

            writeNodeObj(child, path);
            //fullPath += child.getParentNode().getNodeName() + "-";
            //out.print(fullPath);
            NamedNodeMap attr = childs.item(i).getAttributes();
            if(attr == null)
                continue;
            writeNodeAttrs(child, attr, path);
//            for(short j = 0; j < attr.getLength(); j++)
//                out.print(attr.item(j) + " ");
            //out.println(child.getNodeName()+"-"+child.getParentNode().getNodeName());
            getChildRecursive(child, path);
            //out.println(fullPath + " " + child.getNodeName());
            //out.println(i);
        }
    }

    private static void writeNodeObj(Node node, String path) throws IOException {
        if(node.getNodeName() == "children")
            return;
        //out.println(path);
        File f = new File(""+ path +".txt");
        f.createNewFile();
        try(FileWriter writer = new FileWriter(""+ path +".txt", true))
        {
            writer.write(""+ node.getNodeName() + " " + node.getNodeName().toLowerCase() +" = new "+ node.getNodeName() +"();\n");
        }
        catch (Exception e){
            Logger.logException(e);
        }
    }

    private static void writeNodeAttrs(Node node, NamedNodeMap attr, String path) throws IOException {

        //out.println(path);
        File f = new File(""+ path +".txt");
        f.createNewFile();
        try(FileWriter writer = new FileWriter(""+ path +".txt", true))
        {
            //writer.write(""+ node.getNodeName() + " " + node.getNodeName().toLowerCase() +" = new "+ node.getNodeName() +"();\n");

            for(Map.Entry<String, String> pair : getAttrValue(attr).entrySet()){
                writer.write(""+ node.getNodeName().toLowerCase() +".set"+ pair.getKey().substring(0,1).toUpperCase() + pair.getKey().substring(1) +"("+ pair.getValue() +");\n");
            }

        }
        catch (Exception e){
            Logger.logException(e);
        }
    }

    private static Map<String, String> getAttrValue(NamedNodeMap attr){
        HashMap<String, String> pair = new HashMap<String, String>();

        for(short j = 0; j < attr.getLength(); j++){
            String[] str = attr.item(j).toString().split("=");
            if(Main.equals(str[0].toCharArray(), "fx:controller".toCharArray())
                    || Main.equals(str[0].toCharArray(), "xmlns".toCharArray())
                    || Main.equals(str[0].toCharArray(), "xmlns:fx".toCharArray()))
                continue;
            String uncovered = str[1].substring(1, str[1].length() - 1);
            pair.put(str[0], tryParseDouble(uncovered) ? uncovered : str[1]);
        }

        return pair;
    }

    public static boolean tryParseDouble(String str){
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
