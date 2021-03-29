package com.guillermo.leif;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class PongBall {
    public float radius;
    public Vector2 velocity;
    public Color outlineColor;
    public Color fillColor;
    public Polygon polygon;
    public int speed = 30;
    Random random = new Random();
    private float x, y; // position

    public PongBall() {
        this.radius = 25;
        this.x = GlobalVars.viewWidth / 2f - radius;
        this.y = GlobalVars.viewHeight / 2f - radius;
        this.velocity = new Vector2();

        //Start in a random direction at speed.
        velocity.x = random.nextFloat() - 0.5f;
        velocity.y = random.nextFloat() - 0.5f;
        velocity.nor(); // normalize so the magnitude = 1
        velocity.scl(speed); // multiply by speed.

        outlineColor = fillColor = new Color(1, 1, 1, 1);

        polygon = new Polygon(createNPointPolygonArray(10, radius));
        polygon.setOrigin(x + radius, y + radius);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        this.polygon.setPosition(x, y);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        this.polygon.setPosition(x, y);
    }

    public float[] createNPointPolygonArray(int n, float radius) {
        float[] points = new float[n * 2];
        for (int currentPoint = 0; currentPoint < n; currentPoint++) {
            float theta = (2 * (float) Math.PI) / n;
            float angle = (theta * currentPoint);

            points[currentPoint] = radius * (float) Math.cos(angle);
            points[currentPoint+1] = radius * (float) Math.sin(angle);
        }
        return points;
    }

}
