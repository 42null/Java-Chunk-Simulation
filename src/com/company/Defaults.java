package com.company;


import java.awt.*;

public class Defaults {
    public static final Color ERROR_COLOR = new Color(204,9,160); //The Pink Found In Error Textures (https://colornames.org/color/cc09a0)

    public static final Color STARTING_COLOR = new Color(238,238,238);

    public static final Color PLAYER_COLOR = Color.BLUE;


    public static final short MS_BETWEEN_TICKS = 10;//512;//256;

    public static final short chunksToKeepAtATime = 9999;
    public static final boolean entitiesLoadChunks = true;
    public static final boolean entitiesUnloadChunks = true;
    public static final short entitesPerChunkGenerate = 0;

    /*
        0 = normal;
        1 = chunks
    */
    public static final byte DISPLAY_MODE = (byte) 1;//TODO: Change to enum

}
