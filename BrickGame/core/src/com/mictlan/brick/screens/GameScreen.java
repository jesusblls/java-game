package com.mictlan.brick.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mictlan.brick.BrickGame;
import com.mictlan.brick.controllers.BrickController;
import com.mictlan.brick.controllers.CollisionController;
import com.mictlan.brick.controllers.PowerUpController;
import com.mictlan.brick.controllers.ScoreController;
import com.mictlan.brick.entities.Ball;
import com.mictlan.brick.entities.Player;
import com.mictlan.brick.entities.Brick;

public class GameScreen implements Screen {
    private final BrickGame game;
    public static final float GAME_WIDTH = 800;
    public static final float GAME_HEIGHT = 480;
    public static final float  RESIZE_FACTOR = 1.3f;
    private final float PLAYER_WIDTH = 192 / RESIZE_FACTOR;
    private final float PLAYER_HEIGHT = 32 / RESIZE_FACTOR;
    private final float BALL_WIDTH = 32 / RESIZE_FACTOR;
    private final float BALL_HEIGHT = 32 / RESIZE_FACTOR;
    private final float TIME_STEP = 0.01666666f;
    private float accumulator = 0;

    private OrthographicCamera camera;
    private Player player;
    private Ball ball;
    private BrickController brickController;
    private ScoreController scoreController;
    private PowerUpController puController;
    private CollisionController collisionController;

    public GameScreen(final BrickGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
        ball = new Ball(200, 200, BALL_WIDTH, BALL_HEIGHT);
        player = new Player(300, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        collisionController = new CollisionController(ball);
        brickController = new BrickController(collisionController);
        scoreController = new ScoreController(collisionController);
        puController = new PowerUpController(player, scoreController, collisionController);

        // add entitites to collision controller
        collisionController.setPlayer(player);
        collisionController.setBricks(brickController.getBricks());
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

        // Add an accumulator for long render times
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            player.update(TIME_STEP);
            ball.update(TIME_STEP);
            puController.update(TIME_STEP);
            accumulator -= TIME_STEP;
        }

        player.update(delta);
        ball.update(delta);
        puController.update(delta);



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
}
