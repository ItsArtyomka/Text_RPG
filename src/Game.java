/*
 * При запуске игры пользователю нужно будет первым делом создать персонажа, которого назовет пользователь.
 * После этого у игрока должна появиться надпись Куда вы хотите пойти? И быть доступны следующие варианты:
 * 1. К торговцу
 * 2. В тёмный лес
 * 3. На выход
 *
 * В первом случае можно будет попасть к торговцу (которого вам нужно реализовать самостоятельно,
 * вместе с логикой взаимодействия с ним; если еще не реализовали — пусть возвращается надпись Торговец еще не вышел на работу).
 * Во втором случае можно будет запустить логику ведения боя. Монстр должен появляться рандомный (либо скелет, либо гоблин с вероятностью 50%).
 * По завершении торговли или завершении боя должна быть доступна опция Вернуться в город или Продолжить торговлю/бой.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private static BufferedReader br;
    private static Entity player = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        // User Input
        br = new BufferedReader(new InputStreamReader(System.in));
        // Battle System
        battle = new Battle();
        System.out.println("Enter Player Name: ");

        try {
            command(br.readLine());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Player(string, 100, 20, 20, 0, 0);
            System.out.printf("%s will save the world from these dangerous deadly dragons!%n", player.getName());

            // Print out the Menu
            printNavigation();
        }

        // Input system
        switch (string) {
            case "1" -> {
                System.out.println("Trader isn't here yet.");
                command(br.readLine());
            }
            case "2" -> commitFight();
            case "3" -> System.exit(1);
            case "Yes" -> command("2");
            case "No" -> {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("---MENU---");
        System.out.println("1. Item Shop");
        System.out.println("2. To Dark Forest");
        System.out.println("3. Exit the Game");
    }

    private static void commitFight() {
        battle.fight(player, createMonster(), new Game.FightCallback() {
            @Override
            public void fightWin() {
                System.out.printf("%s won! Now you have %d XP and %d Gold, " + "and %d HP left.%n", player.getName(), player.getXp(), player.getGold(), player.getHp());
                System.out.println("Wanna continue or go back to town? [Yes / No]");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
                System.out.println("Wish to continue? (Yes/No)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Entity createMonster() {
        int random = (int) (Math.random() * 10);

        if (random % 2 == 0) {
            return new Goblin("Goblin", 50, 10, 10, 100, 20);
        } else {
            return new Skeleton("Skeleton", 25, 20, 20, 100, 10);
        }
    }

    interface FightCallback {
        void fightWin();
        void fightLost();
    }
}
