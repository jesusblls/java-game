package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.Brick;
import com.mictlan.brick.entities.GameObject;
import com.mictlan.brick.entities.Player;
import com.mictlan.brick.entities.PowerUp;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;

import java.util.ArrayList;
import java.util.Iterator;

public class PowerUpController implements Observer {

    private final float POWERUP_WIDTH = 20;
    private final float POWERUP_HEIGHT = 20;
    private ArrayList<PowerUp> powerUps;
    private PowerUp powerUp;
    private Subject subject;

    public PowerUpController(Subject subject) {
        powerUps = new ArrayList<PowerUp>();
        this.subject = subject;
        subject.register(this);
    }

    public void update(float delta) {
        for (PowerUp powerUp : powerUps) {
            powerUp.update(delta);
        }
    }

    public void render(ShapeRenderer srenderer) {
        for (PowerUp powerUp : powerUps) {
            powerUp.render(srenderer);
        }
    }

    public void addPowerUp(float x, float y) {
        PowerUp powerUp = new PowerUp(x, y, POWERUP_WIDTH, POWERUP_HEIGHT);
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
