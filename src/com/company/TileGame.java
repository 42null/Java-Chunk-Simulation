package com.company;

import javax.swing.*;
import java.awt.*;

public class TileGame implements Runnable  {
    final static int CHUNK_WIDTH_IN_TILES = 5;
    final static int GAME_WIDTH_IN_CHUNKS = 6;

    final static int TILES_PER_CHUNK_PER_RANDOM_TICK = 3;

    TileIsChunk chunkDisplayAndController;
    GameTile[][] gameView;

    JFrame gameBoardFrame;
    JTextField t1;
    
    int playerX = 3;
    int playerY = 3;

    @Override
    public void run(){
        //Run everything in a tick
        while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
            System.out.println("-----------------------------------------Hello from other thread");
            chunkDisplayAndController.runRandomTicks(TILES_PER_CHUNK_PER_RANDOM_TICK);
        }
    }

    public TileGame(){
        //Create Board Chunks
        chunkDisplayAndController = new TileIsChunk(CHUNK_WIDTH_IN_TILES);
        //Generate Chunks


        gameBoardFrame = new JFrame("GAME PLAY BOARD");
        t1 = new JTextField("");

//        USE TILES FROM TILEISCHUNK
//        gameView = new GameTile[chunkDisplayAndController.gameNumOfTilesWidth*Chunk.CHUNK_WIDTH][chunkDisplayAndController.gameNumOfTilesWidth*Chunk.CHUNK_WIDTH];
        gameView = new GameTile[5*5][5*5];
//        chunkDisplayAndController.gameNumOfTilesWidth
        for(int chunkNum = 0; chunkNum < chunkDisplayAndController.chunks.length; chunkNum++) {
            Chunk currentChunk = chunkDisplayAndController.chunks[chunkNum];

            int chunkAdderX = (chunkNum/Chunk.CHUNK_WIDTH)*Chunk.CHUNK_WIDTH;
            int chunkAdderY = (chunkNum%Chunk.CHUNK_WIDTH)*Chunk.CHUNK_WIDTH;

            for(int innerPlayTileX = 0; innerPlayTileX < Chunk.CHUNK_WIDTH; innerPlayTileX++){
                for(int innerPlayTileY = 0; innerPlayTileY < Chunk.CHUNK_WIDTH; innerPlayTileY++){
//                    gameView[chunkNum/Chunk.CHUNK_WIDTH][chunkNum%Chunk.CHUNK_WIDTH] = currentChunk.getTile(innerPlayTileX,innerPlayTileY);
                    gameView[chunkAdderX+innerPlayTileX][chunkAdderY+innerPlayTileY] = currentChunk.getTile(innerPlayTileX,innerPlayTileY);
//                    gameView[chunkNum/Chunk.CHUNK_WIDTH][chunkNum%Chunk.CHUNK_WIDTH] = new GameTile(chunkNum/Chunk.CHUNK_WIDTH+"");// currentChunk.playTiles[innerPlayTileX][innerPlayTileY];

                    System.out.println("On chunk #"+chunkNum+" of "+chunkDisplayAndController.chunks.length);
                    System.out.println("Filled ["+chunkNum/Chunk.CHUNK_WIDTH+"]["+chunkNum%Chunk.CHUNK_WIDTH+"]");
                    System.out.println("Filled ["+innerPlayTileX+"]["+innerPlayTileY+"]<--");

                    gameView[chunkAdderX+innerPlayTileX][chunkAdderY+innerPlayTileY].setBackground(Color.RED);
                    gameBoardFrame.add(gameView[chunkAdderX+innerPlayTileX][chunkAdderY+innerPlayTileY]);
                }
            }



        }
        System.out.println("END");
        System.out.println("gameView.length = "+gameView.length);
        System.out.println("gameView.length[0] = "+gameView[0].length);
        System.out.println("gameView[][].class = "+gameView[0][0].getClass());

//        for(int i = 0; i< gameView.length; i++){
//            for(int j = 0; j< gameView[0].length; j++){
////                gameView[i][j] = new GameTile(i*gameView.length+j+"");
//                System.out.println("GameTile ["+i+"]["+j+"]");
//                gameView[i][j].setPreferredSize(new Dimension(20,20));
//                gameView[i][j].setBackground(Defaults.STARTING_COLOR);
//    //            tmpButton.setText(i+"");
//                gameBoardFrame.add(gameView[i][j]);
//            }
//        }
        System.out.println("END2");

        gameBoardFrame.setLayout(new GridLayout(chunkDisplayAndController.gameNumOfTilesWidth*Chunk.CHUNK_WIDTH,chunkDisplayAndController.gameNumOfTilesWidth*Chunk.CHUNK_WIDTH));

//      ADD ALL BUTTONS TO FRAME
//        for (int i = 0; i < gameView.length; i++) {
//            GameTile tmpButton = gameView[i];
////            tmpButton.addActionListener(this);
//            gameBoardFrame.add(tmpButton);
//        }
//



        gameBoardFrame.pack();
        gameBoardFrame.setVisible(true);




//        chunkDisplayAndController.displayCurrentPlayersChunk();

    }

    public int getPlayerX(){return  playerX;}
    public int getPlayerY(){return  playerY;}

    /**
     * Move north
     * @return true if player moved to another tile
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
