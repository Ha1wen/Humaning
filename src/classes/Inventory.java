package classes;

import java.util.ArrayList;

import tools.Rarities;
import tools.Screen;

public class Inventory {
    public static int DEFAULT_SIZE = 5;

    private final ArrayList<Human> humans;
    private int size;
    private Rod rod;

    public Inventory() {
        humans = new ArrayList<>();
        size = DEFAULT_SIZE;
        rod = new Rod(Rarities.getRarity("Common"));
    }

    public int addHuman(Human human) {
        if (humans.size() == size) {
            return -1;
        }

        humans.add(human);
        return size-humans.size();
    }

    public void removeHuman(int index) {
        humans.remove(index);
    }

    public boolean findHuman(String name) {
        for (Human human: humans) {
            if (human.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Human> getHumans() {
        return humans;
    }

    public Human getHuman(int index) {
        if (index >= humans.size()) {
            return null;
        }
        return humans.get(index);
    }

    public int getSize() {
        return size;
    }

    public int getAmount() {
        return humans.size();
    }

    public void changeSize(int newSize) {
        size = newSize;
    }

    public void incrementSize(int increment) {
        size += increment;
    }

    public void clear() {
        humans.clear();
        size = DEFAULT_SIZE;
    }

    public int getWorth() {
        int worth = 0;

        for (Human human : humans) worth+=human.getPrice();

        return worth;
    }

    public String getLongestName() {
        String longest = "";
        for (Human human: humans) {
            if (human.getName().length() > longest.length()) longest = human.getName();
        }

        return longest;
    }

    public String getLongestRarity() {
        String longest = "";
        for (Human human: humans) {
            if (human.getRarity().length() > longest.length()) longest = human.getRarity();
        }

        return longest;
    }

    public String toString(boolean prices) {
        String string = "";
        int nameSpace = getLongestName().length();
        int raritySpace = getLongestRarity().length();
        int c = 1;

        if (!prices) {
            string+="{ITALIC}Rod:{R,BOLD} "+rod.getColor()+rod.getRarity()+"{R}\n\n";

            if (getAmount() > 0) string+= "{ITALIC}Humans:{R}\n";
        }

        if (prices && getAmount() >1) {
            string+=" {ITALIC}0{R} All : {green}$"+getWorth()+"{R}\n\n";
        }
        for (Human human: humans) {
            String color = human.getColorName();
            String rarity = human.getRarity();
            String name = human.getName();
            String price = "$"+human.getPrice();

            string+=" {R,ITALIC;SOFT}"+c+" {R;BOLD;"+color+"}"+Screen.align(rarity, raritySpace)+" {R}: "+Screen.align(name, nameSpace);
            if (prices) string+="{R} : {green}"+price;

            if (c++ < humans.size()) {
                string += "\n";
            }
        }
        return string;
    }

    public String toString() {
        return toString(false);
    }

    public Rod getRod() {
        return rod;
    }
}
