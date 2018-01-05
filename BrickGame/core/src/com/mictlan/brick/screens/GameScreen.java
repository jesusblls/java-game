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
import com.mictlan.brick.entities.PowerUp;

public class GameScreen implements Screen {
    private final int PLAYER_WIDTH = 192;
    private final int PLAYER_HEIGHT = 32;
    private final int BALL_WIDHT = 32;
    private final int BALL_HEIGHT = 32;

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
        player = new Player(0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        brickController = new BrickController();
        // needs to be called last
        ball = new Ball(100, 140, BALL_WIDHT, BALL_HEIGHT, player, brickController, this);
        scoreController = new ScoreController(ball);
        puController = new PowerUpController(player, scoreController);
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
        player.render(game.getSrenderer());
        brickController.render(game.getSrenderer());
        ball.render(game.getSrenderer());
        puController.render(game.getSrenderer());
        game.getSrenderer().end();
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Points: " + scoreController.getScore(), 0, 480);
        if (ball.getY() < 0){
            game.getFont().draw(game.getBatch(), "Click to play again", 250, 100);
            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
            }
        }
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
