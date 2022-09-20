package com.company;

import com.company.tileTypes.GameTile;

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
            tmpButton.setColor(Defaults.STARTING_COLOR);
            tmpButton.setText(i+"");
            tileArray[i] = tmpButton;
            chunks[i] = new Chunk(this.gameNumOfTilesWidth,  this.chunkWidthInTiles, i, Color.GREEN);
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
        for(int chunkLocationInStorage: entityChunks){
            for(int i=0; i<tilesPerChunk; i++){
                if(chunks[chunkLocationInStorage].entities.size() == 0){
//                    chunks[chunkLocationInStorage].setAllChunkColor(Color.GRAY);
                }else{
                    chunks[chunkLocationInStorage].setAllChunkColor(Color.ORANGE);
                }
                chunks[chunkLocationInStorage].playTiles[(int)Math.round(Math.random()*(this.chunkWidthInTiles-1))][(int)Math.round(Math.random()*(this.chunkWidthInTiles-1))].randomTick();//See if I can just truncate for speed.
            }
        }
    }

    public void tickEntities(){
        for(int chunkLocationInStorage: entityChunks){
            chunks[chunkLocationInStorage].tickEntities();
        }
    }

    public GameTile getGameTile(int x, int y){
        try{
            return chunks[(y/chunkWidthInTiles)*gameNumOfTilesWidth+x/chunkWidthInTiles].playTiles[x%chunkWidthInTiles][y%chunkWidthInTiles];
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public void setGameTileColor(int x, int y, Color newColor){
        chunks[(y/chunkWidthInTiles)*gameNumOfTilesWidth+x/chunkWidthInTiles].playTiles[x%chunkWidthInTiles][y%chunkWidthInTiles].setColor(newColor);
    }
    public void setGameTileColorTemp(int x, int y, Color tempColor){
        chunks[(y/chunkWidthInTiles)*gameNumOfTilesWidth+x/chunkWidthInTiles].playTiles[x%chunkWidthInTiles][y%chunkWidthInTiles].setBackground(tempColor);
    }

    public void updateChunkList(){
        this.updateChunkList(lazyChunks.size()-1);
    }
    public void updateChunkList(int numberOfChunksToKeepTotal){//return boolean?
        for(int i = 0; i < Math.min(Math.max(lazyChunks.size()-numberOfChunksToKeepTotal,0), 9); i++){
            int lazyChunkToRemoveNum = lazyChunks.getFirst();//TODO: GetFirstAndRemoveFirst make more efficent from processing
//            int lazyChunkToRemoveNum = lazyChunks.removeFirst();
            removeChunkFromLazyProcessing(tileArray[lazyChunkToRemoveNum]);//AlsoDoesEntity
        }
//        System.out.println("Number of chunks within lazyChunks = "+ lazyChunks.size());
    }

    /**
     *
     * @param tile
     * @return status byte 0 for reserved, 1 for could not get value from getText(), 2 for requested chunk/tile does not exist, 3 for did not already exist, 4 for already existed,
     */
    private byte addChunkToLazyProcessing(Tile tile){//TODO: Make not move location in array if already existing?  //TODO: Use a nibble instead
        byte returnStatus;
        int valueToAdd = -1;
        try{
            valueToAdd = Integer.parseInt(tile.getText());
        }catch(NullPointerException e){
//            e.printStackTrace();
            return 1;
        }
        if(lazyChunks.removeFirstOccurrence(valueToAdd)){
            returnStatus = 4;
        }else{
            returnStatus = 3;
        }

        try{
            lazyChunks.add(valueToAdd);//TODO: Combine with above and setting returnstatus
            if(returnStatus==4){
//                chunks[valueToAdd].setAllChunkColor(Color.orange);
            }else{
                chunks[valueToAdd].setAllChunkColor(Color.orange);
                tile.setColor(Color.orange);
            }
        }catch(ArrayIndexOutOfBoundsException e){
//            e.printStackTrace();
            return 2;
        }
        recalculateChunkLayers();
//        System.out.println("Lazy.size = "+lazyChunks.size());
        updateChunkList(9);
//        System.out.println("Lazy.size = "+lazyChunks.size());

        return returnStatus;
    }

    private void addChunkToEntityProcessing(Tile tile){
        Integer valueToAdd = Integer.valueOf(tile.getText());
        try{
            if(!entityChunks.removeFirstOccurrence(valueToAdd)){
                tile.setColor(Color.yellow);
                chunks[Integer.parseInt(tile.getText())].setAllChunkColor(Color.yellow);
            }
            entityChunks.add(valueToAdd);
        }catch(ArrayIndexOutOfBoundsException e){
//            e.printStackTrace();
        }
    }

    private void removeChunkFromLazyProcessing(Tile tile){
        int tileNum = Integer.parseInt(tile.getText());
        if(lazyChunks.removeFirstOccurrence(tileNum)){
            tile.setColor(Color.pink);
            chunks[tileNum].setAllChunkColor(Color.pink);
//            System.out.println("Successfully removed chunk from lazy");
        }else{
//            System.out.println("Attempted remove from lazy processing did not work");
        }
        if(entityChunks.removeFirstOccurrence(tileNum)){
            tile.setColor(Color.pink);
//            System.out.println("Successfully removed chunk from entity");
            chunks[tileNum].setAllChunkColor(Color.pink);
        }
    }

    public boolean moveEntityToChunk(Entity entity, int existingChunkNum, int newChunkNum){
        try{
            this.chunks[existingChunkNum].entities.remove(entity);//TODO: Move to look at entity chunks
            this.chunks[newChunkNum].entities.add(entity);
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public int[] getChunkNumXYFromGameXY(int x, int y){
        return new int[] {x/chunkWidthInTiles,y/chunkWidthInTiles};
    }
    public int getChunkIndexFromXY(int x, int y){
        return (y/this.chunkWidthInTiles)*this.gameNumOfTilesWidth+x/this.chunkWidthInTiles;
    }
    public Chunk getChunk(int x, int y){
        return chunks[(y/this.chunkWidthInTiles)*this.gameNumOfTilesWidth+x/this.chunkWidthInTiles];
    }

    public Chunk getChunkFromIndex(int index){
        return chunks[index];
    }

    public boolean addChunkFromIndex(int x, int y){//TODO: Rename
        try {
//            System.out.println("x = " + x + ", y = " + y);
            if (this.addChunkToLazyProcessing(tileArray[y * this.gameNumOfTilesWidth + x]) > 2) {
            } else {
                return false;
            }
        }catch(Exception e){

        }
        return true;
    }


     public void recalculateChunkLayers(){
        for(int i = 0; i< lazyChunks.size(); i++){
            int tileUnderConsideration = lazyChunks.get(i);
//            if(entityTiles.contains(tileUnderConsideration-1) && entityTiles.contains(tileUnderConsideration+1) && entityTiles.contains(tileUnderConsideration-width) && entityTiles.contains(tileUnderConsideration+width)){//TODO: Make much more efficient
            if(lazyChunks.contains(tileUnderConsideration - gameNumOfTilesWidth -1) &&
               lazyChunks.contains(tileUnderConsideration - gameNumOfTilesWidth) &&
               lazyChunks.contains(tileUnderConsideration - gameNumOfTilesWidth +1) &&
               lazyChunks.contains(tileUnderConsideration-1) &&
               lazyChunks.contains(tileUnderConsideration+1) &&
               lazyChunks.contains(tileUnderConsideration + gameNumOfTilesWidth -1) &&
               lazyChunks.contains(tileUnderConsideration + gameNumOfTilesWidth) &&
               lazyChunks.contains(tileUnderConsideration + gameNumOfTilesWidth +1)){//TODO: Make much more efficient

//                removeChunkFromLazyProcessing(tileArray[tileUnderConsideration]);
                addChunkToEntityProcessing(tileArray[tileUnderConsideration]);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().toString();
//        System.out.println(this.getClass() + "   :   "+source);
        int boxNumber = Integer.parseInt(source.substring("com.company.Tile[".length(),source.indexOf(',')));
//        System.out.println("Manually set chunk '" + boxNumber + "' to lazy processing");

        Tile selectedButton = tileArray[boxNumber];
//        System.out.println("X = "+boxNumber % gameNumOfTilesWidth);//X
//        System.out.println("Y = "+boxNumber / gameNumOfTilesWidth);//Y
        addChunkToLazyProcessing(selectedButton);
    }
}