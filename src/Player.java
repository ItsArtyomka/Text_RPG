import java.util.ArrayList;
import java.util.List;

public class Player extends Character implements Fighter {
    List<Potion> potionsList = new ArrayList<>(3);

    public Player(String name, int hp, int strength, int agility, int xp, int gold) {
        super(name, hp, strength, agility, xp, gold);
    }

    void heal(Potion potion) {
        if (!potionsList.isEmpty()) {
            setHp(getHp() + potion.healthHealing());
        } else {
            System.out.println("No potions;");
        }
    }
}

class Potion {
    int healthHealing() {
        return 5;
    }
}