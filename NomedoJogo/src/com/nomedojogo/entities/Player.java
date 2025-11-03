package com.nomedojogo.entities;

import com.nomedojogo.world.World;
import com.nomedojogo.world.Voxel;

public class Player {
    private double x, y;
    private double dx = 0, dy = 0;
    private double speed = 0.1;
    private double gravity = 0.1;
    private double terminalVelocity = 3.0;
    private double jumpStrength = 2.0;
    private int maxJumpTicks = 15;
    private int jumpTicks = 0;
    private boolean isJumping = false;

    private boolean leftPressed, rightPressed, jumpPressed;

    private World world;

    public enum Direction { FRONT, ABOVE, BELOW }

    public Player(int x, int y, World world) {
        this.x = x;
        this.y = y;
        this.world = world;
    }

    public void update() {
        // Horizontal movement
        dx = 0;
        if (leftPressed) dx = -speed;
        if (rightPressed) dx = speed;
        if (!collidesAt(x + dx, y)) x += dx;

        // Vertical movement
        if (onGround()) {
            dy = 0;
            jumpTicks = 0;
            if (jumpPressed) {
                isJumping = true;
            }
        }

        if (isJumping) {
            if (jumpTicks < maxJumpTicks && !collidesAt(x, y - jumpStrength)) {
                dy = -jumpStrength;
                jumpTicks++;
            } else {
                isJumping = false;
            }
        }

        dy += gravity;
        if (dy > terminalVelocity) dy = terminalVelocity;

        if (!collidesAt(x, y + dy)) y += dy;
        else dy = 0;
    }

    private boolean onGround() {
        return collidesAt(x, y + 1);
    }

    private boolean collidesAt(double newX, double newY) {
        int blockX = (int) Math.round(newX);
        int blockY = (int) Math.round(newY);

        Voxel voxel = world.getBlock(blockX, blockY);
        return voxel != null && !voxel.getType().equals("Air");
    }

    public void breakBlock(Direction dir) {
        int targetX = (int) Math.round(x);
        int targetY = (int) Math.round(y);

        switch (dir) {
            case FRONT -> targetY = (int) Math.round(y);
            case ABOVE -> targetY = (int) Math.round(y - 1);
            case BELOW -> targetY = (int) Math.round(y + 1);
        }

        if (world.getBlock(targetX, targetY) != null)
            world.setBlock(targetX, targetY, new Voxel("Air"));
    }

    public void placeBlock(Direction dir) {
        int targetX = (int) Math.round(x);
        int targetY = (int) Math.round(y);

        switch (dir) {
            case FRONT -> targetY = (int) Math.round(y);
            case ABOVE -> targetY = (int) Math.round(y - 1);
            case BELOW -> targetY = (int) Math.round(y + 1);
        }

        if (world.getBlock(targetX, targetY) != null)
            world.setBlock(targetX, targetY, new Voxel("Dirt"));
    }

    public int getX() { return (int) Math.round(x); }
    public int getY() { return (int) Math.round(y); }

    public void setLeftPressed(boolean pressed) { leftPressed = pressed; }
    public void setRightPressed(boolean pressed) { rightPressed = pressed; }
    public void setJumpPressed(boolean pressed) { jumpPressed = pressed; }
}
