package com.guillermo.leif;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class Player {
    // player state
    int score;
    int x;
    int y;
    int degreesRotation; // vertical is 0
    Vector3 normalDirection; // direction orthogonal to paddle.

    int speed; // no need for velocity since only traveling on y axis.

    // player graphics
    Color outlineColor;
    Color fillColor;

    private static int width;
    private static int height;
}
