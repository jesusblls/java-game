package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.PowerUp;

import java.util.ArrayList;

/**
 * Created by josel.garza on 27/12/2017.
 */
public class PowerUpController {

    private ArrayList<PowerUp> powerUps;

    public PowerUpController() {
        powerUps = new ArrayList<PowerUp>();
    }

    public void update() {
        for (PowerUp powerUp : powerUps) {
            powerUp.update();
        }
    }

    public void render(ShapeRenderer srenderer) {
        for (PowerUp powerUp : powerUps) {
            powerUp.render(srenderer, powerUp);
        }
    }

    public void addPowerUp(int x, int y) {
        PowerUp powerUp = new PowerUp(x, y);
        powerUps.add(powerUp);
    }

    public boolean hasPowerUp(){
        return powerUps.size() > 0;
    }
}
