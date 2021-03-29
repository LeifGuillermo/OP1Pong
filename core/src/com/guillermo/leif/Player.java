package com.guillermo.leif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;

public class Player {
    //    private Rectangle hitBox;
    private final Polygon polygon;
    public String name;
    // player state
    public int score;
    public int degreesRotation; // vertical is 0
    public Vector3 normalDirection; // direction orthogonal to paddle.
    public int speed; // no need for velocity since only traveling on y axis.
    public int rotationSpeed;
    // player graphics
    public Color outlineColor;
    public Color fillColor;
    public int width;
    public int height;
    private int lastMoveValue = 127 / 2;
    private int lastRotateValue = 127 / 2;

    public Circle pivot;

    public Player(String name, int x, int y) {
        this.name = name;
        speed = 50;
        rotationSpeed = 10;
        score = 0;
        degreesRotation = 0;
        normalDirection = new Vector3();
        normalDirection.x = 1;
        normalDirection.y = 0;
        normalDirection.z = 0;
        outlineColor = fillColor = new Color(1, 1, 1, 1);

        width = 6;
        height = 150;

        float[] rectanglePoly = new float[]{
                x, y,
                x + width, y,
                x + width, y + height,
                x, y + height
        };
        polygon = new Polygon(rectanglePoly);
        // set origin to center of rectangle to make it easy to rotate
        // correctly.
        polygon.setOrigin(x + (int)(width / 2f), y + (int)(height / 2f));
        pivot = new Circle(polygon.getOriginX(), polygon.getOriginY(),
                width/2f);
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public float getX() {
        return polygon.getX();
    }

    public void setX(float x) {
        this.polygon.setPosition(x, getY());
    }

    public float getY() {
        return polygon.getY();
    }

    public void setY(float y) {
        this.polygon.setPosition(getX(), y);
    }


    public void move(int value) {
        if (turnIsLeft(value, lastMoveValue)) {
            this.polygon.translate(0, speed);
        } else if (turnIsRight(value, lastMoveValue)) {
            this.polygon.translate(0, -speed);
        }
        if (polygon.getY() > 362) {
            polygon.setPosition(polygon.getX(),362);
        }
        if (polygon.getY() < -511) {
            polygon.setPosition(polygon.getX(), -511);
        }
        pivot.setPosition(polygon.getOriginX(),
                polygon.getY() + GlobalVars.viewHeight/2f + height/2f);
        lastMoveValue = value;
    }

    public void rotate(int value) {
        if (turnIsLeft(value, lastRotateValue)) {
            this.polygon.rotate(rotationSpeed);
        } else if (turnIsRight(value, lastRotateValue)) {
            this.polygon.rotate(-rotationSpeed);
        }
        lastRotateValue = value;
    }


    private boolean turnIsLeft(int value, int lastValue) {
        return lastValue > value || (0 == lastValue && 0 == value);
    }

    private boolean turnIsRight(int value, int lastValue) {
        return lastValue < value || (127 == lastValue && 127 == value);
    }

}
