package com.nomedojogo.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Centraliza o controle de entradas do jogador.
 * Mantém compatibilidade com métodos antigos (isLeftPressed, isRightPressed, etc).
 */
public class CommandCenter implements KeyListener {

    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean jumpPressed;
    private boolean attackPressed;

    public CommandCenter() {}

    // Métodos de checagem — compatíveis com o Player antigo
    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }

    public boolean isAttackPressed() {
        return attackPressed;
    }

    // Eventos de teclado
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;
            case KeyEvent.VK_W -> upPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_SPACE -> jumpPressed = true;
            case KeyEvent.VK_J -> attackPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_SPACE -> jumpPressed = false;
            case KeyEvent.VK_J -> attackPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // não utilizado
    }
}
