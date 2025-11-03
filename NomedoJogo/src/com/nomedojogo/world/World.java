package com.nomedojogo.world;

public class World {
    private Voxel[][] blocks;

    public World(int width, int height) {
        blocks = new Voxel[width][height];
    }

    public void setBlock(int x, int y, Voxel voxel) {
        if (x >= 0 && x < blocks.length && y >= 0 && y < blocks[0].length)
            blocks[x][y] = voxel;
    }

    public Voxel getBlock(int x, int y) {
        if (x >= 0 && x < blocks.length && y >= 0 && y < blocks[0].length)
            return blocks[x][y];
        return null;
    }

    public int getWidth() {
        return blocks.length;
    }

    public int getHeight() {
        return blocks[0].length;
    }
}
