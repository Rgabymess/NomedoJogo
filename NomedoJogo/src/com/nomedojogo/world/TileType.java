package com.nomedojogo.world;

import java.awt.Color;

public enum TileType {
    AIR(new Color(135, 206, 235), false),
    DIRT(new Color(139, 69, 19), true),
    GRASS(new Color(34, 139, 34), true),
    BEDROCK(Color.DARK_GRAY, true);

    private final Color color;
    private final boolean solid;

    TileType(Color color, boolean solid) {
        this.color = color;
        this.solid = solid;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSolid() {
        return solid;
    }
}
