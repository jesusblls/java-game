package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;


public class PowerUp extends GameObject {
    private int velY = 200;

    public PowerUp(float x, float y, float width, float height ) {
        super(x, y, width, height);
        color = ColorFactory.getColor(255, 215,	0);
        hitbox = new Rectangle(x, y, width, height);
    }

    @Override
    public void update(float delta) {
        y -= velY * delta;
        hitbox.y = y;
    }

}
