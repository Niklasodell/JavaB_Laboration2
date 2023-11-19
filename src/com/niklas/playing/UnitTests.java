package com.niklas.playing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    @Test
    public void testLevelUp() {
        Player player = new Player("TestPlayer", 10, 10, 10, 100, 90, 1, 2, 0);

        player.setExperience(100);
        player.levelUp();

        assertEquals(2, player.getLevel());
        assertEquals(12, player.getStrength());
        assertEquals(12, player.getIntelligence());
        assertEquals(12, player.getAgility());
        assertEquals(0, player.getExperience());
    }

    @Test
    public void testPlayerLoss() {
        Player player = new Player("TestPlayer", 10, 10, 10, 100, 0, 1, 2, 0);

        player.takeDamage(110);

        assertEquals(0, player.getHealth());
    }

    @Test
    public void testCalculateDamage() {
        Player player = new Player("TestPlayer", 10, 10, 10, 100, 0, 1, 2, 0);

        int damage = player.calculateDamage();
        assertTrue(damage >= 2 && damage <= 7);
    }
}

