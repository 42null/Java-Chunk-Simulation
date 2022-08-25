package com.company;

import javax.swing.*;
import java.awt.*;

public class TileGame implements Runnable  {
    final static short CHUNK_WIDTH_IN_TILES = 6;
    final static int GAME_WIDTH_IN_CHUNKS = 10;

    final static int TILES_PER_CHUNK_PER_RANDOM_TICK = 0;//3;

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
            System.out.println("-----------------------------------------Hello from other thread END");
        }
    }

    public TileGame(){
        //Create Board Chunks
        chunkDisplayAndController = new TileIsChunk(GAME_WIDTH_IN_CHUNKS, CHUNK_WIDTH_IN_TILES);
        //Generate Chunks


        gameBoardFrame = new JFrame("GAME PLAY BOARD");
        t1 = new JTextField("");

//        USE TILES FROM TILEISCHUNK
//        gameView = new GameTile[chunkDisplayAndController.gameNumOfTilesWidth*Chunk.CHUNK_WIDTH][chunkDisplayAndController.gameNumOfTilesWidth*Chunk.CHUNK_WIDTH];
        gameView = new GameTile[GAME_WIDTH_IN_CHUNKS*CHUNK_WIDTH_IN_TILES][GAME_WIDTH_IN_CHUNKS*CHUNK_WIDTH_IN_TILES];
        for(int largeChunkY=0; largeChunkY<GAME_WIDTH_IN_CHUNKS; largeChunkY++){
            for(int innerChunkY=0; innerChunkY<CHUNK_WIDTH_IN_TILES; innerChunkY++){
                for(int largeChunkX=0; largeChunkX<GAME_WIDTH_IN_CHUNKS; largeChunkX++){
                    for(int innerChunkX=0; innerChunkX<CHUNK_WIDTH_IN_TILES; innerChunkX++){
                        Chunk chunk = chunkDisplayAndController.chunks[largeChunkY*GAME_WIDTH_IN_CHUNKS+largeChunkX];
                        gameView[largeChunkY*CHUNK_WIDTH_IN_TILES+innerChunkY][largeChunkX*CHUNK_WIDTH_IN_TILES+innerChunkX] = chunk.getTile(innerChunkX,innerChunkY);
                        gameBoardFrame.add(gameView[largeChunkY*CHUNK_WIDTH_IN_TILES+innerChunkY][largeChunkX*CHUNK_WIDTH_IN_TILES+innerChunkX]);
                    }
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
//                gameView[i][j].setColor(D%efaults.STARTING_COLOR);
//    //            tmpButton.setText(i+"");
//                gameBoardFrame.add(gameView[i][j]);
//            }
//        }
        System.out.println("END2");

        gameBoardFrame.setLayout(new GridLayout(chunkDisplayAndController.gameNumOfTilesWidth*CHUNK_WIDTH_IN_TILES,chunkDisplayAndController.gameNumOfTilesWidth*CHUNK_WIDTH_IN_TILES));

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
        playerX++;
        tryToMove();
        return true;
    }
    public boolean moveEast(){
        playerY++;
        return true;
    }
    public boolean moveSouth(){
        playerX--;
        return true;
    }
    public boolean moveWest(){
        playerY--;
        return true;
    }

    private void tryToMove(){
        Chunk playerInTile = chunkDisplayAndController.accessChunk(playerX,playerY);
        playerInTile.setAllChunkColor(Color.red);
    }


}
