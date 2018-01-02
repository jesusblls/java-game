package com.mictlan.brick.controllers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.entities.Brick;
import com.mictlan.brick.entities.GameObject;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;
import com.mictlan.brick.screens.GameScreen;
import com.mictlan.brick.utils.ColorFactory;

import java.util.Random;

import java.util.ArrayList;

public class BrickController implements Observer {
    int numberOfBricks = 13;
    private int rows = 5;
    private final float BRICK_WIDTH = 50 / GameScreen.RESIZE_FACTOR;
    private final float BRICK_HEIGHT = 30 / GameScreen.RESIZE_FACTOR;
    private float gap = 3;
    private float totalWidht = (numberOfBricks * (BRICK_WIDTH + gap)) - 3;
    private  float iniX = (GameScreen.GAME_WIDTH - totalWidht) / 2;
    private float iniY = 395;

    private Subject subject;
    private ArrayList<Brick> bricks;

    public BrickController(Subject subject) {
        this.subject = subject;
        subject.register(this);
        bricks = new ArrayList<Brick>();
        float newX = iniX;
        float newY = iniY;
        Brick brick = null;
        for (int x = 0; x < rows; x++) {
            for (int i = 0; i < numberOfBricks; i++) {
                Random rand = new Random();
                int intRand = rand.nextInt(10);
                if (intRand < 9){
                    brick = new Brick(newX, newY, BRICK_WIDTH, BRICK_HEIGHT, true);
                    Color color = ColorFactory.getColor(70,130,180);
                    brick.setColor(color);
                } else {
                    brick = new Brick(newX, newY, BRICK_WIDTH, BRICK_HEIGHT, false);
                }
                bricks.add(brick);
                newX += brick.getWidth() + gap;
            }
            newY -= (brick.getHeight() + gap);
            newX = iniX;
        }

    }

    public void render(ShapeRenderer srenderer) {
        for (GameObject brick : bricks) {
            brick.render(srenderer);
        }
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void remove(Brick brick) {
        bricks.remove(brick);
    }

    @Override
    public void update(GameObject entity) {
        remove((Brick) entity);
    }
}
