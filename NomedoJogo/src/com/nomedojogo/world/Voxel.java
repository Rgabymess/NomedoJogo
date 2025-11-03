package com.nomedojogo.world;

import java.awt.Color;

public class Voxel {
    private String type;

    public Voxel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Color getColor() {
        return switch (type) {
            case "Bedrock" -> Color.DARK_GRAY;
            case "Dirt" -> new Color(139, 69, 19);
            case "Grass" -> Color.GREEN;
            case "Air" -> Color.CYAN;
            default -> Color.MAGENTA;
        };
    }
}
