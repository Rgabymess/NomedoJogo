package com.nomedojogo.world;

import java.util.Random;

public class ProceduralVoxelEngine {

    private Random rand = new Random();

    public World generateWorld(int width, int height) {
        World world = new World(width, height);
        for (int x = 0; x < width; x++) {
            Voxel[] column = generateColumn(height);
            for (int y = 0; y < height; y++) {
                world.setBlock(x, y, column[y]);
            }
        }
        return world;
    }

    private Voxel[] generateColumn(int height) {
        Voxel[] column = new Voxel[height];
        for (int y = 0; y < height; y++) {
            if (y == height - 1) column[y] = new Voxel("Bedrock");
            else if (y >= height - 3) column[y] = new Voxel("Dirt");
            else if (y == height - 4 && rand.nextDouble() < 0.5) column[y] = new Voxel("Grass");
            else column[y] = new Voxel("Air");
        }
        return column;
    }
}
