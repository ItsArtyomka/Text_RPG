import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Player extends Entity implements Fighter {
    public Player(String name, int hp, int strength, int agility, int xp, int gold) {
        super(name, hp, strength, agility, xp, gold);
    }

    // Lists for potions.
    static List<Potion> potionsList = new ArrayList<>(3);

    // Healing
    void heal(Potion potion) {
        if (!potionsList.isEmpty()) {
            setHp(getHp() + potion.healthHealing());
        } else {
            System.out.println("No potions;");
        }
    }
}

// Potion Class
class Potion {
    int healthHealing() {
        return 10;
    }
}