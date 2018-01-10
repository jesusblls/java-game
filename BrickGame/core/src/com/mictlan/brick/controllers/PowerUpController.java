package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.*;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;
import java.util.ArrayList;
import java.util.Iterator;

public class PowerUpController implements Observer{
    private final int PU_WIDTH = 20;
    private final int PU_HEIGHT = 20;
    private ArrayList<PowerUp> powerUps;
    private PowerUp powerUp;
    Subject subject;
    private Player player;
    private ScoreController scoreController;

    public PowerUpController(Subject subject) {
        powerUps = new ArrayList<PowerUp>();
        this.player = player;
        this.scoreController = scoreController;
        this.subject = subject;
        subject.register(this);
    }

    public void update() {

        for (PowerUp powerUp : powerUps) {
            powerUp.update();
        }

    }

    public void render(ShapeRenderer srenderer) {
        for (PowerUp powerUp : powerUps) {
            powerUp.render(srenderer);
        }
    }

    public void addPowerUp(float x, float y) {
        PowerUp powerUp = new PowerUp(x, y, PU_WIDTH, PU_HEIGHT);
        powerUps.add(powerUp);
    }

    public boolean hasPowerUp(){
        return powerUps.size() > 0;
    }

    public void remove(PowerUp powerUp) {
        powerUps.remove(powerUp);
    }

    @Override
    public void update(GameObject entity) {
        if (entity instanceof Brick) {
            Brick brick = ((Brick) entity);
            if(brick.hasPowerUp()) {
                addPowerUp(brick.getX(), brick.getY());
            }
        }

    }

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }
}
