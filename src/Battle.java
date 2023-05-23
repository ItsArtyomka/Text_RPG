/*
   Если игрок убит — игра заканчивается,
   если убит монстр — игрок получает золото и опыт.
   Бой нужно реализовать в отдельном потоке.
*/

@SuppressWarnings("ReassignedVariable")
public class Battle {
    void fight(Entity player, Entity monster, Game.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Turn: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, player, fightCallback);
                } else {
                    isFightEnded = makeHit(player, monster, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        };
        // Starting fight thread.
        Thread battleThread = new Thread(runnable);
        battleThread.start();
    }

    boolean makeHit(Entity attacker, Entity defender, Game.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHp() - hit;

        // Damage System
        if (hit != 0) {
            System.out.printf("%s did %d damage!%n", attacker.getName(), hit);
            System.out.printf("%s has %d health left...%n", defender.getName(), defenderHealth);
        } else {
            System.out.printf("%s missed!%n", attacker.getName());
        }

        // Victory / Defeat System
        if (defenderHealth <= 0 && defender instanceof Player) {
            System.out.println("Lost to the Zone...");

            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.printf("Enemy defeated! You got %d XP and %d Gold%n", defender.getXp(), defender.getGold());
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());

            fightCallback.fightWin();
            return true;
        } else {
            defender.setHp(defenderHealth);
            return false;
        }
    }
}
