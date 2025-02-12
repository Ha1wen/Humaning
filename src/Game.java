import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;
    private Inventory inventory;
    private Scanner input;

    private static List<String> menuOptions = Arrays.asList(
        "Humaning",
        "Inventory",
        "Sell"
    );

    public Game(String playerName) {
        player = new Player(playerName);
        inventory = player.getInventory();
        input = new Scanner(System.in);
    }

    public void play() {
        while(true) {
            menu();
        }
    }

    public void menu() {
        System.out.println("What would you like to do?");

        int c = 1;
        for (String name : menuOptions) {
            System.out.println(c+++" : "+name);
        }

        int option = input.nextInt();
        clr();

        switch (option) {
            case 1:
                humaning();
                break;
            case 2:
                inventory();
                break;
            case 3:
                sell();
                break;
            default:
                System.out.println("Invalid Option");
        }
        cnt();
        clr();
    }

    public void clr() {
        System.out.println("-------------");
    }

    public void cnt() {
        input.nextLine();
    }

    public void humaning() {
        Human human = Humans.getRandomHuman();

        String rarity = human.getRarity();
        String name = human.getName();
        int chance = human.getChance();

        System.out.println("You caught a "+rarity+" : "+name+"! A 1 in "+chance+" chance");

        boolean full = !inventory.addHuman(human);
        if (full) {
            System.out.println("Unfortunately, your inventory is full!");
        }
    }

    public void inventory() {
        String inventoryString = inventory.toString();
        System.out.println("INVENTORY\n"+inventoryString);
    }

    public void sell() {
        String inventoryString = inventory.toString();
        System.out.println("Which human would you like to sell? (type 0 to exit)\n"+inventoryString);

        int selection = input.nextInt()-1;
        
        if (selection == -1) {
            System.out.println("Exiting inventory");
            return;
        }

        if (selection < 0 || selection >= inventory.getAmount()) {
            System.out.println("Invalid Selection");
            return;
        }

        Human human = inventory.getHuman(selection);
        int price = human.getPrice();

        player.addMoney(price);
        inventory.removeHuman(selection);

        System.out.println("Sold! You earned $"+price);
    }
}