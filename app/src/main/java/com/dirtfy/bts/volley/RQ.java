package com.dirtfy.bts.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RQ {

    private static RequestQueue myRequestQueue = null;

    public static RequestQueue getInstance(Context context){
        if(myRequestQueue == null) {
            myRequestQueue = Volley.newRequestQueue(context);
        }

        return myRequestQueue;
    }

    public RQ(Context context){
        getInstance(context);
    }

}
