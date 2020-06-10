package com.example.easyfinance;

import java.util.ArrayList;

public class Container {
    Container instance;
    ArrayList<Receipt> arrayList;

    private Container(){
        arrayList = new ArrayList<Receipt>();
    }
    public Container getInstance(){
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }
}
