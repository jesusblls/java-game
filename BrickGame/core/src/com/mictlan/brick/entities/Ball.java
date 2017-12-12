package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;
import com.mictlan.brick.entities.Player;
import com.mictlan.brick.controllers.BrickController;


public class Ball extends GameObject {
    private int x;
    private int y;
    private int width = 32;
    private int height = 32;
    private int velX = 200;
    private int velY = 200;
    private Rectangle hitbox;
    private Player player;
    private BrickController brickcontroller;
    Color color;

    public Ball(int x, int y, Player player, BrickController brickcontroller) {
        this.x = x;
        this.y = y;
        color = ColorFactory.getColor(244, 244, 244);
        hitbox = new Rectangle();
        hitbox.width = 32;
        hitbox.height = 32;
        hitbox.x = x;
        hitbox.y = y;
        this.player = player;
        this.brickcontroller = brickcontroller;

    }


    public void update() {


        x += velX * Gdx.graphics.getDeltaTime();
        y -= velY * Gdx.graphics.getDeltaTime();

        if (x < 0) {
            velX *= -1;
        }
        if (x + width > 800) {
            velX *= -1;
        }

        if (y < 0) {
            velY *= -1;
        }
        if (y > 480 - width) {
            velY *= -1;

        }


        if (player.getHitbox().overlaps(this.hitbox)) {
            y = player.getY() + player.getHeight();
            velY *= -1;
        }

        hitbox.x = x;
        hitbox.y = y;

        for (Brick brick : brickcontroller.getBricks()){
            if (brick.getHitbox().overlaps(this.hitbox)){
                System.out.println(brick.getHitbox().x);
            }

        }

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

}
