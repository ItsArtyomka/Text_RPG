public class Skeleton extends Character implements Fighter {

    public Skeleton(String name, int hp, int strength, int agility, int xp, int gold) {
        super(name, hp, strength, agility, xp, gold);
    }

    @Override
    public int attack() {
        return 0;
    }
}
