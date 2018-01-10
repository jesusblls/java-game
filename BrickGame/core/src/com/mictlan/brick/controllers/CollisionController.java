package com.mictlan.brick.controllers;

import com.mictlan.brick.entities.*;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;

import java.util.ArrayList;
import java.util.Iterator;

public class CollisionController implements Observer, Subject {

    private Subject subject;
    private Ball ball;
    private Player player;
    private ArrayList<Brick> bricks;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Observer> observers;

    public CollisionController (Subject subject){
        observers = new ArrayList<Observer>();
        this.subject = subject;
        subject.register(this);
    }

    public void setPlayer (Player player) {
        this.player = player;
    }

    private void handlePlayerCollision(Ball ball, Player player) {
        ball.bounceOnPlayer(player);
    }

    private void handleBrickCollision(Ball ball, Brick brick) {
        ball.bounceOnBrick(brick);
    }

    @Override
    public void update(GameObject ball) {

        Iterator<Brick> IterB = bricks.iterator();
        Brick brick = null;
        while (IterB.hasNext()) {
            brick = IterB.next();
            if (ball.getHitbox().overlaps(brick.getHitbox())) {
                handleBrickCollision((Ball) ball, brick);
                IterB.remove();
                notifyObservers(brick);
                break;
            }
        }

        Iterator<PowerUp> iterPU = powerUps.iterator();
        PowerUp powerUp = null;
        while (iterPU.hasNext()) {
            powerUp = iterPU.next();
            powerUp.update();
            if (player.getHitbox().overlaps(powerUp.getHitbox())) {
                iterPU.remove();
                notifyObservers(powerUp);
            }

        }

        if (ball.getHitbox().overlaps(player.getHitbox())) {
            handlePlayerCollision((Ball) ball, player);
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
    public void notifyObservers(GameObject entity) {
        for (Observer observer : observers) {
            observer.update(entity);
        }
    }


    public void setPowerUps(ArrayList<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }
}
