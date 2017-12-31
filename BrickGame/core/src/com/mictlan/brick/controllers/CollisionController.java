package com.mictlan.brick.controllers;

import com.mictlan.brick.entities.Ball;
import com.mictlan.brick.entities.Brick;
import com.mictlan.brick.entities.GameObject;
import com.mictlan.brick.entities.Player;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;

import java.util.ArrayList;

public class CollisionController implements Observer {

    private Subject ball;
    private ArrayList<GameObject> entities;

    public CollisionController (Subject ball) {
        entities = new ArrayList<GameObject>();
        this.ball = ball;
        ball.register(this);

    }

    public void addEntity (GameObject entity) {
        entities.add(entity);
    }

    public void addEntities (ArrayList<GameObject> entities) {
        this.entities.addAll(entities);
    }

    @Override
    public void update (GameObject ball) {
        for (GameObject entity: entities) {
            if (ball.getHitbox().overlaps(entity.getHitbox())) {
                if (entity instanceof Player) {
                   handlePlayerCollision((Ball) ball, (Player) entity);
                }
                else if (entity instanceof Brick) {
                    handleBrickCollision((Ball) ball, (Brick) entity);
                }
            }
        }
    }

    private void handleBrickCollision(Ball ball, Brick brick) {
        ball.bounceOnBrick();
    }

    private void handlePlayerCollision(Ball ball, Player player) {
        ball.bounceOnPlayer(player);
    }
}
