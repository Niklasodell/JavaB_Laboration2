package com.niklas.playing;

interface ICombat {

    void fight(Player player, Monster monster);

    boolean tryToFlee(int agility);

}
