package com.mictlan.brick.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle hitbox;
    private Color color;

    public void render(ShapeRenderer srenderer, GameObject gobject) {
        srenderer.setColor(gobject.getColor());
        srenderer.rect(gobject.getX(), gobject.getY(), gobject.getWidth(), gobject.getHeight());
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setRectangle(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
