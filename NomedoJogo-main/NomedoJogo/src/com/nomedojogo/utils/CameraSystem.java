package com.nomedojogo.utils;

import com.nomedojogo.entities.Player;
import com.nomedojogo.world.World;

public class CameraSystem {
    private double camX, camY;
    private final int viewWidth, viewHeight;
    private final World world;
    private final double smoothFactor = 0.12;

    public CameraSystem(World world, int viewWidth, int viewHeight) {
        this.world = world;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
    }

    public void update(Player player) {
        double targetX = player.getPreciseX() - viewWidth / 2.0;
        double targetY = player.getPreciseY() - viewHeight / 2.0;
        camX += (targetX - camX) * smoothFactor;
        camY += (targetY - camY) * smoothFactor;
    }

    public int getX() { return (int)camX; }
    public int getY() { return (int)camY; }
}
