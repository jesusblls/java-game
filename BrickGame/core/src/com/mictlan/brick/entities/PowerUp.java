package com.mictlan.brick.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;

public class PowerUp extends GameObject {
    private int x;
    private int y;
    private int width = 20;
    private int height = 20;
    private Color color;
    private Rectangle hitbox;

    public PowerUp (int x, int y) {
        this.x = x;
        this.y = y;
        hitbox = new Rectangle();
        hitbox.width = 20;
        hitbox.height = 20;
        hitbox.x = x;
        hitbox.y = y;
        color = ColorFactory.getColor(70,130,180);
    }

    public void update (){
        hitbox.x = x;
        hitbox.y = y;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
