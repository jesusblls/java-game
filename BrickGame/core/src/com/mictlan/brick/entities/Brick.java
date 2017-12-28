package com.mictlan.brick.entities;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;

public class Brick extends GameObject {
    private int x;
    private int y;
    private int width = 50;
    private int height = 30;
    private int points = 100;
    private boolean hasPowerUp = false;
    private Color color;
    private Rectangle hitbox;



    public Brick(int x, int y, boolean hasPowerUp) {
        this.x = x;
        this.y = y;
        this.hasPowerUp = hasPowerUp;
        hitbox = new Rectangle(x, y, width, height);
        color = ColorFactory.getColor(244, 244, 244);
    }

    @Override
    public void update() {
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public void setColor(Color color){
        this.color = color;
    }

    public int getPoints() {
        return points;
    }

    public boolean hasPowerUp() {
        return hasPowerUp;
    }
}
