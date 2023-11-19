package com.niklas.playing;

import java.util.Scanner;
import java.util.Random;

public class Monster implements ICombat {
    private String type;
    private int health;
    private int strength;
    private int agility;
    private int goldReward;

    public Monster(String type, int health, int strength, int agility, int goldReward) {
        this.type = type;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.goldReward = goldReward;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public void setGoldReward(int goldReward) {
        this.goldReward = goldReward;
    }

    public void attack(Player player) {
        if (player.didDodge()) {
            System.out.println("DODGED");
        } else {
            int damage = calculateMonsterDamage();
            player.takeDamage(damage);
        }
    }
    private int calculateMonsterDamage() {
        return strength;
    }


    @Override
    public void fight(Player player, Monster monster) {
        if (tryToFlee(player.getAgility())) {
            System.out.println(player.getName() + " lyckades fly från " + monster.getType() + "!");
        } else if (monster.didDodge()) {
            System.out.println(monster.getType() + " dodged your attack!");
        } else {
            int monsterDamage = calculateMonsterDamage();

            player.setHealth(player.getHealth() - monsterDamage);
            System.out.println("You dealt " + monsterDamage + " damage to the " + monster.getType() + "!");

            if (player.getHealth() <= 0) {
                System.out.println("You were defeated by the " + monster.getType() + "!");
            } else {
                monster.attack(player);
            }
        }
    }

    public boolean tryToFlee(int agility) {
        return false;
    }

    protected boolean didDodge() {
        Random random = new Random();
        int dodgeChance = random.nextInt(100) + 1;

        return dodgeChance <= agility;
    }

    public static Monster[] generateMonsterList() {
        return new Monster[]{
                new Monster("Goblin", 30, 10, 20, 5),
                new Monster("Ork", 50, 15, 15, 10),
                new Monster("Drake", 100, 30, 10, 50)
        };
    }
}
