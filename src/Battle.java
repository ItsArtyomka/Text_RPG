/*
   Если игрок убит — игра заканчивается,
   если убит монстр — игрок получает золото и опыт.
   Бой нужно реализовать в отдельном потоке.
*/

@SuppressWarnings("ReassignedVariable")
public class Battle {
    void fight(Character player, Character monster, Game.FightCallback fightCallback) {
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
                    //Чтобы бой не проходил за секунду, сделаем имитацию работы, как если бы
                    //у нас была анимация
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // Starting fight thread.
        Thread thread = new Thread(runnable);
        thread.start();
    }

    boolean makeHit(Character attacker, Character defender, Game.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHp() - hit;

        if (hit != 0) {
            System.out.printf("%s did %d damage!%n", attacker.getName(), hit);
            System.out.printf("%s has %d health left...%n", defender.getName(), defenderHealth);
        } else {
            System.out.printf("%s missed!%n", attacker.getName());
        }
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
