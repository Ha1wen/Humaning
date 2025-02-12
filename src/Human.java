public class Human {
    private String name;
    private String desc;
    private Rarity rarity;

    public Human(String name, String desc, Rarity rarity) {
        this.name = name;
        this.desc = desc;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Rarity getRarityClass() {
        return rarity;
    }

    public String getRarity() {
        return rarity.getName();
    }

    public int getPrice() {
        return rarity.getPrice();
    }

    public int getChance() {
        return rarity.getChance();
    }

}
