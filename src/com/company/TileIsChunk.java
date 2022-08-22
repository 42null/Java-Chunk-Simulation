package com.company;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TileIsChunk implements ActionListener {

    int gameNumOfTilesWidth;
    short chunkWidthInTiles;

    JFrame gameChunkFrame;

    JTextField t1;

    LinkedList<Integer> lazyChunks = new LinkedList<>();
    LinkedList<Integer> entityChunks = new LinkedList<>();

    Tile[] tileArray;
    Chunk[] chunks;


    TileIsChunk(int gameNumOfTilesWidth, short chunkWidthInTiles) {
        this.gameNumOfTilesWidth = gameNumOfTilesWidth;
        this.chunkWidthInTiles = chunkWidthInTiles;

        gameChunkFrame = new JFrame("Tile is Chunk");
        t1 = new JTextField("");


//        GENERATE BUTTONS FROM LIST
        String[] buttonStringArray = new String[this.gameNumOfTilesWidth * this.gameNumOfTilesWidth];
        tileArray = new Tile[buttonStringArray.length];
        chunks = new Chunk[buttonStringArray.length];

        for(int i=0; i<buttonStringArray.length; i++){//CREATE STRINGS TO LATER TURN INTO BUTTONS
            buttonStringArray[i] = i+"";
        }
        for(int i = 0; i< tileArray.length; i++){//USE STRINGS TO TURN INTO BUTTONS
            Tile tmpButton = new Tile(buttonStringArray[i]);
//            tmpButton.setPreferredSize(new Dimension(70,70));
            tmpButton.setPreferredSize(new Dimension(60,60));
            tmpButton.setBackground(Defaults.STARTING_COLOR);
            tmpButton.setText(i+"");
            tileArray[i] = tmpButton;
            chunks[i] = new Chunk(this.chunkWidthInTiles, Color.GREEN);
        }



        gameChunkFrame.setLayout(new GridLayout((int) Math.sqrt(buttonStringArray.length),(int) Math.sqrt(buttonStringArray.length)));

//      ADD ALL BUTTONS TO FRAME
        for (int i = 0; i < tileArray.length; i++) {
            Tile tmpButton = tileArray[i];
            tmpButton.addActionListener(this);
            gameChunkFrame.add(tmpButton);
        }

        gameChunkFrame.pack();
        gameChunkFrame.setVisible(true);
    }

    /**
     * Select a constant number of locations from each entity-processing chunk and call the randomTick method on each selected tile.
     */
    public void runRandomTicks(final int tilesPerChunk){
        for( int chunkLocationInStorage: entityChunks){
            for(int i=0; i<tilesPerChunk; i++){
                chunks[chunkLocationInStorage].playTiles[(int)Math.round(Math.random()*this.chunkWidthInTiles)][(int)Math.round(Math.random()*this.chunkWidthInTiles)].randomTick();//See if I can just truncate for speed.
            }
        }
    }


    public void displayCurrentPlayersChunk(){

    }

    public void updateChunkList(){
        this.updateChunkList(lazyChunks.size()-1);
    }
    public void updateChunkList(int numberOfChunksToKeepTotal){//return boolean?
        for(int i = 0; i < Math.min(Math.max(lazyChunks.size()-numberOfChunksToKeepTotal,0), 9); i++){
            int lazyChunkToRemoveNum = lazyChunks.removeFirst();
            tileArray[lazyChunkToRemoveNum].setBackground(Color.pink);
            chunks[lazyChunkToRemoveNum].setAllChunkColor(Color.pink);
        }
        System.out.println("Number of chunks within lazyChunks = "+ lazyChunks.size());
    }

    private void addChunkToLazyProcessing(Tile tile){
        Integer valueToAdd = Integer.valueOf(tile.getText());
        try{
            lazyChunks.removeFirstOccurrence(valueToAdd);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        tile.setBackground(Color.orange);
        chunks[Integer.parseInt(tile.getText())].setAllChunkColor(Color.orange);
        lazyChunks.add(valueToAdd);
        recalculateChunkLayers();
    }

    private void addChunkToEntityProcessing(Tile tile){
        Integer valueToAdd = Integer.valueOf(tile.getText());
        try{
            entityChunks.removeFirstOccurrence(valueToAdd);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        tile.setBackground(Color.yellow);
        chunks[Integer.parseInt(tile.getText())].setAllChunkColor(Color.yellow);
        lazyChunks.add(valueToAdd);
    }

    private void removeChunkFromLazyProcessing(Tile tile){
        if(lazyChunks.removeFirstOccurrence(Integer.parseInt(tile.getText()))){
            tile.setBackground(Color.pink);
            System.out.println("Successfully removed chunk from lazy");
        }else{
            System.out.println("Attempted remove from lazy processing did not work");
        }
    }

     public void recalculateChunkLayers(){
        for(int i = 0; i< lazyChunks.size(); i++){
            int tileUnderConsideration = lazyChunks.get(i);
//            if(entityTiles.contains(tileUnderConsideration-1) && entityTiles.contains(tileUnderConsideration+1) && entityTiles.contains(tileUnderConsideration-width) && entityTiles.contains(tileUnderConsideration+width)){//TODO: Make much more efficient
            if(lazyChunks.contains(tileUnderConsideration - gameNumOfTilesWidth -1) && lazyChunks.contains(tileUnderConsideration - gameNumOfTilesWidth) && lazyChunks.contains(tileUnderConsideration - gameNumOfTilesWidth +1) && lazyChunks.contains(tileUnderConsideration-1) && lazyChunks.contains(tileUnderConsideration+1) && lazyChunks.contains(tileUnderConsideration + gameNumOfTilesWidth -1) && lazyChunks.contains(tileUnderConsideration + gameNumOfTilesWidth) && lazyChunks.contains(tileUnderConsideration + gameNumOfTilesWidth +1)){//TODO: Make much more efficient
                removeChunkFromLazyProcessing(tileArray[tileUnderConsideration]);
                addChunkToEntityProcessing(tileArray[tileUnderConsideration]);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().toString();
//        System.out.println(this.getClass() + "   :   "+source);
        int boxNumber = Integer.parseInt(source.substring("com.company.Tile[".length(),source.indexOf(',')));
        System.out.println("Manually set chunk '" + boxNumber + "' to lazy processing");

        Tile selectedButton = tileArray[boxNumber];
//        System.out.println("X = "+boxNumber % gameNumOfTilesWidth);//X
//        System.out.println("Y = "+boxNumber / gameNumOfTilesWidth);//Y
        addChunkToLazyProcessing(selectedButton);

        updateChunkList(40);
    }
}