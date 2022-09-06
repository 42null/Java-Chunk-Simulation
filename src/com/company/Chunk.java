package com.company;

import com.company.tileTypes.GameTile;

import java.awt.*;

public class Chunk{

    public static final short chunkWidthForDimensions = 15;

    GameTile[][] playTiles;

    public Chunk(short chunkWidth){
        playTiles = new GameTile[chunkWidth][chunkWidth];
        for(int i = 0; i < playTiles.length; i++){
            for (int j = 0; j < playTiles[0].length; j++){//Switch to playTiles.length for efficiency as always square
//                playTiles[i][j] = new GameTile(i*playTiles[0].length+j*playTiles[0].length+"");
                playTiles[j][j] = new GameTile("ij");
                playTiles[i][j].setPreferredSize(new Dimension(chunkWidthForDimensions,chunkWidthForDimensions));
                playTiles[i][j].setColor(Defaults.STARTING_COLOR);
            }
        }
    }

    public Chunk(short chunkWidth, Color setColor){
        playTiles = new GameTile[chunkWidth][chunkWidth];

        for(int i = 0; i < playTiles.length; i++){
            for (int j = 0; j < playTiles[0].length; j++){//Switch to playTiles.length for efficiency as always square
//                playTiles[i][j] = new GameTile(i*playTiles[0].length+j*playTiles[0].length+"");
                playTiles[i][j] = new GameTile("1");
                playTiles[i][j].setPreferredSize(new Dimension(chunkWidthForDimensions,chunkWidthForDimensions));
                playTiles[i][j].setColor(Defaults.STARTING_COLOR);
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
                playTiles[row][colum].setColor(new Color(255/colum,255/row,100));
            }
        }
    }

    public void getLoadChunkToMemory() {
    }

//    public void unloadChunkFromMemory() {
//    }

    public void setAllChunkColor(Color newColor){//Create for double loop is a method that returns?
        for (int row = 0; row < playTiles[0].length; row++){
            for (int colum = 0; colum < playTiles[0].length; colum++){
                if( playTiles[row][colum].getColor()==Defaults.STARTING_COLOR ||
                    playTiles[row][colum].getColor()==Color.orange ||
                    playTiles[row][colum].getColor()==Color.yellow ||
                    playTiles[row][colum].getColor()==Color.pink ||
                    playTiles[row][colum].getColor()==Color.blue ||
                    playTiles[row][colum].getColor()==Color.green){
                    playTiles[row][colum].setColor(newColor);
                }else{
                }
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
