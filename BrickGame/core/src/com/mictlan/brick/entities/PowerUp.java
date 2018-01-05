package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;


public class PowerUp extends GameObject {
    private int velY = 200;

    public PowerUp(int x, int y, int width, int height) {
        super(x, y, width, height);
        color = ColorFactory.getColor(255, 215,	0);
    }

    @Override
    public void update() {
        y -= velY * Gdx.graphics.getDeltaTime();
        hitbox.y = y;

    }
}
