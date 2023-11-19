package com.niklas.playing;

import java.util.Scanner;
import java.util.Random;

public class Game {

    private static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Välkommen till spelet! Ange ditt namn:");
        String playerName = scanner.nextLine();

        Player player = new Player(playerName, 10, 10, 10, 100, 0, 1, 2, 0);
        Monster[] monsters = Monster.generateMonsterList();

        while (true) {
            System.out.println("Välj handling:");
            System.out.println("1. Strida mot ett monster");
            System.out.println("2. Status");
            System.out.println("3. Avsluta spelet");
            int choice = scanner.nextInt();
            Monster currentMonster = monsters[random.nextInt(monsters.length)];

            switch (choice) {
                case 1:
                    System.out.println("Du möter en " + currentMonster.getType() + "!");
                    player.fight(player, currentMonster);
                    break;
                case 2:
                    player.getStatus();
                    break;
                case 3:
                    System.out.println("Spelet avslutas. Tack för att du spelade!");
                    System.exit(0);
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
            }

            player.setExperience(player.getExperience() + 20);
            player.levelUp();
        }
    }
}
