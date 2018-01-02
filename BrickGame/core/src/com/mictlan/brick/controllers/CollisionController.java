package com.mictlan.brick.controllers;

import com.mictlan.brick.entities.Ball;
import com.mictlan.brick.entities.Brick;
import com.mictlan.brick.entities.GameObject;
import com.mictlan.brick.entities.Player;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;

import java.util.ArrayList;

public class CollisionController implements Observer, Subject {

    private Subject subject;
    private Player player;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private ArrayList<Observer> observers;

    public CollisionController (Subject subject) {
        observers = new ArrayList<Observer>();
        this.subject = subject;
        ball.register(this);
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
        for (Brick brick: bricks) {
            if (ball.getHitbox().overlaps(brick.getHitbox())) {
                    handleBrickCollision((Ball) ball, brick);
                    bricks.remove(brick);
                    notifyObserver(brick);
                    return;
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
}
