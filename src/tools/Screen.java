package tools;

import java.io.IOException;

import classes.Player;

public class Screen {
    private static final String clearString = "\033[H\033[2J";
    private static final String titleColor = "{INVERT;BOLD}";
    private static final String playerColor = "{backdarkblack}";
    private static final int barSize = 50;

    public static void reset() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("/bin/sh","-c","stty sane -echo").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void clear() {
        reset();         
        System.out.print(clearString);
    }

    public static void print(Player player, String title, String content) {
        reset();
        print(getBar(player, title)+content);
    }

    public static void print(String text) {
        System.out.printf(color(text)+"\n");
        //System.out.println();
    }

    public static void printBar(Player player, String title) {
        reset();
        print(getBar(player, title));
    }

    public static String getBar(Player player, String title) {
        int marginSize = (barSize-title.length())/2;
        String space = space(marginSize);

        title = space+title+space;
        if (title.length()%2!=0) title+=" ";

        String name = player.getName();
        int length = title.length();
        int money = player.getMoney();

        String playerBar = playerColor+Screen.align(" "+name, length-(String.valueOf(money).length()+2)) + "{green}$"+money+" {R}";
        String titleBar = titleColor+title+"{R}\n";

        String bar = getClear() + playerBar + "\n" + titleBar;

        return bar;
    }

    public static String color(String string) {
        return Colors.getString(string)+Colors.reset();
    }

    public static String getClear() {
        return clearString;
    }

    public static String align(String string, int length) {
        return string + space(length - string.length());
    }

    public static String space(int length) {
        return " ".repeat(length);
    }
}
