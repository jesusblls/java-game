package com.mictlan.brick.utils;

import com.badlogic.gdx.graphics.Color;

public class ColorFactory {

    public static Color getColor(int r, int g, int b) {
        return new Color(r / 255.0f, g / 255.0f, b / 255.0f, 1);
    }

}
