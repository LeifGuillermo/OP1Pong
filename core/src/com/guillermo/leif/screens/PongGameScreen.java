package com.guillermo.leif.screens;

import com.badlogic.gdx.Screen;
import com.guillermo.leif.GameState;
import com.guillermo.leif.Op1Pong;
import com.guillermo.leif.controller.Op1PongHandler;
import com.guillermo.leif.controller.midiInput.Op1Controller;

public class PongGameScreen implements Screen {
    private Op1Pong game;
    private GameState gamestate;
    private Op1PongHandler op1PongHandler;


    public PongGameScreen(final Op1Pong game) {
        this.game = game;
        gamestate = new GameState();
        Op1PongHandler op1PongHandler = new Op1PongHandler(gamestate);
        Op1Controller op1Controller = new Op1Controller(this, op1PongHandler);
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
