package com.company.entities;

import com.company.Defaults;
import com.company.TileIsChunk;

import java.awt.*;

public class AntAsPartOfColony extends BasicEntity implements Entity {
    private static TileIsChunk _sharedChunks;
    public static int id;

    private static boolean showCollisions = true;

//    static void setSharedStatics(ArrayList<Entity> oneTimeSet, TileIsChunk oneTimeSet2){//TODO: Make more secure and come up with better names
    public static void setSharedStatics(TileIsChunk oneTimeSet2){//TODO: Make more secure and come up with better names
        if(_sharedChunks == null){
            _sharedChunks = oneTimeSet2;
        }
    }

    public AntAsPartOfColony(int id, int x, int y, Color color){
        super(id, x, y, color);
    }

    @Override
    public void onTick(){
//        byte direction = (byte) 2;
        byte direction = (byte) (Math.random()*4D);
        switch(direction){
            case 0:
                tryToMove(this.getX(),this.getY()+1);
                break;
            case 1:
                tryToMove(this.getX()+1,this.getY());
                break;
            case 2:
                tryToMove(this.getX(),this.getY()-1);
                break;
            case 3:
                tryToMove(this.getX()-1,this.getY());
                break;
            default:
                break;
        }

    }

//    @Override
//    public boolean tryToMove(int newX, int newY){//TODO: Change to shorts if only calculating within a chunk
////        //TODO: See if more efficient way to check for collisions
////        int[] test = new int[] {newX,newY};
////        for(Entity entity: _sharedEntities){
////            if(entity.getXY() == test){
////                System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
////                return false;
////            }
////        }
////        _sharedChunks.getGameTile(xy[0],xy[1]).setBackground(_sharedChunks.getGameTile(xy[0],xy[1]).getColor());
////
////        try{
////            _sharedChunks.getGameTile(newX,newY).setBackground(Color.red);
////            this.xy = new int[]{newX,newY};
////            _sharedChunks.getGameTile(xy[0],xy[1]).setBackground(this.color);
////        }catch(NullPointerException e){
//////            e.printStackTrace();
////        }
////        _sharedChunks.setGameTileColorTemp(this.getX(), this.getY(), Colorg.MAGENTA);
////        Make sure not out of bounds by checking if chunk exists
//        try{
//            int existingChunkNum = _sharedChunks.getChunkIndexFromXY(xy[0], xy[1]);
//            if(_sharedChunks.getGameTile(newX, newY).landable) {
//
//                int newChunkNum = _sharedChunks.getChunkIndexFromXY(newX, newY);
////                if (existingChunkNum != newChunkNum && _sharedChunks.entityChunks.contains(newChunkNum)) {//Check if movement is within the chunk
//                if(_sharedChunks.entityChunks.contains(newChunkNum)){//Check if movement is within the chunk
//
//                    boolean entityNotAlreadyInPosition = true;
//                    for(Entity entity : _sharedChunks.getChunk(newX,newY).entities){
//                        if(entity.getX()==newX && entity.getY()==newY){
//                            entityNotAlreadyInPosition = false;
//                            break;
//                        }
//                    }
//
//                    if(entityNotAlreadyInPosition){//Check that other entity does not already exist there //TODO: Save gameTile so not get over and over again
//                    //Send to other chunk
//                        _sharedChunks.moveEntityToChunk(this, existingChunkNum, newChunkNum);//Does not do any extra loading, still need to add as part of a more complex system.
//                        _sharedChunks.getGameTile(this.getX(), this.getY()).updateColorInDisplay();
//                        _sharedChunks.setGameTileColor(this.getX(), this.getY(), Color.cyan);
//                        xy[0] = newX;
//                        xy[1] = newY;
//                        _sharedChunks.setGameTileColorTemp(this.getX(), this.getY(), Color.MAGENTA);
//                    }else if(this.showCollisions){
//                        _sharedChunks.setGameTileColorTemp(this.getX(), this.getY(), new Color(165,30,0));
//                    }
//                }else if(Defaults.entitiesLoadChunks){
//                    _sharedChunks.addChunkToEntityProcessingNextTick(newChunkNum);
//                }
//            }
//        }catch(NullPointerException e){
////            System.out.println("Sorry but that space does not exist.");
////            e.printStackTrace();
//        }
//        return true;
//    }

    @Override
    public boolean teleport(int newX, int newY){
        return false;
    }

}
