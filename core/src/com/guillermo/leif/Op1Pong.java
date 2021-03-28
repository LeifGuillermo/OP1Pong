package com.guillermo.leif;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.guillermo.leif.controller.Op1PongHandler;
import com.guillermo.leif.controller.midiInput.Op1Controller;

import static com.guillermo.leif.GlobalVars.viewHeight;
import static com.guillermo.leif.GlobalVars.viewWidth;

public class Op1Pong extends Game {
    SpriteBatch batch;

    GameState gamestate;

    FitViewport viewport;
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;


    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(viewWidth, viewHeight, camera);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewWidth = width;
        viewHeight = height;
        viewport.update(width, height);
    }
}
