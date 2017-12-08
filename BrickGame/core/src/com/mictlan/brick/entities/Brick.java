package com.mictlan.brick.entities;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;

public class Brick extends GameObject {
    private int x = 0;
    private int y = 0;
    private int width = 50;
    private int height = 30;
    private Color  color;
    private Rectangle hitbox;

    public Brick (int x, int y) {
        this.x = x;
        this.y = y;
        hitbox = new Rectangle();
        color = ColorFactory.getColor(244, 244, 244);
    }

    @Override
    public void update (){

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
}
