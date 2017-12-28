package com.mictlan.brick.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mictlan.brick.BrickGame;
import com.mictlan.brick.controllers.BrickController;
import com.badlogic.gdx.math.Rectangle;
import com.mictlan.brick.controllers.PowerUpController;
import com.mictlan.brick.controllers.ScoreController;
import com.mictlan.brick.entities.Ball;
import com.mictlan.brick.entities.Player;
import com.mictlan.brick.entities.Brick;

public class GameScreen implements Screen {
    private final BrickGame game;

    private OrthographicCamera camera;
    private Player player;
    private BrickController brickController;
    private ScoreController scoreController;
    private Ball ball;
    private PowerUpController puController;

    public GameScreen(final BrickGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        player = new Player(0, 0);
        brickController = new BrickController();
        puController = new PowerUpController();
        scoreController = new ScoreController();
        // needs to be called last
        ball = new Ball(150, 100, player, brickController, scoreController, this);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getSrenderer().setProjectionMatrix(camera.combined);
        game.getSrenderer().begin(ShapeType.Filled);

        // Rendering begins
        player.render(game.getSrenderer(), player);
        brickController.render(game.getSrenderer());
        ball.render(game.getSrenderer(), ball);
        puController.render(game.getSrenderer());
        game.getSrenderer().end();
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Points: " + scoreController.getScore(), 0, 480);
        game.getBatch().end();
        // Rendering ends

        player.update();
        ball.update();
        puController.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }



    public OrthographicCamera getCamera() {
        return camera;
    }

    public void spawnPowerUp(Brick brick) {
        puController.addPowerUp(brick.getX(), brick.getY());
    }
}
