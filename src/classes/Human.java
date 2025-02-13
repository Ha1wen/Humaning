package classes;

public class Human {
    private final String name;
    private final String desc;
    private final Rarity rarity;

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

    public String getColor() {
        return "{"+rarity.getColor()+"}";
    }

    public String getColorName() {
        return rarity.getColor();
    }

    public String getProperties() {
        return "{ITALIC}Rariy  {X}: "+getColor()+getRarity()+"{X}\nName   : "+getColor()+name+"{X}\nChance : 1 in "+getChance()+"\nPrice  : {darkgreen}$"+getPrice()+"{X}";
    }

    public String toString() {
        return "{BOLD}"+getColor()+getRarity()+" : {X}"+getColor()+name+"{X}";
    }
}
