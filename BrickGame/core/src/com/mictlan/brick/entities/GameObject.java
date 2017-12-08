package com.mictlan.brick.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by josel.garza on 22/11/2017.
 */
public abstract class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public void render(ShapeRenderer srenderer) {
        srenderer.setColor(color);
        srenderer.rect(x, y, width, height);

    }

    public abstract void update();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
