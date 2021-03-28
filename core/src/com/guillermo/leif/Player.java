package com.guillermo.leif;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {
    //    todo Polygon hitbox;
    public String name;
    // player state
    public int score;
    public int degreesRotation; // vertical is 0
    public Vector3 normalDirection; // direction orthogonal to paddle.
    public int speed; // no need for velocity since only traveling on y axis.
    // player graphics
    public Color outlineColor;
    public Color fillColor;
    public int width;
    public int height;
    private Rectangle hitBox;
    private float x;
    private float y;
    private float lastMoveValue = 127 / 2f;
    private float lastRotateValue = 127 / 2f;

    public Player(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;

        speed = 30;
        score = 0;
        degreesRotation = 0;
        normalDirection = new Vector3();
        normalDirection.x = 1;
        normalDirection.y = 0;
        normalDirection.z = 0;
        outlineColor = fillColor = new Color(1, 1, 1, 1);
        width = 5;
        height = 150;
        hitBox = new Rectangle();
        hitBox.x = x;
        hitBox.y = y;
        hitBox.width = width;
        hitBox.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        this.hitBox.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        this.hitBox.y = y;
    }

    public void move(int value) {
        if (turnIsLeft(value)) {
            setY(getY() + speed);
        } else if (turnIsRight(value)) {
            setY(getY() - speed);
        }

        if(getY() < 0) {
            setY(0);
        }
        if(getY() > GlobalVars.viewHeight - height) {
            setY(GlobalVars.viewHeight - height);
        }

        lastMoveValue = value;
    }

    public void rotate(int value) {

    }

    private boolean turnIsLeft(int value) {
        return lastMoveValue > value || (0 == lastMoveValue && 0 == value);
    }

    private boolean turnIsRight(int value) {
        return lastMoveValue < value || (127 == lastMoveValue && 127 == value);
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }
}
