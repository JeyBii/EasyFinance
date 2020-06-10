package com.example.easyfinance;

import android.util.Log;

import java.util.ArrayList;

public class Container {

    final static String TAG = "CONTAINER";
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
    public Receipt getReciept(int index){
        if(arrayList.get(index)!=null){
            return arrayList.get(index);
        }
        Log.d(TAG, "getReciept failed! No Reciept found! Index is out of Range");
        return null;
    }
    public Receipt deleteReciept(int index){
        if(arrayList.get(index)!=null){
            return arrayList.remove(index);
        }
        Log.d(TAG,"deleteReciept failed! No Reciept found! Index is out of Range");
        return null;
    }
    public Receipt search(String catchphrase){
        // searchfunction
        return null;
    }

}
