package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Colors {
    private static final List<String> colorNames= Arrays.asList(
        "black",    //0
        "red",      //1
        "green",    //2
        "yellow",   //3
        "blue",     //4
        "purple",   //5
        "cyan",     //6
        "gray",     //7
        null,       //8
        "white"     //9
    );

    private static final List<String> modifierNames= Arrays.asList(
        "R",        //0
        "BOLD",     //1
        "SOFT",     //2
        "ITALIC",   //3
        "LINE",     //4
        "BLINK",    //5
        null,       //6
        "INVERT",   //7
        null,       //8
        "STROKE",   //9
        ""         //10
    );

    public static String unicode= "\033";

    public static String getColor(List<String> attributes) {
        //System.out.println(attributes);
        String color = unicode+"[";

        if (attributes.size() <= 0) {
            return "";
        }

        int a = 1;
        for (String attribute: attributes) {
            if (attribute == null) continue;

            int num = -1;

            int colorIndex = colorNames.indexOf(attribute.toLowerCase().replace("dark", "").replace("back", ""));
            if (colorIndex >=0) {
                int colorNum = 90 + colorIndex;
                if (attribute.toLowerCase().contains("dark")) colorNum -= 60;
                if (attribute.toLowerCase().contains("back")) colorNum +=10;

                num = colorNum;
            }

            int modIndex = modifierNames.indexOf(attribute.toUpperCase());
            if (modIndex >=0) {
                num = modIndex;
            }

            if (num >=0) color+=num;
            if (a++<attributes.size()) color+=";";
        }

        if (!color.equals("")) color += "m";

        return color;
    }

    public static String getColor(String frontColor, String modifier, String backColor) {
        return getColor(Arrays.asList(frontColor, modifier, backColor));
    }

    public static String getColor(String color, String modifier) {
        return getColor(color, modifier, null);
    }

    public static String getColor(String color) {
        return getColor(color, null);
    }

    @SuppressWarnings("unchecked")
    public static String getString(String string) {
        ArrayList<Map<String, Object>> colors = new ArrayList<>();

        ArrayList<String> attributes = new ArrayList<>();
        int pos = 0;
        int start = -1;
        String attribute = "";

        for (char ch : string.toCharArray()) {
            switch (ch) {
                case '{':
                    start = pos;
                    break;
                case '}':
                    attributes.add(attribute);
                    attribute = "";

                    Map<String,Object> colorMap = new HashMap<>();
                    colorMap.put("start", (Integer)start);
                    colorMap.put("end", (Integer)pos+1);

                    colorMap.put("attributes", attributes);

                    colors.add(colorMap);

                    attributes = new ArrayList<>();
                    start = -1;
                    break;
                case ';':
                case ',':
                    attributes.add(attribute);
                    attribute = "";
                    break;
                default:
                    if (start >=0) attribute+=ch;
                    break;
            }

            pos++;
        }

        int offset = 0;
        for (Map<String, Object> colorMap : colors) {
            start = (int)colorMap.get("start")+offset;
            int end = (int)colorMap.get("end")+offset;

            attributes = (ArrayList<String>)colorMap.get("attributes");
            //1System.out.println(attributes);
            String color = getColor(attributes);

            string = string.substring(0,start)+color+string.substring(end);
            offset += color.length() - (end-start);
        }

        return string;
    }

    public static String reset() {
        return unicode+"[0m";
    }
}
