package com.company;

import java.awt.*;

public interface Entity {
//  public static final int id = 0;
//  private int[] xy = new int[]{0,0};
//  private int x,y;//TODO: Make array for efficiency and memory?
//  private Color color = _____;

//GETTERS
    public int getX();
    public int getY();
    public int[] getXY();
//SETTERS
    boolean setColor(Color newColor);//TODO: Make public?

    /**
     * Method called when a new tick is called.
     */
    public void onTick();

    /**
     * Decides if a move is valid and moves there is possible
     * @param newX
     * @param newY
     * @return if movement was successful
     */
    public boolean tryToMove(int newX, int newY);

    /**
     * tryToMove but with fewer requirements, still needs to check if spot is valid but doesn't care about obstacles in the way.
     * @param newX
     * @param newY
     */
    public boolean teleport(int newX, int newY);

    /**
     * Can be overridden but these settings will have it treat the other location as if it were a wall.
     * @param entity
     * @return if move is able to be completed
     */
    private boolean onCollisionWithOtherEntity(Entity entity){
        entity.setColor(Color.RED);//TODO: remove?
        return false;
    }

}
