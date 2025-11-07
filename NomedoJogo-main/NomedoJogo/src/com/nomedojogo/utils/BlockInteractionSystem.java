package com.nomedojogo.utils;

import java.awt.*;
import java.awt.event.MouseEvent;

import com.nomedojogo.world.*;

public class BlockInteractionSystem {
    private final World world;
    private final CameraSystem camera;
    private final int reach = 5 * World.TILE_SIZE;

    public BlockInteractionSystem(World world, CameraSystem camera) {
        this.world = world;
        this.camera = camera;
    }

    public void handleMouseClick(MouseEvent e, boolean rightClick) {
        int mx = e.getX() + camera.getX();
        int my = e.getY() + camera.getY();
        int tileX = mx / World.TILE_SIZE;
        int tileY = my / World.TILE_SIZE;

        if (withinReach(mx, my)) {
            if (rightClick) {
                world.setTile(tileX, tileY, new Tile(TileType.DIRT));
            } else {
                world.setTile(tileX, tileY, new Tile(TileType.AIR));
            }
        }
    }

    private boolean withinReach(int mx, int my) {
        int px = (int)(camera.getX() + reach / 2);
        int py = (int)(camera.getY() + reach / 2);
        double dx = mx - px, dy = my - py;
        return Math.sqrt(dx * dx + dy * dy) <= reach;
    }

    public void renderOutline(Graphics g, int mouseX, int mouseY) {
        int tileX = (mouseX + camera.getX()) / World.TILE_SIZE;
        int tileY = (mouseY + camera.getY()) / World.TILE_SIZE;

        int screenX = tileX * World.TILE_SIZE - camera.getX();
        int screenY = tileY * World.TILE_SIZE - camera.getY();

        boolean canReach = withinReach(mouseX + camera.getX(), mouseY + camera.getY());
        g.setColor(canReach ? Color.WHITE : Color.RED);
        g.drawRect(screenX, screenY, World.TILE_SIZE - 1, World.TILE_SIZE - 1);
    }
}
