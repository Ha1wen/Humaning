package tools;

import java.io.IOException;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static void cnt() {
        scanner.nextLine();
    }

    public static int num(int max) {
        int num = 0;
        try {
            new ProcessBuilder("sh", "-c", "stty raw -echo").inheritIO().start().waitFor();

            while (num < 1 || num > max) {
                int ascii = System.in.read();
                char ch = (char)ascii;
                
                num = Character.getNumericValue(ch);
            }

            new ProcessBuilder("sh", "-c", "stty sane -echo").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return num;
    }
}
