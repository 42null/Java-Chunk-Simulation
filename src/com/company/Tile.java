package com.company;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton{
    private String _name;
    private int _toggleStageSelected = 0;//0 = default
    private Color _color = Defaults.STARTING_COLOR;

    public void setColor(Color newColor){//TODO: Make return boolean if changed?
        this._color = newColor;
        this.setBackground(this._color);
    }
    public Color getColor(){return _color;}

    public Tile(String name_) {
        this._name = name_;
        this.setName(_name);
    }

    public int getPosistion(){
//        return this.posistion;
        return -1;
    }

    public void updateImages(final Image selected, final Image unselected) {

        final ImageView iv = new ImageView(selected);
//        this.getChildren().add(iv);

        iv.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                iv.setImage(unselected);
            }
        });
        iv.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                iv.setImage(selected);
            }
        });
//        super.setGraphic(iv);
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