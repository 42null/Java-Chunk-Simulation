package com.company.tileTypes;

import com.company.Defaults;
import com.company.Tile;
import com.company.tileTypes.TileType;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTile extends JButton {

    public char[] tileID = new char[2];//TODO: Just Do GetClass?

    public boolean passible = true;
    public boolean landable = true;
    public boolean liquid = false;
    public short state = -1;

    public Color defaultColor = Defaults.ERROR_COLOR;
    private Color _color = Defaults.STARTING_COLOR;

    public void setColor(Color newColor){//TODO: Make return boolean if changed?
        this._color = newColor;
        updateColorInDisplay();
    }
    public Color getColor(){return _color;}

    public GameTile(String name_) {
        super.setName(name_);
        this.setName(name_);
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

}