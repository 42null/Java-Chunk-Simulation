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
        this._color = this._color.darker();
        this.setBackground(_color);
        return true;
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
        this.setBackground(_color);
    }
}