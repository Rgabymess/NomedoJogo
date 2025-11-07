package com.nomedojogo.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.nomedojogo.entities.Player;
import com.nomedojogo.utils.*;
import com.nomedojogo.world.*;

public class GameMain extends JPanel implements Runnable {
    private final World world;
    private final Player player;
    private final CameraSystem camera;
    private final CommandCenter commands;
    private final BlockInteractionSystem blockSystem;
    private boolean running = true;
    private int mouseX, mouseY;

    public GameMain() {
        setPreferredSize(new Dimension(960, 540));
        setFocusable(true);

        world = new World(200, 60);
        player = EntityFactory.createPlayer(world);
        camera = new CameraSystem(world, 960, 540);
        commands = new CommandCenter();
        blockSystem = new BlockInteractionSystem(world, camera);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) { commands.handleKeyPress(e); }
            public void keyReleased(KeyEvent e) { commands.handleKeyRelease(e); }
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                blockSystem.handleMouseClick(e, SwingUtilities.isRightMouseButton(e));
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        new Thread(this).start();
    }

    public void run() {
        while (running) {
            player.update(commands.isLeft(), commands.isRight(), commands.isJump());
            camera.update(player);
            repaint();
            try { Thread.sleep(16); } catch (InterruptedException ignored) {}
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.render(g, camera.getX(), camera.getY(), getWidth(), getHeight());
        player.render(g, camera.getX(), camera.getY());
        blockSystem.renderOutline(g, mouseX, mouseY);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TileWorld");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GameMain());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
