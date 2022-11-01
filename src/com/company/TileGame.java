package com.company;

import com.company.entities.BasicEntity;
import com.company.tileTypes.GameTile;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TileGame implements Runnable  {
    final static int GAME_WIDTH_IN_CHUNKS = 24;
    final static short CHUNK_WIDTH_IN_TILES = 6;

    final static int TILES_PER_CHUNK_PER_RANDOM_TICK = 0;//3;

    TileIsChunk chunkDisplayAndController;
    GameTile[][] gameView;

    JFrame gameBoardFrame;
    JTextField t1;

    private short msBetweenTicks = Defaults.MS_BETWEEN_TICKS;

    int playerX = 0;
    int playerY = 0;

    @Override
    public void run(){
        //Run everything in a tick
        while(true){
            try {
                Thread.sleep(this.msBetweenTicks);
//                byte direction = (byte) (Math.random()*4D);
//
//                switch(direction){
//                    case 0:
//                        moveNorth();
//                        break;
//                    case 1:
//                        moveEast();
//                        break;
//                    case 2:
//                        moveSouth();
//                        break;
//                    case 3:
//                        moveWest();
//                        break;
//                    default:
//                        break;
//                }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            chunkDisplayAndController.runRandomTicks(TILES_PER_CHUNK_PER_RANDOM_TICK);
//            chunkDisplayAndController.tickTileElements();
//            System.out.println("Going to tick entities");
            try{
                chunkDisplayAndController.tickEntities();
            }catch(ConcurrentModificationException e){
                e.printStackTrace();
            }
        }
    }

    public TileGame(){
        //Create Board Chunks
        chunkDisplayAndController = new TileIsChunk(GAME_WIDTH_IN_CHUNKS, CHUNK_WIDTH_IN_TILES);
        BasicEntity.setSharedStatics(chunkDisplayAndController);
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
//                        gameView[largeChunkY*CHUNK_WIDTH_IN_TILES+innerChunkY][largeChunkX*CHUNK_WIDTH_IN_TILES+innerChunkX] = chunk.getTile(innerChunkX,innerChunkY);
//                        gameBoardFrame.add(gameView[largeChunkY*CHUNK_WIDTH_IN_TILES+innerChunkY][largeChunkX*CHUNK_WIDTH_IN_TILES+innerChunkX]);
                        int gameViewX = largeChunkY*CHUNK_WIDTH_IN_TILES+innerChunkY, gameViewY = largeChunkX*CHUNK_WIDTH_IN_TILES+innerChunkX;
                        gameView[gameViewX][gameViewY] = chunk.getTile(innerChunkX,innerChunkY);
                        gameBoardFrame.add(gameView[gameViewX][gameViewY]);
                        gameView[gameViewX][gameViewY].addActionListener(chunk);
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
        checkAndMoveIfPossible(this.playerX, this.playerY-1);

        return true;
    }
    public boolean moveEast(){
        checkAndMoveIfPossible(this.playerX+1, this.playerY);

        return true;
    }
    public boolean moveSouth(){
        checkAndMoveIfPossible(this.playerX, this.playerY+1);

        return true;
    }
    public boolean moveWest(){
        checkAndMoveIfPossible(this.playerX-1, this.playerY);
        return true;
    }

    private void checkAndMoveIfPossible(int newX, int newY){
        //Make sure not out of bounds by checking if chunk exists
        try{
            try{
                if(chunkDisplayAndController.getGameTile(newX, newY).landable){
                    setPlayerTileColor(chunkDisplayAndController.getGameTile(playerX,playerY).getColor());
        //            setPlayerTileColor(Color.red);
                    playerX = newX;
                    playerY = newY;
                    colorPlayerChunk();//TODO: Make more efficent?
                    setPlayerTileColorTemp(Defaults.PLAYER_COLOR);
                }
            }catch(NullPointerException e){
//                e.printStackTrace();
            }
        }catch(ArrayIndexOutOfBoundsException e){
//            e.printStackTrace();
//            System.out.println("Sorry but that space does not exist.");
        }
    }

    private boolean colorPlayerChunk(){
        int chunkXY[] = chunkDisplayAndController.getChunkNumXYFromGameXY(playerX, playerY);
        Chunk playerInTile = chunkDisplayAndController.getChunk(playerX, playerY);
//        playerInTile.setAllChunkColor(Color.red);
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                chunkDisplayAndController.addChunkFromIndex(chunkXY[0]+i,chunkXY[1]+j);
            }
        }

        return true;
    }

    public void setPlayerTileColor(Color newColor){
        chunkDisplayAndController.setGameTileColor(getPlayerX(),getPlayerY(),newColor);
    }

    public void setPlayerTileColorTemp(Color tempColor){
        chunkDisplayAndController.setGameTileColorTemp(getPlayerX(),getPlayerY(),tempColor);
    }
}
