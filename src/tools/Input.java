package tools;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static void cnt() {
        scanner.nextLine();
        scanner.nextLine();
    }

    public static int num(int max) {
        return scanner.nextInt();
    }
}
