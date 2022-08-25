package com.company;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;

public class GameTile extends JButton{
    private String _name;
    private int _toggleStageSelected = 0;//0 = default
    private Color _color = Defaults.STARTING_COLOR;

    public void setColor(Color newColor){//TODO: Make return boolean if changed?
        this._color = newColor;
        updateColorInDisplay();
    }
    public Color getColor(){return _color;}

    public GameTile(String name_) {
        this._name = name_;
        this.setName(_name);
    }

    /**
     * Runs a random tick operation on the current tile and loads any necessary chunks
     * @return if there has been a visual change
     */
    public boolean randomTick(){
        setColor(this._color.darker());
        return true;
    }

    public void updateColorInDisplay(){
        this.setBackground(this._color);
    }


    public int getPosistion(){
//        return this.posistion;
        return -1;
    }

    public void updateState(int state){

    }

    public void leftClick(){
        _toggleStageSelected++;
        if(_toggleStageSelected>2)
            _toggleStageSelected=0;

        switch(_toggleStageSelected){
            case(0):
                _color = Defaults.STARTING_COLOR;
                break;
            case(1):
                _color = Defaults.FIRSTCLICK_LEFT;
                break;
            case(2):
                _color = Defaults.FIRSTCLICK_RIGHT;
                break;
        }
    }
    public void rightClick(){
        _toggleStageSelected++;
        if(_toggleStageSelected>2)
            _toggleStageSelected=0;

        switch(_toggleStageSelected){
            case(0):
                _color = Defaults.STARTING_COLOR;
                break;
            case(1):
                _color = Defaults.FIRSTCLICK_LEFT;
                break;
            case(2):
                _color = Defaults.FIRSTCLICK_RIGHT;
                break;
        }
        this.setColor(_color);
    }
}