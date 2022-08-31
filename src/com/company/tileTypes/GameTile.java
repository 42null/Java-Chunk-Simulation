package com.company.tileTypes;

import com.company.Defaults;
import com.company.tileTypes.TileType;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;

public class GameTile extends JButton implements TileType {

    public char[] tileID = new char[2];//TODO: Just Do GetClass?

    public boolean passible = true;
    public boolean landable = true;
    public boolean liquid = false;
    public short state = -1;

    public Color defaultColor = new Color(204,9,160); //The Pink Found In Error Textures (https://colornames.org/color/cc09a0)

    private Color _color = Defaults.STARTING_COLOR;

    public void setColor(Color newColor){//TODO: Make return boolean if changed?
        this._color = newColor;
        updateColorInDisplay();
    }
    public Color getColor(){return _color;}

    public GameTile(String name_) {
        super.setName(name_);
        this.setName(name);
    }

    public boolean tick(){
        return false;
    }

    /**
     * Runs a random tick operation on the current tile and loads any necessary chunks
//     * @return if there has been a visual change NOTSURE
     */
    public boolean randomTick(){
        setColor(this._color.darker());
        return false;
    }

    public void updateColorInDisplay(){
        this.setBackground(this._color);
    }

    public void leftClick(){
//        _toggleStageSelected++;
//        if(_toggleStageSelected>2)
//            _toggleStageSelected=0;
//
//        switch(_toggleStageSelected){
//            case(0):
//                _color = Defaults.STARTING_COLOR;
//                break;
//            case(1):
//                _color = Defaults.FIRSTCLICK_LEFT;
//                break;
//            case(2):
//                _color = Defaults.FIRSTCLICK_RIGHT;
//                break;
//        }
    }
    public void rightClick(){
//        _toggleStageSelected++;
//        if(_toggleStageSelected>2)
//            _toggleStageSelected=0;
//
//        switch(_toggleStageSelected){
//            case(0):
//                _color = Defaults.STARTING_COLOR;
//                break;
//            case(1):
//                _color = Defaults.FIRSTCLICK_LEFT;
//                break;
//            case(2):
//                _color = Defaults.FIRSTCLICK_RIGHT;
//                break;
//        }
//        this.setColor(_color);
    }

}