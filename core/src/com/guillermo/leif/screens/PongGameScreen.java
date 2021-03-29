package com.guillermo.leif.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.guillermo.leif.*;
import com.guillermo.leif.controller.Op1PongHandler;
import com.guillermo.leif.controller.midiInput.MidiListener;
import com.guillermo.leif.controller.midiInput.Op1Controller;

import javax.sound.midi.MidiUnavailableException;

public class PongGameScreen implements Screen {
    private final Op1Pong game;
    private final GameState gamestate;
    private final Sound wallBounceSound;
    private final Sound paddleBounceSound;
    OrthographicCamera camera;

    public PongGameScreen(final Op1Pong game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GlobalVars.viewWidth,
                GlobalVars.viewHeight);

        gamestate = new GameState();

        wallBounceSound = Gdx.audio.newSound(Gdx.files.internal("wall-bounce.wav"));
        paddleBounceSound = Gdx.audio.newSound(Gdx.files.internal("paddle-bounce.wav"));
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

        float direction = gamestate.pongBall.velocity.x;

        boolean player1Collide =
                Intersector.overlapConvexPolygons(gamestate.pongBall.polygon,
                        gamestate.player1.getPolygon());
        boolean player2Collide =
                Intersector.overlapConvexPolygons(gamestate.pongBall.polygon,
                        gamestate.player2.getPolygon());

        if (player1Collide && direction < 0 || player2Collide && direction > 0) {
//            gamestate.pongBall.velocity.x *= -1
            if (player1Collide) {
                gamestate.pongBall.velocity = reflectBall(gamestate.player1,
                        gamestate.pongBall.velocity, gamestate.pongBall.speed);
            } else {
                gamestate.pongBall.velocity = reflectBall(gamestate.player2,
                        gamestate.pongBall.velocity, gamestate.pongBall.speed);
            }
            paddleBounceSound.play();
        }
    }

    private Vector2 reflectBall(Player player, Vector2 ballVelocity,
                                int speed) {
        float[] vertices = player.getPolygon().getVertices();
        // top left point
        float x1 = vertices[0];
        float y1 = vertices[1];
        // top right point
        float x2 = vertices[6];
        float y2 = vertices[7];

        float ix = -(x2 - x1);
        float iy = y2 - y1;
        Vector2 normalVector = new Vector2(iy, ix);

        int newVelocityDirection = ballVelocity.x > 0 ? -1 : 1;

        float angle = ballVelocity.dot(normalVector);

        ballVelocity = ballVelocity.rotateAroundDeg(normalVector, angle);
        ballVelocity.nor();
        ballVelocity.x = newVelocityDirection * ballVelocity.x;
        return ballVelocity.scl(speed);

    }

    private float getDistanceTraveledThroughBottomOfScreen() {
        return (GlobalVars.viewHeight - (gamestate.pongBall.getY() + gamestate.pongBall.radius));
    }

    private void renderShapes() {
        game.shapeRenderer.begin();
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(Color.BLUE);
        game.shapeRenderer.polygon(gamestate.player1.getPolygon().getTransformedVertices());
        game.shapeRenderer.setColor(Color.WHITE);
        game.shapeRenderer.end();

        game.shapeRenderer.begin();
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(1, 0.25f, 0.25f, 1);
        game.shapeRenderer.polygon(gamestate.player2.getPolygon().getTransformedVertices());
        game.shapeRenderer.setColor(Color.WHITE);
        game.shapeRenderer.end();

        game.shapeRenderer.begin();
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(Color.GREEN);
        game.shapeRenderer.circle(gamestate.player1.pivot.x,
                gamestate.player1.pivot.y, gamestate.player1.pivot.radius);
        game.shapeRenderer.setColor(Color.WHITE);
        game.shapeRenderer.end();

        game.shapeRenderer.begin();
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(Color.WHITE);
        game.shapeRenderer.circle(gamestate.player2.pivot.x,
                gamestate.player2.pivot.y, gamestate.player2.pivot.radius);
        game.shapeRenderer.setColor(Color.WHITE);
        game.shapeRenderer.end();

        game.shapeRenderer.begin();
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(Color.PURPLE);
        game.shapeRenderer.circle(gamestate.pongBall.getX(),
                gamestate.pongBall.getY(),
                gamestate.pongBall.radius);
        game.shapeRenderer.setColor(Color.WHITE);
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
