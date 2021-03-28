package com.guillermo.leif.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.guillermo.leif.GameState;
import com.guillermo.leif.GlobalVars;
import com.guillermo.leif.Op1Pong;
import com.guillermo.leif.controller.Op1PongHandler;
import com.guillermo.leif.controller.midiInput.MidiListener;
import com.guillermo.leif.controller.midiInput.Op1Controller;

import javax.sound.midi.MidiUnavailableException;

public class PongGameScreen implements Screen {
    private Op1Pong game;
    private GameState gamestate;
    private Op1PongHandler op1PongHandler;

    FitViewport viewport;
    OrthographicCamera camera;

    public PongGameScreen(final Op1Pong game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GlobalVars.viewWidth, GlobalVars.viewHeight);

        gamestate = new GameState();

        Op1PongHandler op1PongHandler = new Op1PongHandler(gamestate);
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

        game.batch.begin();
        game.font.draw(game.batch, "P1 Score: " + gamestate.player1.score, 20,
                camera.viewportHeight-20);
        game.font.draw(game.batch, "P2 Score: " + gamestate.player2.score,
                camera.viewportWidth- 20 - 75,
                camera.viewportHeight-20);
        game.batch.end();
        renderShapes();


    }

    private void renderShapes() {

        game.shapeRenderer.begin();
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

        game.shapeRenderer.rect(gamestate.player1.x, gamestate.player1.y,
                gamestate.player1.width, gamestate.player1.height);
        game.shapeRenderer.rect(gamestate.player2.x, gamestate.player2.y,
                gamestate.player2.width, gamestate.player2.height);
        game.shapeRenderer.circle(gamestate.pongBall.x, gamestate.pongBall.y,
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
