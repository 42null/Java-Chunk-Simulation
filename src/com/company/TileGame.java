package com.company;

import javax.swing.*;
import java.awt.*;

public class TileGame implements Runnable  {
    final static int CHUNK_WIDTH_IN_TILES = 5;
    final static int GAME_WIDTH_IN_CHUNKS = 6;

    final static int TILES_PER_CHUNK_PER_RANDOM_TICK = 3;

    TileIsChunk chunkDisplayAndController;

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

//        GENERATE BUTTONS FROM LIST
//        String[] buttonStringArray = new String[chunkDisplayAndController.gameNumOfTilesWidth*chunkDisplayAndController.];
//        _controlsArray = new Tile[buttonStringArray.length];
//
//        for(int i=0; i<buttonStringArray.length; i++){//CREATE STRINGS TO LATER TURN INTO BUTTONS
//            buttonStringArray[i] = i+"";
//        }
//        for(int i = 0; i< _controlsArray.length; i++){//USE STRINGS TO TURN INTO BUTTONS
//            Tile tmpButton = new Tile(buttonStringArray[i]);
//            tmpButton.setPreferredSize(new Dimension(60,60));
//            tmpButton.setBackground(Defaults.STARTING_COLOR);
////            tmpButton.setText(i+"");
//            _controlsArray[i] = tmpButton;
//        }
//
//        gameBoardFrame.setLayout(new GridLayout((int) Math.sqrt(buttonStringArray.length),(int) Math.sqrt(buttonStringArray.length)));
//
////      ADD ALL BUTTONS TO FRAME
//        for (int i = 0; i < _controlsArray.length; i++) {
//            Tile tmpButton = _controlsArray[i];
//            tmpButton.addActionListener(this);
//            gameBoardFrame.add(tmpButton);
//        }
//
//        gameBoardFrame.pack();
//        gameBoardFrame.setVisible(true);
//
//
//
//
//
//
//        chunkDisplayAndController.displayCurrentPlayersChunk();
//
        
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
