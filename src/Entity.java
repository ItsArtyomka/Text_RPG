@SuppressWarnings("FieldMayBeFinal")
public class Entity implements Fighter {
    // Name
    private final String name;
    // Stats
    private int hp;
    private int strength;
    private int agility;
    // Gold and XP
    private int xp;
    private int gold;

    // Constructor
    public Entity(String name, int hp, int strength, int agility, int xp, int gold) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.agility = agility;
        this.xp = xp;
        this.gold = gold;
    }

    // Method for attack
    @Override
    public int attack() {
        if (agility * 3 > getRandomValue()) {
            return strength;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    // Getters and Setters
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format("%s's health: %d", name, hp);
    }
}