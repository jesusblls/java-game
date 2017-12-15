package com.mictlan.brick;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mictlan.brick.screens.GameScreen;

public class BrickGame extends Game {
	private SpriteBatch batch;
	private ShapeRenderer srenderer;
	private BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		srenderer = new ShapeRenderer();
		font = new BitmapFont();

		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public ShapeRenderer getSrenderer() {
		return srenderer;
	}

	public BitmapFont getFont() {
		return font;
	}
}

