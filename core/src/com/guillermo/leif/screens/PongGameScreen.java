package com.guillermo.leif.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.guillermo.leif.GameState;
import com.guillermo.leif.GlobalVars;
import com.guillermo.leif.Op1Pong;
import com.guillermo.leif.PongBall;
import com.guillermo.leif.controller.Op1PongHandler;
import com.guillermo.leif.controller.midiInput.MidiListener;
import com.guillermo.leif.controller.midiInput.Op1Controller;

import javax.sound.midi.MidiUnavailableException;

public class PongGameScreen implements Screen {
    FitViewport viewport;
    OrthographicCamera camera;
    private Op1Pong game;
    private GameState gamestate;
    private Op1PongHandler op1PongHandler;
    private Sound wallBounceSound;
    private float p1x;
    private float p2x;
    private float p1y;
    private float p2y;

    public PongGameScreen(final Op1Pong game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GlobalVars.viewWidth, GlobalVars.viewHeight);

        gamestate = new GameState();

        wallBounceSound = Gdx.audio.newSound(Gdx.files.internal("wall-bounce" +
                ".wav"));

        op1PongHandler = new Op1PongHandler(gamestate);
        Op1Controller op1Controller = new Op1Controller(this, op1PongHandler);
        MidiListener listener = new MidiListener(op1Controller);
        try {
            listener.beginListening();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        updateBallLocation();
        game.batch.begin();
        game.font.draw(game.batch, "P1 Score: " + gamestate.player1.score, 20,
                20);
        game.font.draw(game.batch, "P2 Score: " + gamestate.player2.score,
                camera.viewportWidth - 20 - 75,
                20);
        game.batch.end();
        renderShapes();
    }

    private void updateBallLocation() {
        gamestate.pongBall.setX(gamestate.pongBall.velocity.x + gamestate.pongBall.getX());
        gamestate.pongBall.setY(gamestate.pongBall.velocity.y + gamestate.pongBall.getY());

        if (gamestate.pongBall.getY() < 0 && gamestate.pongBall.velocity.y < 0) {
            gamestate.pongBall.setY((gamestate.pongBall.getY() + gamestate.pongBall.radius));
            gamestate.pongBall.velocity.y *= -1;
            wallBounceSound.play();
        } else if (((gamestate.pongBall.getY() + gamestate.pongBall.radius) > GlobalVars.viewHeight) && gamestate.pongBall.velocity.y > 0) {
            gamestate.pongBall.setY(GlobalVars.viewHeight - getDistanceTraveledThroughBottomOfScreen());
            gamestate.pongBall.velocity.y *= -1;
            wallBounceSound.play();
        }

        if (gamestate.pongBall.getX() + gamestate.pongBall.radius >= GlobalVars.viewWidth) {
            gamestate.player1.score++;
            gamestate.pongBall = new PongBall();
        } else if (gamestate.pongBall.getX() <= 0) {
            gamestate.player2.score++;
            gamestate.pongBall = new PongBall();
        }

        // handle bounce off paddle
        boolean player1Collide =
                gamestate.pongBall.getHitBox().overlaps(gamestate.player1.getHitBox());
        boolean player2Collide =
                gamestate.pongBall.getHitBox().overlaps(gamestate.player2.getHitBox());

        float direction = gamestate.pongBall.velocity.x;

        if (player1Collide && direction < 0 || player2Collide && direction > 0){
            gamestate.pongBall.velocity.x *= -1;
        }
    }

    private float getDistanceTraveledThroughBottomOfScreen() {
        return (GlobalVars.viewHeight - (gamestate.pongBall.getY() + gamestate.pongBall.radius));
    }

    private void renderShapes() {
        p1x = gamestate.player1.getX();
        p2x = gamestate.player2.getX();
        p1y = gamestate.player1.getY();
        p2y = gamestate.player2.getY();

        game.shapeRenderer.begin();
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Line);

        game.shapeRenderer.rect(p1x, p1y,
                gamestate.player1.width, gamestate.player1.height);
        game.shapeRenderer.rect(p2x, p2y,
                gamestate.player2.width, gamestate.player2.height);
        game.shapeRenderer.circle(gamestate.pongBall.getX(),
                gamestate.pongBall.getY(),
                gamestate.pongBall.radius);
        game.shapeRenderer.end();
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
}
