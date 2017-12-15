package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.Brick;

import java.util.ArrayList;

public class BrickController {
    int numberOfBricks = 13;
    int rows = 5;
    int gap = 3;
    ArrayList<Brick> bricks;

    public BrickController() {
        bricks = new ArrayList<Brick>();
        int iniX = 54;
        int iniY = 395;
        Brick brick = null;

        for (int x = 0; x < rows; x++) {
            for (int i = 0; i < numberOfBricks; i++) {
                brick = new Brick(iniX, iniY);
                bricks.add(brick);
                iniX += brick.getWidth() + gap;
            }
            iniY -= (brick.getHeight() + gap);
            iniX = 54;
        }

    }

    public void render(ShapeRenderer srenderer) {
        for (Brick brick : bricks) {
            brick.render(srenderer, brick);
        }
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }
}
