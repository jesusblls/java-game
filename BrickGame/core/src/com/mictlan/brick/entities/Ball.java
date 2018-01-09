package com.mictlan.brick.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;
import com.mictlan.brick.screens.GameScreen;
import com.mictlan.brick.utils.ColorFactory;
import com.mictlan.brick.controllers.BrickController;
import com.mictlan.brick.controllers.ScoreController;

import java.util.ArrayList;
import java.util.Iterator;


public class Ball extends GameObject implements Subject {
    private ArrayList<Observer> observers;
    private int velX = 250;
    private int velY = 250;
    private Player player;
    private BrickController brickcontroller;
    private GameScreen gs;

    public Ball(int x, int y, int width, int height, Player player, BrickController brickcontroller, GameScreen gs) {
        super(x, y, width, height);
        observers = new ArrayList<Observer>();
        color = ColorFactory.getColor(244, 244, 244);
        this.player = player;
        this.brickcontroller = brickcontroller;
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

    public Brick getCollidingBrick() {
        Brick brick = null;
        Iterator<Brick> iter = brickcontroller.getBricks().iterator();
        while (iter.hasNext()) {
            brick = iter.next();
            if (this.hitbox.overlaps(brick.getHitbox())) {
                return brick;
            }
        }
        return null;
    }

    public void update() {
        moveOnX();
        // Brick collision on X
        Brick collidingBrick = getCollidingBrick();
        if(collidingBrick != null) {
            brickcontroller.remove(collidingBrick);
            velX *= -1;
            notifyObservers(collidingBrick);

        }
        // Player collision on x
        if (this.hitbox.overlaps(player.getHitbox())){
            double speedXY = Math.sqrt(velX*velX + velY*velY);
            double posX = (getCenterX() - player.getCenterX()) / (player.getWidth() / 2);
            velX = (int)(speedXY * posX * player.getFriction() * 1.2) ;
        }

        moveOnY();
        // Brick collision on Y
        collidingBrick = getCollidingBrick();
        if(collidingBrick != null) {
            brickcontroller.remove(collidingBrick);
            velY *= -1;
            notifyObservers(collidingBrick);

        }
        // Player collision on Y
        if (this.hitbox.overlaps(player.getHitbox())){
            double speedXY = Math.sqrt(velX*velX + velY*velY);
            velY = (int)(Math.sqrt(speedXY*speedXY - velX*velX) * (velY > 0 ? -1 : 1));
        }

        // window collition
        if (x < 0) {
            velX *= -1;
        }
        if (x + width > 800) {
            velX *= -1;
        }

        if (y > 480 - width) {
            velY *= -1;

        }
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(GameObject gameObject) {
        for(Observer observer : observers) {
            observer.update(gameObject);
        }
    }

}
