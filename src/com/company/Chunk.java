package com.company;

import java.awt.*;

public class Chunk {
    short CHUNK_WIDTH = 5;

    MineButton[][] playTiles = new MineButton[CHUNK_WIDTH][CHUNK_WIDTH];//contained space?

    public Chunk(){

    }

    public Chunk(Color setColor){
        setAllChunkColor(setColor);
    }

    public void prepareChunk() {
    }

    public void generateChunk() {
        for (int row = 0; row < playTiles.length; row++) {
            for (int colum = 0; colum < playTiles[0].length; colum++) {
                playTiles[row][colum].setBackground(new Color(255/colum,255/row,100));
            }
        }
    }

    public void getLoadChunkToMemory() {
    }

//    public void unloadChunkFromMemory() {
//    }

    public void setAllChunkColor(Color newColor){//Create for double loop is a method that returns?
        for (int row = 0; row < playTiles.length; row++) {
            for (int colum = 0; colum < playTiles[0].length; colum++) {
                playTiles[row][colum].setBackground(newColor);
            }
        }
    }

    public void updateChunk(){//Create for double loop is a method that returns?
        for (int row = 0; row < playTiles.length; row++) {
            for (int colum = 0; colum < playTiles[0].length; colum++) {

            }
        }
    }

    public void attemptRandomTick(){

    }
}
