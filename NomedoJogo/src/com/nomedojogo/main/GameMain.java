package com.nomedojogo.main;

import com.nomedojogo.entities.Player;
import com.nomedojogo.utils.CommandCenter;
import com.nomedojogo.utils.EntityFactory;
import com.nomedojogo.utils.CameraSystem;
import com.nomedojogo.world.ProceduralVoxelEngine;
import com.nomedojogo.world.World;
import com.nomedojogo.world.Voxel;

import javax.swing.*;
import java.awt.*;

public class GameMain extends JPanel {
    private World world;
    private Player player;
    private CameraSystem camera;
    private final int BLOCK_SIZE = 32;
    private final int VIEW_WIDTH = 20;
    private final int VIEW_HEIGHT = 15;

    public GameMain() {
        ProceduralVoxelEngine engine = new ProceduralVoxelEngine();
        world = engine.generateWorld(100, 50);

        EntityFactory factory = new EntityFactory();
        player = factory.createPlayer(world);

        camera = new CameraSystem(world, VIEW_WIDTH, VIEW_HEIGHT);

        CommandCenter controls = new CommandCenter(player);
        setFocusable(true);
        addKeyListener(controls);

        Timer timer = new Timer(16, e -> {
            player.update();
            camera.update(player);
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < VIEW_WIDTH; x++) {
            for (int y = 0; y < VIEW_HEIGHT; y++) {
                int worldX = x + camera.getCamX();
                int worldY = y + camera.getCamY();
                Voxel voxel = world.getBlock(worldX, worldY);
                if (voxel != null) {
                    g.setColor(voxel.getColor());
                    g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
        g.setColor(Color.RED);
        g.fillRect((player.getX() - camera.getCamX()) * BLOCK_SIZE,
                   (player.getY() - camera.getCamY()) * BLOCK_SIZE,
                   BLOCK_SIZE, BLOCK_SIZE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TerrariaClone");
        GameMain game = new GameMain();
        frame.add(game);
        frame.setSize(20 * 32, 15 * 32);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
