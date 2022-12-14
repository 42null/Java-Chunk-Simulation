package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class UIControls implements ActionListener{
    Tile[] _controlsArray;

    TileGame game;

    JFrame uiFrame;
    JTextField t1;

    int widthInButtons = 3;


    UIControls(TileGame game) {
        this.game = game;
        uiFrame = new JFrame("GAME CONTROLS");
        t1 = new JTextField("");

//        GENERATE BUTTONS FROM LIST
        String[] buttonStringArray = new String[widthInButtons*widthInButtons];
        _controlsArray = new Tile[buttonStringArray.length];

        for(int i=0; i<buttonStringArray.length; i++){//CREATE STRINGS TO LATER TURN INTO BUTTONS
            buttonStringArray[i] = i+"";
        }
        for(int i = 0; i< _controlsArray.length; i++){//USE STRINGS TO TURN INTO BUTTONS
            Tile tmpButton = new Tile(buttonStringArray[i]);
            tmpButton.setPreferredSize(new Dimension(80,80));
            tmpButton.setColor(Defaults.STARTING_COLOR);
//            tmpButton.setText(i+"");
            _controlsArray[i] = tmpButton;
        }

        uiFrame.setLayout(new GridLayout((int) Math.sqrt(buttonStringArray.length),(int) Math.sqrt(buttonStringArray.length)));

//      ADD ALL BUTTONS TO FRAME
        for (int i = 0; i < _controlsArray.length; i++) {
            Tile tmpButton = _controlsArray[i];
            tmpButton.addActionListener(this);
            uiFrame.add(tmpButton);
        }

        _controlsArray[1*widthInButtons+0].setText("<-");
        _controlsArray[1*widthInButtons+0].setColor(Color.cyan);
        _controlsArray[0*widthInButtons+1].setText("^");
        _controlsArray[0*widthInButtons+1].setColor(Color.cyan);
        _controlsArray[2*widthInButtons+1].setText("v");
        _controlsArray[2*widthInButtons+1].setColor(Color.cyan);
        _controlsArray[1*widthInButtons+2].setText("->");
        _controlsArray[1*widthInButtons+2].setColor(Color.cyan);
        _controlsArray[1*widthInButtons+1].setText("T100");
        _controlsArray[1*widthInButtons+1].setColor(Color.gray);

        _controlsArray[2*widthInButtons+0].setText("A");
        _controlsArray[2*widthInButtons+2].setText("R");
        _controlsArray[0*widthInButtons+2].setText("T");


        Font font = new Font("Courier New", 1, 10);
        _controlsArray[2*widthInButtons+2].setFont(font);

        uiFrame.pack();
        uiFrame.setVisible(true);

        game.run();
    }

    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().toString();
//        System.out.println(this.getClass() + "   :   "+source);
        int boxNumber = Integer.parseInt(source.substring("com.company.Tile[".length(),source.indexOf(',')));

        Tile selectedButton = _controlsArray[boxNumber];

        if(!checkIfSpecialButton(selectedButton.getText())){
//            selectedButton.setColor(Color.BLUE);
        }
    }

    private boolean checkIfSpecialButton(String stringCheck){
        if(stringCheck=="<-"){
            game.moveWest();
            _controlsArray[0*widthInButtons+0].setText(game.getPlayerX()+","+game.getPlayerY());

        }else if(stringCheck=="^") {
            game.moveNorth();
            _controlsArray[0*widthInButtons+0].setText(game.getPlayerX()+","+game.getPlayerY());

        }else if(stringCheck=="v") {
            game.moveSouth();
            _controlsArray[0*widthInButtons+0].setText(game.getPlayerX()+","+game.getPlayerY());

        }else if(stringCheck=="->") {
            game.moveEast();
            _controlsArray[0*widthInButtons+0].setText(game.getPlayerX()+","+game.getPlayerY());
        }else if(stringCheck=="R"){
            for(int j = 100/*(int) Math.random() * 20*/; j>0; j--){
                int direction = (int) (Math.random()*4D);
                for(int i = 5/*(int) Math.random() * 20*/; i>0; i--){
                    //                try {
                    //                    Thread.sleep(1000);
                    //                } catch (InterruptedException e) {
                    //                    throw new RuntimeException(e);
                    //                }
                    switch(direction){
                        case 0:
                            game.moveNorth();
                            break;
                        case 1:
                            game.moveEast();
                            break;
                        case 2:
                            game.moveSouth();
                            break;
                        case 3:
                            game.moveWest();
                            break;
                        default:
                            break;
                    }
                }
            }
        }else if(stringCheck=="A"){
            for(int i=0; i < TileGame.GAME_WIDTH_IN_CHUNKS; i++){
                for(int j=0; j < TileGame.GAME_WIDTH_IN_CHUNKS; j++){
                    game.chunkDisplayAndController.addChunkFromIndex(i,j);
//                    game.chunkDisplayAndController.entityChunks.add(i*TileGame.GAME_WIDTH_IN_CHUNKS+j);
                }
            }
            game.chunkDisplayAndController.recalculateChunkLayers();
        }else if(stringCheck=="T"){
            game.chunkDisplayAndController.tickEntities();
        }else if(stringCheck=="T100"){
            for(int i = 0; i < 100; i++){
                game.chunkDisplayAndController.tickEntities();
                try {
                    Thread.sleep(Defaults.MS_BETWEEN_TICKS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            return false;
        }

//        recalculateChunkLayers();
        return true;
    }

}