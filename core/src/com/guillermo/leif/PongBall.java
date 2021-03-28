package com.guillermo.leif;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class PongBall {
    private float x, y; // position
    public float radius;
    public Vector3 velocity;
    public Color outlineColor;
    public Color fillColor;

    /* TODO: Use a polygon (needs to be a polygon
               to check for overlap with another polygon.*/
    private Rectangle hitBox;
    public int speed = 40;

    Random random = new Random();

    public PongBall() {
        this.radius = 15;
        this.x = GlobalVars.viewWidth / 2f - radius;
        this.y = GlobalVars.viewHeight / 2f - radius;
        this.velocity = new Vector3();

        //Start in a random direction at speed.
        velocity.x = random.nextFloat();
        velocity.y = random.nextFloat();
        velocity.z = random.nextFloat();
        velocity.nor(); // normalize so the magnitude = 1
        velocity.scl(speed); // multiply by speed.

        outlineColor = fillColor = new Color(1, 1, 1, 1);

        hitBox = new Rectangle();
        hitBox.x = x;
        hitBox.y = y;
        hitBox.width = radius*2;
        hitBox.height = radius*2;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x = x;
        this.hitBox.x = x;
    }

    public void setY (float y) {
        this.y = y;
        this.hitBox.y = y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
        this.x = hitBox.x;
        this.y = hitBox.y;
        this.radius = hitBox.width/2f;
    }
}
