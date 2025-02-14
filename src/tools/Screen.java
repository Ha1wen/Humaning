package tools;

import java.io.IOException;

import classes.Player;

public class Screen {
    private static final String titleColor = "{INVERT;BOLD}";
    private static final String playerColor = "{backdarkblack}";
    private static final int barSize = 50;

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033[H\033[2J");
                new ProcessBuilder("/bin/sh","-c","stty sane -echo").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void print(String text) {
        System.out.printf(color(text)+"\n");
        //System.out.println();
    }

    public static void printBar(Player player, String title) {
        int marginSize = (barSize-title.length())/2;
        String space = space(marginSize);

        title = space+title+space;
        if (title.length()%2!=0) title+=" ";

        String name = player.getName();
        int length = title.length();
        int money = player.getMoney();

        String playerBar = playerColor+Screen.align(" "+name, length-(String.valueOf(money).length()+2)) + "{green}$"+money+" {R}";
        String titleBar = titleColor+title+"{R}\n";

        clear();
        print(playerBar);
        print(titleBar);

    }

    public static String color(String string) {
        return Colors.getString(string)+Colors.reset();
    }

    public static String align(String string, int length) {
        return string + space(length - string.length());
    }

    public static String space(int length) {
        return " ".repeat(length);
    }
}
