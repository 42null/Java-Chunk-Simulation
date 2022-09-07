package com.company;

import com.company.tileTypes.GameTile;

import java.awt.*;
import java.nio.file.FileSystemNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Chunk{

    public static final short chunkWidthForDimensions = 15;

    GameTile[][] playTiles;
    //    Map<Integer[],Entity> entities = new HashMap();
    ArrayList<Entity> entities = new ArrayList<>();

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

    public Chunk(int gameWidthInTiles, short chunkWidth, int chunkNumber, Color setColor){
        playTiles = new GameTile[chunkWidth][chunkWidth];

        for(int i = 0; i < playTiles.length; i++){
            for (int j = 0; j < playTiles[0].length; j++){//Switch to playTiles.length for efficiency as always square
//                playTiles[i][j] = new GameTile(i*playTiles[0].length+j*playTiles[0].length+"");
                playTiles[i][j] = new GameTile("1");
                playTiles[i][j].setPreferredSize(new Dimension(chunkWidthForDimensions,chunkWidthForDimensions));
                playTiles[i][j].setColor(Defaults.STARTING_COLOR);
            }
        }

        for(int i = 0; i < 5; i++){
            entities.add(new BasicEntity(i,chunkNumber%gameWidthInTiles+(int)(Math.random()*(chunkWidth-1)),chunkNumber/gameWidthInTiles+(int)(Math.random()*(chunkWidth-1)), Color.BLACK));
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
    public void randomTick(){

    }

    public void tickEntities(){
        for(Entity entity: entities){
            try{
                entity.onTick();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
