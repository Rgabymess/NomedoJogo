package com.nomedojogo.utils;

import com.nomedojogo.entities.Player;
import com.nomedojogo.world.World;

public class EntityFactory {

    public Player createPlayer(World world) {
        int spawnX = world.getWidth() / 2;
        int spawnY = world.getHeight() - 5; // spawn acima do chão
        return new Player(spawnX, spawnY, world);
    }
}
