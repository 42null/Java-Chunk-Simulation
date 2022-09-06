package com.company;

import java.awt.*;
import java.util.ArrayList;

public class BasicEntity implements Entity {

    private static ArrayList<Entity> _sharedEntities;
    private static TileIsChunk _sharedChunks;

    public static int id;
    private int[] xy = new int[]{0,0};
    private Color color = Defaults.ERROR_COLOR;

    static void setSharedStatics(ArrayList<Entity> oneTimeSet, TileIsChunk oneTimeSet2){//TODO: Make more secure and come up with better names
        if(_sharedEntities == null){
            _sharedEntities = oneTimeSet;
        }
        if(_sharedChunks == null){
            _sharedChunks = oneTimeSet2;
        }
    }

    public BasicEntity(int id, int x, int y, Color color){
        this.id = id;
        this.xy = new int[] {x,y};
        this.color = color;
    }

    @Override
    public int getX(){return xy[0];}

    @Override
    public int getY(){return xy[1];}

    @Override
    public int[] getXY(){return new int[0];}

    @Override
    public boolean setColor(Color newColor){
        this.color = newColor;
        return true;
    }

    @Override
    public void onTick(){

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

    @Override
    public boolean tryToMove(int newX, int newY){
        //TODO: See if more efficient way to check for collisions
        int[] test = new int[] {newX,newY};
        for(Entity entity: _sharedEntities){
            if(entity.getXY() == test){
                System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
                return false;
            }
        }
        _sharedChunks.getGameTile(xy[0],xy[1]).setBackground(_sharedChunks.getGameTile(xy[0],xy[1]).getColor());

        try{
            _sharedChunks.getGameTile(newX,newY).setBackground(Color.red);//TODO: Make better @@@
            this.xy = new int[]{newX,newY};
            _sharedChunks.getGameTile(xy[0],xy[1]).setBackground(this.color);
        }catch(NullPointerException e){
//            e.printStackTrace();
        }

        //Make sure not out of bounds by checking if chunk exists
//        try{
//            if(chunkDisplayAndController.getGameTile(newX, newY).landable){
//                setPlayerTileColor(chunkDisplayAndController.getGameTile(playerX,playerY).getColor());
//                //            setPlayerTileColor(Color.red);
//                playerX = newX;
//                playerY = newY;
//                colorPlayerChunk();//TODO: Make more efficent?
//                setPlayerTileColorTemp(Defaults.PLAYER_COLOR);
//            }
//        }catch(ArrayIndexOutOfBoundsException e){
////            System.out.println("Sorry but that space does not exist.");
//        }
        return true;
    }

    @Override
    public boolean teleport(int newX, int newY){
        return false;
    }
}
