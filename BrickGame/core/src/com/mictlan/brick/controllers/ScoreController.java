package com.mictlan.brick.controllers;

import com.mictlan.brick.entities.Brick;
import com.mictlan.brick.entities.GameObject;
import com.mictlan.brick.entities.PowerUp;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;

public class ScoreController  implements Observer{

    private int score = 0;
    private Subject collisionController;

    public ScoreController(Subject collisionController) {
        this.collisionController = collisionController;
        collisionController.register(this);
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        this.score = score + points;
    }

    @Override
    public void update(GameObject entity) {
        if (entity instanceof Brick) {
            addPoints(((Brick) entity).getPoints());
        }
        if (entity instanceof PowerUp) {
            addPoints(200);
        }
    }
}
