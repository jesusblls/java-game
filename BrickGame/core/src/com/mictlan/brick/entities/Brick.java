package com.mictlan.brick.entities;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;

/**
 * Created by josel.garza on 22/11/2017.
 */
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



    public void update (){

    }
}
