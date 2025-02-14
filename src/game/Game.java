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
        Screen.printBar(player, "What would you like to do?");

        int c = 1;
        for (String name : menuOptions) {
            String effect = "{ITALIC}";
            String string = +c+++" : "+name;
            if (name.equals("Sell") && inventory.getAmount() <= 0) {
                effect = "{ITALIC;SOFT}";
                string += " (empty)";
            }
            Screen.print(effect + string + "\n");
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

        Screen.print("{BOLD;INVERT} YOU CAUGHT A HUMAN! {R}\n\n"+properties+"\n");

        int left = inventory.addHuman(human);  
        if (left < 0) {
            Screen.print("{ITALIC}Unfortunately, your inventory is {BOLD}full!");
        } else {
            Screen.print("{ITALIC}You have "+left+" inventory slots left!");
        }


        Input.cnt();
    }

    public void inventory() {
        Screen.printBar(player, "INVENTORY");
        Screen.print(inventory.toString());
        Input.cnt();
    }

    public void sell() {
        if (inventory.getAmount() <= 0) return;

        Screen.printBar(player, "SELL HUMANS");
        Screen.print(inventory.toString(true));

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
            money+= player.sellHuman(selection-1);
        }

        Screen.print("{BOLD} Sold! You earned {green}$"+money+"!");
        Input.cnt();
    }
}