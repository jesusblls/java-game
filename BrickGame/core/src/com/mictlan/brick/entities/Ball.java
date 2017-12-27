package com.mictlan.brick.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.screens.GameScreen;
import com.mictlan.brick.utils.ColorFactory;
import com.mictlan.brick.controllers.BrickController;
import com.mictlan.brick.controllers.ScoreController;

import java.util.Iterator;


public class Ball extends GameObject {
    private int x;
    private int y;
    private int width = 32;
    private int height = 32;
    private int velX = 250;
    private int velY = 250;
    private boolean isColliding = false;
    private Rectangle hitbox;
    private Player player;
    private BrickController brickcontroller;
    Color color;
    private ScoreController scorecontroller;
    private GameScreen gs;

    public Ball(int x, int y, Player player, BrickController brickcontroller, ScoreController scorecontroller, GameScreen gs) {
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
        this.gs = gs;
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
                if(brick.hasPowerUp())
                gs.spawnPowerUp(brick);
            }
        }
        if (this.hitbox.overlaps(player.getHitbox())){
            isColliding = true;
            double speedXY = Math.sqrt(velX*velX + velY*velY);
            double posX = (getCenterX() - player.getCenterX()) / (player.getWidth() / 2);
            velX = (int)(speedXY * posX * player.getFriction() * 1.2) ;
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
                System.out.println(brick.hasPowerUp());
                scorecontroller.addPoints(brick.getPoints());
            }
        }
        if (this.hitbox.overlaps(player.getHitbox())){
            isColliding = true;
            double speedXY = Math.sqrt(velX*velX + velY*velY);
            velY = (int)(Math.sqrt(speedXY*speedXY - velX*velX) * (velY > 0 ? -1 : 1));
        }
        isColliding = false;

        // window collition
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


        // Player Collisio
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

    public float getCenterX() {
        return x + width / 2;
    }

    public float getCenterY() {
        return y + height / 2;
    }

}
