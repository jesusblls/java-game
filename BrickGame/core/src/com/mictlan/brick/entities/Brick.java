package com.mictlan.brick.entities;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;

public class Brick extends GameObject {
    private int points = 100;
    private boolean hasPowerUp = false;

    public Brick(float x, float y, float width, float height, boolean hasPowerUp) {
        super(x, y, width, height);
        color = ColorFactory.getColor(244, 244, 244);
        this.hasPowerUp = hasPowerUp;
        hitbox = new Rectangle(x, y, width, height);
    }

    @Override
    public void update(float delta) {
        hitbox.x = x;
        hitbox.y = y;
    }

    public int getPoints() {
        return points;
    }

    public boolean hasPowerUp() {
        return hasPowerUp;
    }

    @Override
    public void setColor(Color color){
        this.color = color;
    }
}
