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
    private Player player;
    private ScoreController scoreController;
    private PowerUp powerUp;
    private Subject subject;

    public PowerUpController(Player player, ScoreController scoreController, Subject subject) {
        powerUps = new ArrayList<PowerUp>();
        this.player = player;
        this.scoreController = scoreController;
        this.subject = subject;
        subject.register(this);
    }

    public void update(float delta) {
        Iterator<PowerUp> iter = powerUps.iterator();
        while (iter.hasNext()) {
            powerUp = iter.next();
            powerUp.update(delta);
            if (player.getHitbox().overlaps(powerUp.getHitbox())) {
                iter.remove();
                scoreController.addPoints(200);
            }
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
    public void update(GameObject gameObject) {
        if (gameObject instanceof Brick) {
            Brick brick = ((Brick) gameObject);
            if(brick.hasPowerUp()) {
                addPowerUp(brick.getX(), brick.getY());
            }
        }
    }
}
