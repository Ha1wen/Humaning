package classes;

public class Player {
    private final Inventory inventory;
    private int money;
    private String name;

    public Player(String playerName) {
        inventory = new Inventory();
        money = 0; 
        name = playerName;
    }

    public void changeName(String newName) {
        name = newName;
    }

    public void addMoney(int num) {
        money+=num;
    }

    public boolean spendMoney(int num) {
        if (num > money) {
            return false;
        }

        money -= num;
        return true;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void clear() {
        inventory.clear();
        money = 0;
    }
}
