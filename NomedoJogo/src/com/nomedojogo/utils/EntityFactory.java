package com.nomedojogo.utils;

import com.nomedojogo.entities.Player;
import com.nomedojogo.world.World;

/**
 * Cria entidades do jogo (por enquanto só o Player, mas pode incluir mobs no futuro).
 */
public class EntityFactory {

    private EntityFactory() {}

    public static Player createPlayer(World world) {
        // Spawna o player no topo do mapa, logo acima do solo.
        int spawnX = world.getWidth() / 2;
        int spawnY = 0;

        // Procura o primeiro bloco sólido pra posicionar o jogador acima dele
        for (int y = 0; y < world.getHeight(); y++) {
            if (world.getBlock(spawnX, y) != null && world.getBlock(spawnX, y).getType().isSolid()) {
                spawnY = y - 2; // um pouco acima do chão
                break;
            }
        }

        // Player usa coordenadas precisas (double)
        return new Player(spawnX, spawnY);
    }
}
