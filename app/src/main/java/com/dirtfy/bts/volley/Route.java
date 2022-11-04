package com.dirtfy.bts.volley;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Route {
    ArrayList<Transit> transitList;

    public Route(ArrayList<Transit> transitList) {
        this.transitList = transitList;
    }

    public ArrayList<Transit> getTransitList() {
        return transitList;
    }

    @NonNull
    @Override
    public String toString() {
        String string = "\n";

        for(int i = 0;i < transitList.size();i++){
            string = string+transitList.get(i).toString();
        }

        return string;
    }
}
