package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.Brick;
import com.mictlan.brick.entities.PowerUp;

import java.util.ArrayList;


public class PowerUpController {
    private Brick brick;
    ArrayList<PowerUp> powerUps;
    PowerUp powerUp = null;

    // funcion para agregar powerup

    public void add(Brick brick){
        powerUps.add(brick);
    }

    public boolean hasPowerUp(){
        return powerUps.size() != 0;
    }
    // fucnion para ver estado de la lista
    // public boolean hasPowerUp() { return powerUps.size() != 0}


    
    public void render(ShapeRenderer srenderer) {
        powerUp.render(srenderer, powerUp);
        powerUp.update();
    }

    //render (shaperenderer) { for (powerUp : powerUps)){
    //      powerup.render(srender)
    //      power.update()
    //  }
    // }





    public static void add(PowerUp powerUp) {
    }
}
