package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javafx.embed.swing.JFXPanel;

import javax.swing.JFrame;

public class UIControls implements ActionListener {

    final static int CHUNK_SIZE = 5;


    JFrame uiFrame;
    JTextField t1;

    int widthInButtons = 3;

    TileGame game = new TileGame();

    MineButton[] _buttonArray;

    UIControls() {

        uiFrame = new JFrame("GAME CONTROLS");
        t1 = new JTextField("");

//        GENERATE BUTTONS FROM LIST
        String[] buttonStringArray = new String[widthInButtons*widthInButtons];
        _buttonArray = new MineButton[buttonStringArray.length];

        for(int i=0; i<buttonStringArray.length; i++){//CREATE STRINGS TO LATER TURN INTO BUTTONS
            buttonStringArray[i] = i+"";
        }
        for(int i = 0; i< _buttonArray.length; i++){//USE STRINGS TO TURN INTO BUTTONS
            MineButton tmpButton = new MineButton(buttonStringArray[i]);
            tmpButton.setPreferredSize(new Dimension(60,60));
            tmpButton.setBackground(Defaults.STARTING_COLOR);
            tmpButton.setText(i+"");
            _buttonArray[i] = tmpButton;
        }

        uiFrame.setLayout(new GridLayout((int) Math.sqrt(buttonStringArray.length),(int) Math.sqrt(buttonStringArray.length)));

//      ADD ALL BUTTONS TO FRAME
        for (int i = 0; i < _buttonArray.length; i++) {
            MineButton tmpButton = _buttonArray[i];
            tmpButton.addActionListener(this);
            uiFrame.add(tmpButton);
        }

        _buttonArray[1*widthInButtons+0].setText("<-");
        _buttonArray[1*widthInButtons+0].setBackground(Color.cyan);
        _buttonArray[0*widthInButtons+1].setText("^");
        _buttonArray[0*widthInButtons+1].setBackground(Color.cyan);
        _buttonArray[2*widthInButtons+1].setText("v");
        _buttonArray[2*widthInButtons+1].setBackground(Color.cyan);
        _buttonArray[1*widthInButtons+2].setText("->");
        _buttonArray[1*widthInButtons+2].setBackground(Color.cyan);
        _buttonArray[1*widthInButtons+1].setText("T");
        _buttonArray[1*widthInButtons+1].setBackground(Color.gray);

        uiFrame.pack();
        uiFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().toString();
        int boxNumber = Integer.parseInt(source.substring("com.company.MineButton[".length(),source.indexOf(',')));

        MineButton selectedButton = _buttonArray[boxNumber];


        if(!checkIfSpecialButton(selectedButton.getText())){
            selectedButton.setBackground(Color.BLUE);
        }
    }


    private boolean checkIfSpecialButton(String stringCheck){
        if(stringCheck=="<-"){
            game.moveWest();
        }else if(stringCheck=="^") {
            game.moveNorth();
        }else if(stringCheck=="v") {
            game.moveSouth();
        }else if(stringCheck=="->") {
            game.moveEast();
        }else if(stringCheck=="T"){
//            updateChunkList();
//            recalculateChunkLayers();
        }else{
            return false;
        }

//        recalculateChunkLayers();
        return true;
    }

}