package com.mictlan.brick.controllers;

import com.mictlan.brick.entities.*;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;

import java.util.ArrayList;
import java.util.Iterator;

public class CollisionController implements Observer, Subject {

    private Subject subject;
    private Player player;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Observer> observers;

    public CollisionController (Subject subject) {
        observers = new ArrayList<Observer>();
        this.subject = subject;
        subject.register(this);
    }

    public void setBricks (ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public void setPlayer (Player player) {
        this.player = player;
    }

    private void handleBrickCollision(Ball ball, Brick brick) {
        ball.bounceOnBrick(brick);
    }

    private void handlePlayerCollision(Ball ball, Player player) {
        ball.bounceOnPlayer(player);
    }

    @Override
    public void update (GameObject ball) {
        // ball - brick collision
        Iterator<Brick> bIter = bricks.iterator();
        Brick brick = null;
        while (bIter.hasNext()) {
            brick = bIter.next();
            if (ball.getHitbox().overlaps(brick.getHitbox())) {
                handleBrickCollision((Ball) ball, brick);
                bIter.remove();
                notifyObserver(brick);
                break;
            }
        }
        // player - ball collision
        if (ball.getHitbox().overlaps(player.getHitbox())) {
            handlePlayerCollision((Ball) ball, player);
        }
        // player - pu collision
        Iterator<PowerUp> pIter = powerUps.iterator();
        PowerUp powerUp = null;
        while (pIter.hasNext()) {
            powerUp = pIter.next();
            if (player.getHitbox().overlaps(powerUp.getHitbox())) {
                pIter.remove();
                notifyObserver(powerUp);
            }
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
    public void notifyObserver(GameObject entity) {
        for (Observer observer : observers) {
            observer.update(entity);
        }
    }

    public void setPowerUps(ArrayList<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }
}
