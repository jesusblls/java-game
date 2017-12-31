package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;
import com.mictlan.brick.utils.ColorFactory;
import com.mictlan.brick.controllers.BrickController;

import java.util.ArrayList;
import java.util.Iterator;


public class Ball extends GameObject implements Subject {
    private ArrayList<Observer> observers;
    private int velX = 250;
    private int velY = 250;
    private Player player;
    private BrickController brickcontroller;

    public Ball(int x, int y, int width, int height, Player player, BrickController brickcontroller) {
        super(x, y, width, height);
        observers = new ArrayList<Observer>();
        color = ColorFactory.getColor(244, 244, 244);
        hitbox = new Rectangle(x, y, width, height);
        this.player = player;
        this.brickcontroller = brickcontroller;
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
            notifyObserver(collidingBrick);
        }
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
            notifyObserver(collidingBrick);
        }
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

    public float getCenterX() {
        return x + width / 2;
    }

    public float getCenterY() {
        return y + height / 2;
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(GameObject gameObject) {
        for(Observer observer : observers) {
            observer.update(gameObject);
        }
    }
}
