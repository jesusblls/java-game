package com.mictlan.brick.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;
import com.mictlan.brick.controllers.BrickController;
import com.mictlan.brick.controllers.ScoreController;

import java.util.Iterator;


public class Ball extends GameObject {
    private int x;
    private int y;
    private int width = 32;
    private int height = 32;
    private int velX = 200;
    private int velY = 200;
    private boolean isColliding = false;
    private Rectangle hitbox;
    private Player player;
    private BrickController brickcontroller;
    Color color;
    private ScoreController scorecontroller;

    public Ball(int x, int y, Player player, BrickController brickcontroller, ScoreController scorecontroller) {
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
        this.scorecontroller = scorecontroller;
    }

    public void moveOnX() {
        x += velX * Gdx.graphics.getDeltaTime();
        hitbox.x = x;
    }

    public void moveOnY() {
        y -= velY * Gdx.graphics.getDeltaTime();
        hitbox.y = y;
    }

    public void update() {

        moveOnX();
        // Brick Collision on X
        Iterator<Brick> iter = brickcontroller.getBricks().iterator();
        while(iter.hasNext() && !isColliding) {
            Brick brick = iter.next();
            if (this.hitbox.overlaps(brick.getHitbox())) {
                isColliding = true;
                iter.remove();
                velX *= -1;
            }
        }
        isColliding = false;

        moveOnY();
        // Brick Collision on Y
        iter = brickcontroller.getBricks().iterator();
        while(iter.hasNext() && !isColliding) {
            Brick brick = iter.next();
            if (this.hitbox.overlaps(brick.getHitbox())) {
                isColliding = true;
                iter.remove();
                velY *= -1;
            }
        }
        isColliding = false;

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

        // Player Collision
        if (player.getHitbox().overlaps(this.hitbox)) {
            System.out.println("COLITION");
            y = player.getY() + player.getHeight();
            velY *= -1;
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
