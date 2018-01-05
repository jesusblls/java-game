package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.Player;
import com.mictlan.brick.entities.PowerUp;

import java.util.ArrayList;
import java.util.Iterator;

public class PowerUpController {
    private final int PU_WIDTH = 20;
    private final int PU_HEIGHT = 20;

    private ArrayList<PowerUp> powerUps;
    private Player player;
    private ScoreController scoreController;
    private PowerUp powerUp;

    public PowerUpController(Player player, ScoreController scoreController) {

        powerUps = new ArrayList<PowerUp>();
        this.player = player;
        this.scoreController = scoreController;
    }

    public void update() {

        Iterator<PowerUp> iter = powerUps.iterator();
        while (iter.hasNext()) {
            powerUp = iter.next();
            powerUp.update();
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

    public void addPowerUp(int x, int y) {
        PowerUp powerUp = new PowerUp(x, y, PU_WIDTH, PU_HEIGHT);
        powerUps.add(powerUp);
    }

    public boolean hasPowerUp(){
        return powerUps.size() > 0;
    }

    public void remove(PowerUp powerUp) {
        powerUps.remove(powerUp);
    }
}
