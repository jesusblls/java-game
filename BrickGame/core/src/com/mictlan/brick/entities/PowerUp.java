package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;


public class PowerUp extends GameObject {
    private int x;
    private int y;
    private int width = 20;
    private int height = 20;
    private int velY = 200;
    private Rectangle hitbox;
    private Color color;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        color = ColorFactory.getColor(255, 215,	0);
        hitbox = new Rectangle(x, y, width, height);
    }



    @Override
    public void update() {

        y -= velY * Gdx.graphics.getDeltaTime();
        hitbox.y = y;

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
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }
}
