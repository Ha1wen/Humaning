package game;

import classes.*;

import java.util.Arrays;
import java.util.List;
import tools.*;

public class Game {
    private final Player player;
    private final Inventory inventory;

    private static final List<String> menuOptions = Arrays.asList(
        "Humaning",
        "Inventory",
        "Sell"
    );

    public Game(String playerName) {
        player = new Player(playerName);
        inventory = player.getInventory();
    }

    public void play() {
        //Screen.print("{Blue,Bold,White}Balls");
        while(true) {
            menu();
        }
    }

    public void menu() {
        Screen.clear();
        Screen.print("{Yellow}What would you like to do?");

        int c = 1;
        for (String name : menuOptions) {
            Screen.print(c+++" : "+name);
        }

        int option = Input.num(3);
        Screen.clear();

        switch (option) {
            case 1 -> humaning();
            case 2 -> inventory();
            case 3 -> sell();
            default -> Screen.print("Invalid Option");
        }
        Screen.clear();
    }

    public void humaning() {
        Human human = Humans.getRandomHuman();

        String rarity = human.getRarity();
        String name = human.getName();
        int chance = human.getChance();

        Screen.print("You caught a "+rarity+" : "+name+"! A 1 in "+chance+" chance");

        boolean full = !inventory.addHuman(human);
        if (full) {
            Screen.print("Unfortunately, your inventory is full!");
        }
        Input.cnt();
    }

    public void inventory() {
        String inventoryString = inventory.toString();
        Screen.print("INVENTORY\n"+inventoryString);
        Input.cnt();
    }

    public void sell() {
        String inventoryString = inventory.toString();
        Screen.print("Which human would you like to sell? (type 0 to exit)\n"+inventoryString);

        int options = inventory.getAmount();
        int selection = Input.num(options)-1;

        if (selection == -1) {
            return;
        }

        Screen.clear();

        if (selection < 0 || selection >= inventory.getAmount()) {
            Screen.print("Invalid Selection");
            Input.cnt();
            return;
        }

        Human human = inventory.getHuman(selection);
        int price = human.getPrice();

        player.addMoney(price);
        inventory.removeHuman(selection);

        Screen.print("Sold! You earned $"+price);
        Input.cnt();
    }
}