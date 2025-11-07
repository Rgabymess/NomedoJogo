package com.nomedojogo.world;

import java.util.Random;

public class ProceduralTileEngine {
    private final Random random = new Random();

    public void generate(World world) {
        for (int x = 0; x < world.getWidth(); x++) {
            int groundHeight = world.getHeight() / 2 + random.nextInt(3) - 1;
            for (int y = 0; y < world.getHeight(); y++) {
                if (y > groundHeight) {
                    world.setTile(x, y, new Tile(TileType.DIRT));
                } else if (y == groundHeight) {
                    world.setTile(x, y, new Tile(TileType.GRASS));
                } else {
                    world.setTile(x, y, new Tile(TileType.AIR));
                }
            }
            world.setTile(x, world.getHeight() - 1, new Tile(TileType.BEDROCK));
        }
    }
}
