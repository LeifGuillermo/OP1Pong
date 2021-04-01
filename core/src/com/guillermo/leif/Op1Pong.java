package com.guillermo.leif;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.guillermo.leif.screens.PongGameScreen;
import com.guillermo.leif.screens.PongTitleScreen;

public class Op1Pong extends Game {
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;

    private PongTitleScreen titleScreen;
    private PongGameScreen gameScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // TODO use something other than Arial
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        this.setScreen(new PongTitleScreen(this));
    }

    public void changeToScreen(PongScreen screen) {
        switch (screen) {
            case TITLE:
                if (null == titleScreen) {
                    titleScreen = new PongTitleScreen(this);
                }
                setScreen(titleScreen);
            case GAME:
                if (null == gameScreen) {
                    gameScreen = new PongGameScreen(this);
                }
                setScreen(gameScreen);
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
    }

    public enum PongScreen {
        TITLE,
        GAME
    }
}
