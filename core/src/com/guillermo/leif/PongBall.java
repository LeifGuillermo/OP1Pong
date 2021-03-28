package com.guillermo.leif;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class PongBall {
    public int x, y; // position
    public int radius;
    public Vector3 velocity;
    public Color outlineColor;
    public Color fillColor;

    public int speed = 10;

    Random random = new Random();
    public PongBall() {
        this.radius = 15;
        this.x = GlobalVars.viewWidth/2 - radius;
        this.y = GlobalVars.viewHeight/2 - radius;
        this.velocity = new Vector3();

        //Start in a random direction at speed.
        velocity.x = random.nextFloat();
        velocity.y = random.nextFloat();
        velocity.z = random.nextFloat();
        velocity.nor(); // normalize so the magnitude = 1
        velocity.scl(speed); // multiply by speed.

        outlineColor = fillColor = new Color(1, 1, 1, 1);

    }
}
