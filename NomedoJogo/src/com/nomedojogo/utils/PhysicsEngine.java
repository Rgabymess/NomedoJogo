package com.nomedojogo.utils;

import com.nomedojogo.entities.Player;
import com.nomedojogo.world.Tile;
import com.nomedojogo.world.World;

public class PhysicsEngine {

    private final double MOVE_ACCEL = 0.08;
    private final double MAX_MOVE_SPEED = 0.22;
    private final double FRICTION = 0.82;
    private final double GRAVITY = 0.10;
    private final double TERMINAL_VEL = 1.4;
    private final double JUMP_INIT_VEL = -0.95;
    private final int MAX_JUMP_TICKS = 7;

    // Player size (tiles)
    private final double WIDTH = 0.8;
    private final double HEIGHT = 0.95;

    private int jumpTicks = 0;
    private boolean jumpHoldActive = false;

    public void update(Player player, World world) {
        double px = player.getPreciseX();
        double py = player.getPreciseY();
        double vx = player.getVelX();
        double vy = player.getVelY();

        boolean left = player.isLeftPressed();
        boolean right = player.isRightPressed();
        boolean jump = player.isJumpPressed();

        boolean onGround = isOnGround(px, py, world);

        // --- Movimentação lateral ---
        if (left == right) {
            vx *= FRICTION;
            if (Math.abs(vx) < 0.001) vx = 0;
        } else if (left) {
            vx -= MOVE_ACCEL;
            if (vx < -MAX_MOVE_SPEED) vx = -MAX_MOVE_SPEED;
        } else if (right) {
            vx += MOVE_ACCEL;
            if (vx > MAX_MOVE_SPEED) vx = MAX_MOVE_SPEED;
        }

        // --- Pulo ---
        if (jump && onGround && !jumpHoldActive) {
            vy = JUMP_INIT_VEL;
            jumpTicks = 0;
            jumpHoldActive = true;
            onGround = false;
        }
        if (jumpHoldActive && jump && jumpTicks < MAX_JUMP_TICKS && vy < 0) {
            jumpTicks++;
        } else {
            jumpHoldActive = false;
        }

        // --- Aplicar gravidade ---
        if (!onGround) {
            vy += GRAVITY;
            if (vy > TERMINAL_VEL) vy = TERMINAL_VEL;
        } else if (vy > 0) {
            vy = 0;
        }

        // --- Movimento eixo X com correção incremental ---
        double nextX = px + vx;
        if (!collidesAt(nextX, py, world)) {
            px = nextX;
        } else {
            px = moveUntilCollision(px, py, vx, 0, world);
            vx = 0;
        }

        // --- Movimento eixo Y com correção incremental ---
        double nextY = py + vy;
        if (!collidesAt(px, nextY, world)) {
            py = nextY;
        } else {
            py = moveUntilCollision(px, py, 0, vy, world);
            vy = 0;
        }

        // Atualiza estado
        player.setPreciseX(px);
        player.setPreciseY(py);
        player.setVelX(vx);
        player.setVelY(vy);
    }

    /** Move o player passo a passo até tocar em um bloco */
    private double moveUntilCollision(double px, double py, double vx, double vy, World world) {
        double step = 0.05;
        double distance = Math.max(Math.abs(vx), Math.abs(vy));
        int steps = (int) Math.ceil(distance / step);

        double dx = vx == 0 ? 0 : step * Math.signum(vx);
        double dy = vy == 0 ? 0 : step * Math.signum(vy);

        for (int i = 0; i < steps; i++) {
            double testX = px + dx;
            double testY = py + dy;
            if (collidesAt(testX, testY, world)) break;
            px = testX;
            py = testY;
        }
        return vy != 0 ? py : px;
    }

    private boolean collidesAt(double px, double py, World world) {
        double left = px;
        double right = px + WIDTH;
        double top = py;
        double bottom = py + HEIGHT;

        int minX = (int) Math.floor(left);
        int maxX = (int) Math.floor(right);
        int minY = (int) Math.floor(top);
        int maxY = (int) Math.floor(bottom);

        for (int tx = minX; tx <= maxX; tx++) {
            for (int ty = minY; ty <= maxY; ty++) {
                Tile t = world.getTile(tx, ty);
                if (t != null && !t.getType().equals("Air")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOnGround(double px, double py, World world) {
        double feetY = py + HEIGHT + 0.05;
        int left = (int) Math.floor(px);
        int right = (int) Math.floor(px + WIDTH);

        int ty = (int) Math.floor(feetY);
        for (int tx = left; tx <= right; tx++) {
            Tile t = world.getTile(tx, ty);
            if (t != null && !t.getType().equals("Air")) {
                return true;
            }
        }
        return false;
    }
}
