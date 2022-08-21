package com.company;

import java.awt.*;

public class Chunk{

    public static final short CHUNK_WIDTH = 5;

    GameTile[][] playTiles = new GameTile[CHUNK_WIDTH][CHUNK_WIDTH];//contained space?

    public Chunk(){
        for(int i = 0; i < playTiles.length; i++){
            for (int j = 0; j < playTiles[0].length; j++){//Switch to playTiles.length for efficiency as always square
//                playTiles[i][j] = new GameTile(i*playTiles[0].length+j*playTiles[0].length+"");
                playTiles[i][j] = new GameTile("1");
                playTiles[i][j].setPreferredSize(new Dimension(20,20));
                playTiles[i][j].setBackground(Defaults.STARTING_COLOR);
            }
        }
    }

    public Chunk(Color setColor){
        for(int i = 0; i < playTiles.length; i++){
            for (int j = 0; j < playTiles[0].length; j++){//Switch to playTiles.length for efficiency as always square
//                playTiles[i][j] = new GameTile(i*playTiles[0].length+j*playTiles[0].length+"");
                playTiles[i][j] = new GameTile("1");
                playTiles[i][j].setPreferredSize(new Dimension(20,20));
                playTiles[i][j].setBackground(Defaults.STARTING_COLOR);
            }
        }
        setAllChunkColor(setColor);
    }

    public GameTile getTile(int x, int y){
        return playTiles[x][y];
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
