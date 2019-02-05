package com.example.calculator;

import java.io.Serializable;

public class bus implements Serializable {
    private final String eyeColor;
    private final String haircolor;

    public bus(String eyeColor, String haircolor){
        this.eyeColor = eyeColor;
        this.haircolor = haircolor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getHaircolor() {
        return haircolor;
    }
}

