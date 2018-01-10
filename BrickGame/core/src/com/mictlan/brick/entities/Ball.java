package com.mictlan.brick.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;
import com.mictlan.brick.utils.ColorFactory;
import java.util.ArrayList;


public class Ball extends GameObject implements Subject {
    private ArrayList<Observer> observers;
    private float velX = 250;
    private float velY = 250;
    private boolean movingOnX = false;



    public Ball(float x, float y, float width, float height) {
        super(x, y, width, height);
        observers = new ArrayList<Observer>();
        color = ColorFactory.getColor(244, 244, 244);
        hitbox = new Rectangle(x, y , width, height);

    }


    public void moveOnX() {
        x += velX * Gdx.graphics.getDeltaTime();
        hitbox.x = x;
        movingOnX = true;
        notifyObservers(this);
    }

    public void moveOnY() {
        y -= velY * Gdx.graphics.getDeltaTime();
        hitbox.y = y;
        movingOnX = false;
        notifyObservers(this);
    }


    public void update() {
        moveOnX();
        moveOnY();


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
        return x + width;
    }

    public float getCenterY() {
        return y + height;
    }

    public boolean isMovingOnX() {
        return movingOnX;
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
        if (movingOnX) {
            velX *= -1;
        }
        else {
            velY *= -1;
        }
    }

}
