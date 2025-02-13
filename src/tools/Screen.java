package tools;

import java.io.IOException;

public class Screen {
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
