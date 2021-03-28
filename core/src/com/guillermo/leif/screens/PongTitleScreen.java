package com.guillermo.leif.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.guillermo.leif.GlobalVars;
import com.guillermo.leif.Op1Pong;
import com.guillermo.leif.controller.Op1PongTitleHandler;
import com.guillermo.leif.controller.midiInput.MidiListener;
import com.guillermo.leif.controller.midiInput.Op1Controller;
import com.guillermo.leif.controller.midiInput.Op1Handler;

import javax.sound.midi.MidiUnavailableException;

public class PongTitleScreen implements Screen {
    private final Op1Pong game;
    public boolean encoderPressed = false;
    private OrthographicCamera camera;
    private MidiListener listener;

    public PongTitleScreen(final Op1Pong game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GlobalVars.viewWidth, GlobalVars.viewHeight);
        Op1Controller controller = new Op1Controller(this,
                new Op1PongTitleHandler(this));
        listener = new MidiListener(controller);
        try {
            listener.beginListening();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setEncoderPressed(boolean pressed) {
        encoderPressed = pressed;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Op-1 Pong", 100, 150);
        game.font.draw(game.batch, "Press any encoder to begin", 100, 100);
        game.batch.end();

        if (encoderPressed) {
            game.setScreen(new PongGameScreen(game));
            dispose();
        }
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
        listener.stopListening();
    }
}
