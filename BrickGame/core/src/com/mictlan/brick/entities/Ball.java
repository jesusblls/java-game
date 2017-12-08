package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.utils.ColorFactory;


public class Ball {
    private int x;
    private int y;
    private int width = 32;
    private int height = 32;
    private int velX = 200;
    private int velY = 200;
    Color color;

    public Ball (int x, int y){
        this.x = x;
        this.y = y;
        color = ColorFactory.getColor(244, 244, 244);
    }

    public void render(ShapeRenderer srenderer){
        srenderer.setColor(color);
        srenderer.rect(x, y, width, height);
    }

    public void update (){
        x += velX * Gdx.graphics.getDeltaTime();
        y += velY * Gdx.graphics.getDeltaTime();

        if (x < 0) {
            velX *= -1;
        }
        if (x + width > 800) {
            velX *= -1;
        }

        if (y < 0){
            velY *= -1;
        }
        if (y > 480 - width){
            velY *= -1;

        }

    }
}
