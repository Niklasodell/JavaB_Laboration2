package com.niklas.playing;

import java.util.Scanner;
import java.util.Random;

public class Player implements ICombat{
    private String name;
    private int strength;
    private int intelligence;
    private int agility;
    private int health;
    private int experience;
    private int level;
    private int baseDamage;
    private int gold;

    public Player(String name, int strength, int intelligence, int agility, int health, int experience, int level, int baseDamage, int gold) {
        this.name = name;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.health = 100;
        this.experience = 0;
        this.level = 1;
        this.baseDamage = 2;
        this.gold = 0;
    }

    public String getName() {
        return name;
    }

    public int getAgility() {
        return agility;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void levelUp() {
        if (experience >= 100) {
            level++;
            strength += 2;
            intelligence += 2;
            agility += 2;
            experience = 0;
            System.out.println(name + " har gått upp i level " + level + "!");
        }
    }

    // Ändring i Player-klassen
    public void performAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Välj handling:");
        System.out.println("1. Attackera");
        System.out.println("2. Fly");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                attack();
                break;
            case 2:
                flee();
                break;
            default:
                System.out.println("Ogiltigt val. Försök igen.");
        }
    }


    public void flee() {
        Random random = new Random();
        int fleeChance = random.nextInt(100) + 1;

        if (fleeChance <= agility) {
            System.out.println(name + " lyckades fly!");
        } else {
            System.out.println(name + " misslyckades med att fly och förlorade hälsa.");
            health -= 10;
        }
    }

    public void attack() {
        // TODO - Implementera logik för att attackera monstret
    }

    public void getStatus() {
        System.out.println("Status för " + name + ":");
        System.out.println("Hälsa: " + health);
        System.out.println("Styrka: " + strength);
        System.out.println("Intelligens: " + intelligence);
        System.out.println("Skicklighet: " + agility);
        System.out.println("Erfarenhet: " + experience);
        System.out.println("Nivå: " + level);
        System.out.println("Bas skada: " + baseDamage);
        System.out.println("Guld: " + gold);
    }

    private int calculateDamage() {
        return baseDamage + (strength * 2 / 4 + 1);
    }

    protected boolean didDodge() {
        Random random = new Random();
        int dodgeChance = random.nextInt(100) + 1;

        return dodgeChance <= agility;
    }

    @Override
    public void fight(Player player, Monster monster) {
        int playerDamage = calculateDamage();

        if (monster.tryToFlee(player.getAgility())) {
            System.out.println(monster.getType() + " försökte fly, men du lyckades hålla kvar det!");
        } else if (monster.didDodge()) {
            System.out.println(monster.getType() + " dodged your attack!");
        } else {
            monster.setHealth(monster.getHealth() - playerDamage);
            System.out.println("You dealt " + playerDamage + " damage to the " + monster.getType() + "!");
        }

        if (monster.getHealth() <= 0) {
            System.out.println("You defeated the " + monster.getType() + "!");
        } else {
            monster.attack(player);
        }
    }

    @Override
    public boolean tryToFlee(int agility) {
        Random random = new Random();
        int fleeChance = random.nextInt(100) + 1;

        return fleeChance <= agility;
    }


    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("You took " + damage + " damage!");
        if (health <= 0) {
            System.out.println("Game over! You have been defeated.");
            System.exit(0);
        }
    }
}

