package classes;

public class Rod {
    private static int priceMultiplier = 10;


    private Rarity rarity;

    public Rod(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity.getName();
    }

    public String getColorName() {
        return rarity.getColorName();
    }

    public String getColor() {
        return rarity.getColor();
    }

    public int getPrice() {
        return rarity.getPrice() * priceMultiplier;
    }

    public int getMultiplier() {
        return rarity.getChance();
    }
}
