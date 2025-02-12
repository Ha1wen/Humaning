package classes;

public class Rarity {
    private final String name;
    private final int chance;
    private final int price;

    public Rarity (String name, int chance, int price) {
        this.name = name;
        this.chance = chance;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }

    public int getChance() {
        return chance;
    }
}