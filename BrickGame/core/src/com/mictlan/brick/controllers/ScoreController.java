package com.mictlan.brick.controllers;

public class ScoreController {

    private int score = 0;

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        this.score = score + points;
    }

}
