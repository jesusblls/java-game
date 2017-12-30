package com.mictlan.brick.controllers;

import com.mictlan.brick.entities.Brick;
import com.mictlan.brick.entities.GameObject;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;

public class ScoreController  implements Observer{

    private int score = 0;
    private Subject ball;

    public ScoreController(Subject ball) {
        this.ball = ball;
        ball.register(this);
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        this.score = score + points;
    }

    @Override
    public void update(GameObject gameObject) {
        if (gameObject instanceof Brick) {
            addPoints(((Brick) gameObject).getPoints());
        }
    }
}
