package classes;

import java.util.ArrayList;

import tools.Screen;

public class Inventory {
    public static int DEFAULT_SIZE = 5;

    private final ArrayList<Human> humans;
    private int size;

    public Inventory() {
        humans = new ArrayList<>();
        size = DEFAULT_SIZE;
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

    public String getString(boolean prices) {
        String string = "";
        int nameSpace = getLongestName().length();
        int raritySpace = getLongestRarity().length();
        int c = 1;

        if (prices) {
            string+="{ITALIC}0{X} All : {green}$"+getWorth()+"{X}\n\n";
        }
        for (Human human: humans) {
            String color = human.getColorName();
            String rarity = human.getRarity();
            String name = human.getName();
            String price = "$"+human.getPrice();

            string+="{ITALIC}"+c+" {BOLD;"+color+"}"+Screen.align(rarity, raritySpace)+" {X}: "+Screen.align(name, nameSpace);
            if (prices) string+="{X} : {green}"+price;

            if (c++ < humans.size()) {
                string += "\n";
            }
        }
        return string;
    }

    public String toString() {
        return getString(false);
    }
}
