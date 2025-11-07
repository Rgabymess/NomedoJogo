package com.nomedojogo.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import com.nomedojogo.utils.CommandCenter;
import com.nomedojogo.world.World;

/**
 * Player com posição e velocidade precisas (double).
 * Compatível com o PhysicsEngine refinado.
 */
public class Player {

    private double preciseX, preciseY;
    private double velX, velY;

    private final int TILE_SIZE = 16;
    private final double width = 0.8;
    private final double height = 0.95;

    private boolean leftPressed, rightPressed, jumpPressed;

    public Player(double x, double y) {
        this.preciseX = x;
        this.preciseY = y;
    }

    // === Atualização de entrada ===
    public void updateInputs(CommandCenter commandCenter) {
        leftPressed = commandCenter.isLeftPressed();
        rightPressed = commandCenter.isRightPressed();
        jumpPressed = commandCenter.isJumpPressed();
    }

    public void update(World world) {
        // A física será chamada externamente (PhysicsEngine.update)
        // aqui podemos adicionar lógica extra se precisar
    }

    // === Renderização ===
    public void render(Graphics2D g, int camX, int camY) {
        int drawX = (int) ((preciseX * TILE_SIZE) - camX);
        int drawY = (int) ((preciseY * TILE_SIZE) - camY);
        int drawW = (int) (width * TILE_SIZE);
        int drawH = (int) (height * TILE_SIZE);

        g.setColor(Color.RED);
        g.fillRect(drawX, drawY, drawW, drawH);
    }

    // === Getters e Setters precisos ===
    public double getPreciseX() { return preciseX; }
    public double getPreciseY() { return preciseY; }
    public void setPreciseX(double preciseX) { this.preciseX = preciseX; }
    public void setPreciseY(double preciseY) { this.preciseY = preciseY; }

    public double getVelX() { return velX; }
    public double getVelY() { return velY; }
    public void setVelX(double velX) { this.velX = velX; }
    public void setVelY(double velY) { this.velY = velY; }

    // === Inputs ===
    public boolean isLeftPressed() { return leftPressed; }
    public boolean isRightPressed() { return rightPressed; }
    public boolean isJumpPressed() { return jumpPressed; }

    // === Utilidades ===
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
