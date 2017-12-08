package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.Brick;

import java.util.ArrayList;

/**
 * Created by josel.garza on 22/11/2017.
 */
public class BrickController {
    int numberOfBricks = 15;
    int rows = 5;
    int gap = 3;
    ArrayList<Brick> bricks;

    public BrickController(){
        bricks = new ArrayList<Brick>();
        int iniX = 4;
        int iniY = 445;
        Brick brick = null;

        for (int x = 0; x < rows; x++) {
            for (int i = 0; i < numberOfBricks; i++) {
                brick = new Brick(iniX, iniY);
                bricks.add(brick);
                iniX += brick.getWidth() + gap;
            }
            iniY -= (brick.getHeight() + gap);
            iniX = 4;
        }

    }

    public void render(ShapeRenderer srenderer){
        for (Brick brick: bricks){
            brick.render(srenderer);
        }
    }

}
