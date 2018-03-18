package com.example.chad.myapplication;

import android.graphics.Color;

/**
 * Created by chad on 2018/3/15.
 */

public enum Colors {
    LIGHTGREY("#D3D3D3"), BLUE("#33B5E5"), PURPLE("#AA66CC"),
    GREEN("#99CC00"), ORANGE("#FFBB33"), RED("#FF4444");

    private String code;


    Colors(String code){
        this.code=code;
    }

    public String getCode(){
        return code;
    }
    public int getColorsID(){
        return Color.parseColor(code);
    }
}
