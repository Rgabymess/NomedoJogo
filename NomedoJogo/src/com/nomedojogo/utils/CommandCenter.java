package com.nomedojogo.utils;

import com.nomedojogo.entities.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CommandCenter extends KeyAdapter {
    private Player player;

    public CommandCenter(Player player) { 
        this.player = player; 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> player.setLeftPressed(true);
            case KeyEvent.VK_D -> player.setRightPressed(true);
            case KeyEvent.VK_W -> player.setJumpPressed(true);
            case KeyEvent.VK_E -> player.breakBlock(Player.Direction.FRONT);
            case KeyEvent.VK_R -> player.breakBlock(Player.Direction.ABOVE);
            case KeyEvent.VK_F -> player.breakBlock(Player.Direction.BELOW);
            case KeyEvent.VK_Q -> player.placeBlock(Player.Direction.FRONT);
            case KeyEvent.VK_T -> player.placeBlock(Player.Direction.ABOVE);
            case KeyEvent.VK_G -> player.placeBlock(Player.Direction.BELOW);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> player.setLeftPressed(false);
            case KeyEvent.VK_D -> player.setRightPressed(false);
            case KeyEvent.VK_W -> player.setJumpPressed(false);
        }
    }
}
