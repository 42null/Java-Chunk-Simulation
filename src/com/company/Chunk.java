package com.company;

import com.company.entities.BasicEntity;
import com.company.entities.Entity;
import com.company.tileTypes.GameTile;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import static com.company.TileGame.CHUNK_WIDTH_IN_TILES;

public class Chunk implements ActionListener{

    public static final short chunkWidthForDimensions = 5;

    public int chunkNumber;

    GameTile[][] playTiles;
    //    Map<Integer[],Entity> entities = new HashMap();
    public ArrayList<Entity> entities = new ArrayList<>();//TODO: Make not public

    public Chunk(short chunkWidth){
        this.chunkNumber = -1;
        playTiles = new GameTile[chunkWidth][chunkWidth];
        for(int i = 0; i < playTiles.length; i++){
            for (int j = 0; j < playTiles[0].length; j++){//Switch to playTiles.length for efficiency as always square
//                playTiles[i][j] = new GameTile(i*playTiles[0].length+j*playTiles[0].length+"");
                playTiles[j][j] = new GameTile("ij");
                playTiles[i][j].setPreferredSize(new Dimension(chunkWidthForDimensions,chunkWidthForDimensions));
                playTiles[i][j].setColor(Defaults.STARTING_COLOR);
                playTiles[i][j].addActionListener(this);
                playTiles[i][j].setBackground(Color.RED);
            }
        }
    }

    public Chunk(int gameWidthInTiles, short chunkWidth, int chunkNumber, Color setColor){
        this.chunkNumber = chunkNumber;
        playTiles = new GameTile[chunkWidth][chunkWidth];

        for(int i = 0; i < playTiles.length; i++){
            for (int j = 0; j < playTiles[0].length; j++){//Switch to playTiles.length for efficiency as always square
//                playTiles[i][j] = new GameTile(i*playTiles[0].length+j*playTiles[0].length+"");
                playTiles[i][j] = new GameTile("1");
                playTiles[i][j].setPreferredSize(new Dimension(chunkWidthForDimensions,chunkWidthForDimensions));
                playTiles[i][j].setColor(Defaults.STARTING_COLOR);
            }
        }

        for(int i = 0; i < Defaults.entitesPerChunkGenerate; i++){
            int x = chunkNumber%gameWidthInTiles*chunkWidth+(int)(Math.random()*(chunkWidth));
            int y = chunkNumber/gameWidthInTiles*chunkWidth+(int)(Math.random()*(chunkWidth));
            System.out.println("x = "+x);
            System.out.println("y = "+y);
            System.out.println("gameWidthInTiles = "+gameWidthInTiles);
            entities.add(new BasicEntity(i,x,y, Color.BLACK));
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
                    playTiles[row][colum].getColor()==Color.cyan ||
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

    /**
     * Ticks all entities within the chunk
     * @return true if the chunk contains o entities
     */
    public boolean tickEntities(){
//        for (Iterator<Map.Entry<Entity>> i = entities.iterator(); i.hasNext();) {
//            Map.Entry<String,Integer> entry = i.next();
//            entry.setValue(entry.getValue() - 20); // update via the Map.Entry
//            if (entry.getValue() <= 0) {
//                i.remove(); // remove via the iterator
//            }
//        }

//        for(Entity entity: entities){

        for(int i=0; i<entities.size();i++){
            try{
                entities.get(i).onTick();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(entities.size()==0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().toString();
        System.out.println(this.getClass() + "   :   "+source);
        String sourceShortened = source.substring("com.company.tileTypes.GameTile[1,".length(),source.indexOf('x'));
        int boxX = Integer.parseInt(sourceShortened.substring(0,sourceShortened.indexOf(',')))/this.chunkWidthForDimensions;
        int boxY = Integer.parseInt(sourceShortened.substring(sourceShortened.indexOf(',')+1,sourceShortened.lastIndexOf(',')))/this.chunkWidthForDimensions;
        System.out.println("-------------");
        System.out.println("X = "+boxX);
        System.out.println("Y = "+boxY);
//        boxX = boxX/(CHUNK_WIDTH_IN_TILES);
//        boxY = boxY/(CHUNK_WIDTH_IN_TILES);
//        System.out.println("bX= "+boxX);
//        System.out.println("bY= "+boxY);
        for(int i = 0; i < 100; i++){
            entities.add(new BasicEntity((int)(Math.random()*100), boxX,boxY, Color.BLACK));
        }
        playTiles[boxX%CHUNK_WIDTH_IN_TILES][boxY%CHUNK_WIDTH_IN_TILES].setBackground(Color.RED);//TODO: Make method for adding or override
        System.out.println(">>"+entities.size());
        System.out.println("{{{{{{{"+entities.size());
    }

}