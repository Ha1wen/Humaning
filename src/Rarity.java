public class Rarity {
    private String name;
    private int chance;
    private int price;

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