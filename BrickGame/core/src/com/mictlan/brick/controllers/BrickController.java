package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.Brick;
import com.mictlan.brick.utils.ColorFactory;

import java.util.Random;

import java.util.ArrayList;

public class BrickController {
    private final int BRICK_WIDTH = 50;
    private final int BRICK_HEIGHT = 30;
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
                Random rand = new Random();
                int intRand = rand.nextInt(10);
                if (intRand < 2){
                    brick = new Brick(iniX, iniY, BRICK_WIDTH, BRICK_HEIGHT, true);
                    Color color = ColorFactory.getColor(70,130,180);
                    brick.setColor(color);
                } else {
                    brick = new Brick(iniX, iniY, BRICK_WIDTH, BRICK_HEIGHT, false);
                }
                bricks.add(brick);
                iniX += brick.getWidth() + gap;
            }
            iniY -= (brick.getHeight() + gap);
            iniX = 54;
        }

    }

    public void render(ShapeRenderer srenderer) {
        for (Brick brick : bricks) {
            brick.render(srenderer);
        }
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void remove(Brick brick) {
        bricks.remove(brick);
    }
}
