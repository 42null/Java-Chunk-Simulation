package com.company;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TileIsChunk implements ActionListener {

    int width = 15;

    JFrame gameBoardFrame;

    JTextField t1;
    JLabel l;

    int playerX = 6;
    int playerY = 6;

//    LinkedHashMap<Integer, Integer> entityTiles = new LinkedHashMap<Integer, Integer>();
//    Set entrySet = entityTiles.entrySet();
//    Iterator entityTileIterator = entrySet.iterator();
    LinkedList<Integer> entityTiles = new LinkedList<>();

    public boolean flag_doFaceDetection = false;
    public boolean flag_doFlippedStream = false;
    public boolean flag_doFlippedStreamVertical = false;
    public boolean flag_doGreyFrameProcessing = false;
    public boolean flag_doInvertedColors = false;
    public boolean flag_doHistogram = true;
    public int num_frameColor = 0;


    MineButton[] _buttonArray;
    Chunk[] chunks;

    TileIsChunk() {

        JFXPanel jfxPanel = new JFXPanel();
//        final Image PIZZA = new Image("http://icons.iconarchive.com/icons/aha-soft/desktop-buffet/128/Pizza-icon.png");
//        final Image CAKE = new Image("http://icons.iconarchive.com/icons/aha-soft/desktop-buffet/128/Piece-of-cake-icon.png");
//        try {
////            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//        } catch (Exception e) {
//            System.out.println("Look and Feel not set");
//        }

        gameBoardFrame = new JFrame("Tile is Chunk");
        t1 = new JTextField("");


        MineButton testMineButton = new MineButton("TEST");

//        GENERATE BUTTONS FROM LIST
        String[] buttonStringArray = new String[width*width];
        _buttonArray = new MineButton[buttonStringArray.length];
        chunks = new Chunk[buttonStringArray.length];

        for(int i=0; i<buttonStringArray.length; i++){//CREATE STRINGS TO LATER TURN INTO BUTTONS
            buttonStringArray[i] = i+"";
        }
        for(int i = 0; i< _buttonArray.length; i++){//USE STRINGS TO TURN INTO BUTTONS
            MineButton tmpButton = new MineButton(buttonStringArray[i]);
//            tmpButton.setPreferredSize(new Dimension(70,70));
            tmpButton.setPreferredSize(new Dimension(60,60));
            tmpButton.setBackground(Defaults.STARTING_COLOR);
            tmpButton.setText(i+"");
            _buttonArray[i] = tmpButton;
            chunks[i] = new Chunk();
        }



        gameBoardFrame.setLayout(new GridLayout((int) Math.sqrt(buttonStringArray.length),(int) Math.sqrt(buttonStringArray.length)));
//        frame.add(t1);
//        frame.add(l);
//        frame.add(chk_togFace);


//      ADD ALL BUTTONS TO FRAME
        for (int i = 0; i < _buttonArray.length; i++) {
            MineButton tmpButton = _buttonArray[i];
            tmpButton.addActionListener(this);
//            tmpButton.setAction();
//            tmpButton.updateImages(PIZZA,CAKE);
//            tmpButton.setHideActionText(false);
//            tmpButton.setText(i+"*");
//            tmpButton.hashCode();
            gameBoardFrame.add(tmpButton);
        }


//        chk_togFace.addActionListener(this);
        gameBoardFrame.pack();
        gameBoardFrame.setVisible(true);
    }

    private void updateChunkList(){
        this.updateChunkList(entityTiles.size()-1);
    }
    private void updateChunkList(int numberOfChunksToKeepTotal){//return boolean?
//        System.out.println("entityTileIterator.next() = "+entityTileIterator.next());
//        Set entrySet2 = entityTiles.entrySet();
//        Iterator entityTileIterator2 = entrySet2.iterator();
//        if(entityTileIterator2.hasNext()){
////            _buttonArray[[1]];
//            System.out.println("entityTileIterator.next() = "+entityTileIterator.next());
//            entityTileIterator.remove();
//        }
        for(int i=0; i < Math.min(Math.max(entityTiles.size()-2-numberOfChunksToKeepTotal,0), 9); i++){
            _buttonArray[entityTiles.removeFirst()].setBackground(Color.orange);
            _buttonArray[2*width+0].setText(entityTiles.size()+"");
            _buttonArray[2*width+0].setBackground(Color.red);
        }
    }

    private boolean checkIfSpecialButton(String stringCheck){
        if(stringCheck=="<-"){
            playerX--;
        }else if(stringCheck=="^") {
            playerY--;
        }else if(stringCheck=="v") {
            playerY++;
        }else if(stringCheck=="->") {
            playerX++;
        }else if(stringCheck=="T"){
            updateChunkList();
            recalculateChunkLayers();
        }else{
            return false;
        }

    //        _buttonArray[playerY*width+playerX].updateState(1);

        for(int i=Math.max(0, playerY-1); i<Math.min(width, playerY+2); i++){//Y
            for(int j=Math.max(0, playerX-1); j<Math.min(width, playerX+2); j++){//X
                    /* -1 for not default;
                        0 for remove from active
                        1 for added to active
                    */
                try{
                    entityTiles.remove(Integer.valueOf(i * width + j));
                }catch(Exception exception){
                    exception.printStackTrace();
                }
                addTileToProcessing(_buttonArray[i*width+j]);
                _buttonArray[i*width+j].setBackground(Color.magenta);

                // Getting size of Map using size() method

    //                // Using containsKey() method to check for a key
    //                System.out.println("Contains key 'two'? "
    //                        + lhm.containsKey("two"));
    //
    //                // Using containsKey() method to check for a value
    //                System.out.println(
    //                        "Contains value 'practice.geeks"
    //                                + "forgeeks.org'? "
    //                                + lhm.containsValue("practice"
    //                                + ".geeksforgeeks.org"));
            }
        }
        recalculateChunkLayers();
        return true;
    }

    private void addTileToProcessing(MineButton tile){
        Integer valueToAdd = Integer.valueOf(tile.getText());
        try{
            entityTiles.removeFirstOccurrence(valueToAdd);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        tile.setBackground(Color.PINK);
        entityTiles.add(valueToAdd);
    }

    private void recalculateChunkLayers(){
        for(int i=0; i<entityTiles.size(); i++){
            int tileUnderConsideration = entityTiles.get(i);
//            if(entityTiles.contains(tileUnderConsideration-1) && entityTiles.contains(tileUnderConsideration+1) && entityTiles.contains(tileUnderConsideration-width) && entityTiles.contains(tileUnderConsideration+width)){//TODO: Make much more efficient
            if(entityTiles.contains(tileUnderConsideration-width-1) && entityTiles.contains(tileUnderConsideration-width) && entityTiles.contains(tileUnderConsideration-width+1) && entityTiles.contains(tileUnderConsideration-1) && entityTiles.contains(tileUnderConsideration+1) && entityTiles.contains(tileUnderConsideration+width-1) && entityTiles.contains(tileUnderConsideration+width) && entityTiles.contains(tileUnderConsideration+width+1)){//TODO: Make much more efficient
                _buttonArray[tileUnderConsideration].setBackground(Color.YELLOW);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
//        public void actionPerformed(ActionEvent e) {
//        String s = e.getID()+"";// getActionCommand();
        String source = e.getSource().toString();
        int boxNumber = Integer.parseInt(source.substring("com.company.MineButton[".length(),source.indexOf(',')));
//        l.setText(s);
        System.out.println("User clicked '" + boxNumber + "'");

        MineButton selectedButton = _buttonArray[boxNumber];
        System.out.println("X = "+boxNumber % width);//X
        System.out.println("Y = "+boxNumber / width);//Y

        selectedButton.setBackground(Color.MAGENTA);

        if(!checkIfSpecialButton(selectedButton.getText())){
            selectedButton.setBackground(Color.BLUE);
            addTileToProcessing(selectedButton);
        }
        System.out.println("Everything in entityTiles: "+entityTiles);

        _buttonArray[playerY*width+playerX].setBackground(Color.GREEN);
//        updateChunkList(5);
//        updateChunkList(999);
        updateChunkList(30);
    }
}