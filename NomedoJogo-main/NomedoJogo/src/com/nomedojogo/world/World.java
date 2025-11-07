package com.nomedojogo.world;

import java.awt.Graphics;

public class World {
    private final int width, height;
    private final Tile[][] tiles;
    public static final int TILE_SIZE = 32;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        ProceduralTileEngine generator = new ProceduralTileEngine();
        generator.generate(this);
    }

    public void render(Graphics g, int camX, int camY, int screenW, int screenH) {
        int startX = Math.max(0, camX / TILE_SIZE);
        int endX = Math.min(width, (camX + screenW) / TILE_SIZE + 1);
        int startY = Math.max(0, camY / TILE_SIZE);
        int endY = Math.min(height, (camY + screenH) / TILE_SIZE + 1);

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Tile t = tiles[x][y];
                g.setColor(t.getType().getColor());
                g.fillRect(x * TILE_SIZE - camX, y * TILE_SIZE - camY, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public boolean isSolid(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return true;
        return tiles[x][y].getType().isSolid();
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return new Tile(TileType.AIR);
        return tiles[x][y];
    }

    public void setTile(int x, int y, Tile tile) {
        if (x < 0 || y < 0 || x >= width || y >= height) return;
        tiles[x][y] = tile;
    }

    /** 
     * Alias compatível com códigos antigos que usam getBlock().
     * Retorna o mesmo que getTile(), apenas com outro nome.
     */
    public Tile getBlock(int x, int y) {
        return getTile(x, y);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
