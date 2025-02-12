import java.util.ArrayList;

public class Inventory {
    public static int DEFAULT_SIZE = 5;

    private ArrayList<Human> humans;
    private int size;

    public Inventory() {
        humans = new ArrayList<Human>();
        size = DEFAULT_SIZE;
    }

    public boolean addHuman(Human human) {
        if (humans.size() == size) {
            return false;
        }

        humans.add(human);
        return true;
    }

    public void removeHuman(int index) {
        humans.remove(index);
    }

    public boolean findHuman(String name) {
        for (Human human: humans) {
            if (human.getName() == name) {
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

    public String toString() {
        String string = "";
        int c = 0;
        for (Human human: humans) {
            c++;

            String name = human.getName();
            String rarity = human.getRarity();

            string += c+" "+rarity+" : "+name;

            if (c < humans.size()) {
                string += "\n";
            }
        }
        return string;
    }

    public void wipe() {
        humans.clear();
        size = DEFAULT_SIZE;
    }
}
