package com.mictlan.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DeluxeDrop extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    public void create() {
        batch = new SpriteBatch();

        // Use default area font
        font = new BitmapFont();
        font.getData().setScale(2, 2);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // important
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
