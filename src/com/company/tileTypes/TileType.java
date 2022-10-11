package com.company.tileTypes;

import java.awt.Color;
import java.awt.event.ActionListener;

public interface TileType extends ActionListener {
    /**
     * tileID controls what tile type it is. Each unique tile type is the first value, and the second encodes any special information about it.
     */
    public char[] tileID = new char[2];//TODO: Just Do GetClass?
    public String name = "NAME NOT SET";

    public boolean passible = false;//Can go through
    public boolean landable = false;//Player can move onto spot
    public boolean liquid = false;
    public short state = -1;

    public Color defaultColor = new Color(204,9,160); //The Pink Found In Error Textures (https://colornames.org/color/cc09a0)

    public boolean tick();
    public boolean randomTick();

    public void setColor(Color newColor);
    public void updateColorInDisplay();


    public void rightClick();

}