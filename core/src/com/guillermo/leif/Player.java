package com.guillermo.leif;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class Player {
    public Player(String name, int x, int y) {
        this.name = name;
        this.score = 0;
        // todo - may need to adjust x and y by a factor of width/height.
        this.x = x;
        this.y = y;
        this.degreesRotation = 0;
        this.normalDirection = new Vector3();
        this.normalDirection.x = 1;
        this.normalDirection.y = 0;
        this.normalDirection.z = 0;

        this.outlineColor = this.fillColor = new Color(1, 1, 1, 1);
        this.width = 2;
        this.height = 100;
    }

    String name;
    // player state
    public int score;
    public int x;
    public int y;
    public int degreesRotation; // vertical is 0
    public Vector3 normalDirection; // direction orthogonal to paddle.

    public int speed; // no need for velocity since only traveling on y axis.

    // player graphics
    public Color outlineColor;
    public Color fillColor;

    public int width;
    public int height;
}
