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
        Screen.print("{INVERT;BOLD} What would you like to do? \n");

        int c = 1;
        for (String name : menuOptions) {
            String effect = "{ITALIC}";
            String string = +c+++" : "+name;
            if (name.equals("Sell") && inventory.getAmount() <= 0) {
                effect = "{ITALIC;SOFT}";
                string += " (empty)";
            }
            Screen.print(effect + string);
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

        // String rarity = human.getRarity();
        // String name = human.getName();
        // String color = human.getColor();
        //int chance = human.getChance();
        String properties = human.getProperties();

        Screen.print("{BOLD;INVERT} YOU CAUGHT A HUMAN! {X}\n\n"+properties+"\n");

        int left = inventory.addHuman(human);  
        if (left < 0) {
            Screen.print("{ITALIC}Unfortunately, your inventory is {BOLD}full!");
        } else {
            Screen.print("{ITALIC}You have "+left+" inventory slots left!");
        }


        Input.cnt();
    }

    public void inventory() {
        Screen.print("{BOLD;INVERT} INVENTORY {X}\n\n"+inventory);
        Input.cnt();
    }

    public void sell() {
        if (inventory.getAmount() <= 0) return;
        String inventoryString = inventory.getString(true);
        Screen.print("{BOLD;INVERT} Which human would you like to sell? (press enter to exit )\n\n"+inventoryString);

        int options = inventory.getAmount();
        int selection = Input.num(0, options, true);

        if (selection == -1) {
            return;
        }

        Screen.clear();
        int money = 0;

        if (selection == 0) {
            money+= player.sellHumans();
        } else {
            money+= player.sellHuman(selection);
        }

        Screen.print("{BOLD} Sold! You earned {green}$"+money+"!");
        Input.cnt();
    }
}