package com.nomedojogo.utils;

import com.nomedojogo.entities.Player;
import com.nomedojogo.world.World;

public class CameraSystem {
    private int camX, camY;
    private int viewWidth, viewHeight;
    private World world;

    public CameraSystem(World world, int viewWidth, int viewHeight) {
        this.world = world;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
    }

    public void update(Player player) {
        camX = Math.max(0, Math.min(player.getX() - viewWidth / 2, world.getWidth() - viewWidth));
        camY = Math.max(0, Math.min(player.getY() - viewHeight / 2, world.getHeight() - viewHeight));
    }

    public int getCamX() { return camX; }
    public int getCamY() { return camY; }
}
