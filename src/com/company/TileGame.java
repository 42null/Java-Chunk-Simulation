package com.company;

public class TileGame {
    int playerX = 6;
    int playerY = 6;

    public TileGame(){
        //Generate Chunks

    }

    public int getPlayerX(){return  playerX;}
    public int getPlayerY(){return  playerY;}

    /**
     * Move north
     * @return true if player moved between tiles
     */
    public boolean moveNorth(){
        return true;
    }
    public boolean moveEast(){
        return true;
    }
    public boolean moveSouth(){
        return true;
    }
    public boolean moveWest(){
        return true;
    }
}
