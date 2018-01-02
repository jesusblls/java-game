package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;
import com.mictlan.brick.utils.ColorFactory;

import java.util.ArrayList;


public class Ball extends GameObject implements Subject {
    private ArrayList<Observer> observers;
    private int velX = 150;
    private int velY = 150;
    private boolean movingOnX = false;

    public Ball(float x, float y, float width, float height) {
        super(x, y, width, height);
        observers = new ArrayList<Observer>();
        color = ColorFactory.getColor(244, 244, 244);
        hitbox = new Rectangle(x, y, width, height);
    }

    public void moveOnX(float delta) {
        x += velX * delta;
        hitbox.x = x;
        movingOnX = true;
        notifyObserver(this);
    }

    public void moveOnY(float delta) {
        y -= velY * delta;
        hitbox.y = y;
        movingOnX = false;
        notifyObserver(this);
    }

    public void update(float delta) {

        moveOnX(delta);
        moveOnY(delta);

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

    public boolean isMovingOnX() {
        return movingOnX;
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

    public void bounceOnPlayer(Player player) {
        if (movingOnX) {
            double speedXY = Math.sqrt(velX*velX + velY*velY);
            double posX = (getCenterX() - player.getCenterX()) / (player.getWidth() / 2);
            velX = (int)(speedXY * posX * player.getFriction() * 1.2) ;
        }
        else {
            double speedXY = Math.sqrt(velX*velX + velY*velY);
            velY = (int)(Math.sqrt(speedXY*speedXY - velX*velX) * (velY > 0 ? -1 : 1));
        }
    }

    public void bounceOnBrick(Brick brick) {
        // if needed we can get brick info to fix the bounce or implement new mechanics
        if (movingOnX) {
            velX *= -1;
        }
        else {
            velY *= -1;
        }
    }
}
