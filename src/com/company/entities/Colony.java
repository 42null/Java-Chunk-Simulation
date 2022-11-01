package com.company.entities;

import com.company.entities.BasicEntity;
import com.company.entities.Entity;

import java.util.LinkedList;
import java.util.List;

public class Colony{

    private List<BasicEntity> colonyMembers = new LinkedList<BasicEntity>();

    public void OnTick(){
        for(int i = 0; i <colonyMembers.size(); i++) {
            colonyMembers.get(i).onTick();
        }
    }
}
