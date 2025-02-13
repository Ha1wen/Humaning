package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Colors {
    private static final List<String> colorNames= Arrays.asList(
        "Black",
        "Red",
        "Green",
        "Yellow",
        "Blue",
        "Purple",
        "Cyan",
        "White"
    );

    private static final List<String> modifierNames= Arrays.asList(
        "",
        "Bold",
        "Soft",
        "Italic",
        "Line",
        "Blink"
    );

    public static final String unicode= "\033";

    public static String getColor(String frontColor, String modifier, String backColor) {
        int frontNum = 30;
        int backNum = 100;
        int modifierNum = modifierNames.indexOf(modifier);

        if (frontColor.contains("Dark")) {
            frontNum += 30;
            frontColor = frontColor.substring(4);
        }
        if (backColor.contains("Dark")) {
            backNum -= 60;
            backColor = backColor.substring(4);
        }

        int frontIndex = colorNames.indexOf(frontColor);
        int backIndex = colorNames.indexOf(backColor);

        frontNum+=frontIndex;
        backNum+=backIndex;
        
        String color = unicode+"["+modifierNum+";"+frontNum+";"+backNum+"m";

        return color;
    }

    public static String getColor(String color, String modifier) {
        return getColor(color, modifier, "");
    }

    public static String getColor(String color) {
        return getColor(color, "");
    }

    public static String getString(String string) {
        ArrayList<Map<String, Object>> colors = new ArrayList<>();

        int pos = 0;
        int start = -1;
        int attribute = -1;
        String frontColor = "";
        String backColor = "";
        String modifier = "";

        for (char ch : string.toCharArray()) {
            switch (ch) {
                case '{':
                    start = pos;
                    attribute = 1;
                    break;
                case '}':
                    Map<String,Object> colorMap = new HashMap<>();
                    colorMap.put("start", (Integer)start);
                    colorMap.put("end", (Integer)pos+1);

                    colorMap.put("front-color", frontColor);
                    colorMap.put("back-color", backColor);
                    colorMap.put("modifier", modifier);

                    colors.add(colorMap);

                    attribute = -1;
                    frontColor = "";
                    backColor = "";
                    modifier = "";
                    break;
                case ',':
                    if (attribute > 0) {
                        attribute++;
                    }
                    break;
                default:
                    switch (attribute) {
                        case 1:
                            frontColor+=ch;
                            break;
                        case 2:
                            modifier+=ch;
                            break;
                        case 3:
                            backColor+=ch;
                            break;
                }
            }

            pos++;
        }

        int offset = 0;
        for (Map<String, Object> colorMap : colors) {
            start = (int)colorMap.get("start")+offset;
            int end = (int)colorMap.get("end")+offset;

            frontColor = (String)colorMap.get("front-color");
            backColor = (String)colorMap.get("back-color");
            modifier = (String)colorMap.get("modifier");
            
            String color = getColor(frontColor, modifier, backColor);

            string = string.substring(0,start)+color+string.substring(end);
            offset += color.length() - (end-start);
        }

        return string;
    }

    public static String reset() {
        return unicode+"[0m";
    }
}
