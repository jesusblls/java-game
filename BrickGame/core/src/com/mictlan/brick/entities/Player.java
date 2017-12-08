package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.utils.ColorFactory;

public class Player {
    private int width = 192;
    private int height = 32;
    private int x = 0;
    private int y = 0;
    private int velX = 200;

    private Color color;

    private Rectangle hitbox;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        hitbox = new Rectangle();
        color = ColorFactory.getColor(78, 32, 9);
    }

    public void render(ShapeRenderer srenderer) {
        srenderer.setColor(color);
        srenderer.rect(x, y, width, height);
    }

    public void update() {
        // Listen for input
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                x -= (velX * 1.5) * Gdx.graphics.getDeltaTime();
            }
            x -= velX * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += velX * Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                x += (velX * 1.5) * Gdx.graphics.getDeltaTime();
            }
        }

        // Check for collition
        if (x < 0) {
            x = 0;
        }
        if (x + width > 800) {
            x = 800 - width;
        }

    }
}
