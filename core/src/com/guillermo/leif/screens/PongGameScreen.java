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
    ShapeRenderer shapeRenderer;

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

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Game started", 100, 150);
        game.batch.end();
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
