package com.mictlan.brick.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.observer.Observer;
import com.mictlan.brick.observer.Subject;
import com.mictlan.brick.utils.ColorFactory;

public class Player extends GameObject {
    private int velX = 200;
    private float friction = .80F;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        color = ColorFactory.getColor(78, 32, 9);
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
        // Adjust hitbox
        hitbox.x = x;
        hitbox.y = y;

    }

    public float getFriction() {
        return friction;
    }


}
