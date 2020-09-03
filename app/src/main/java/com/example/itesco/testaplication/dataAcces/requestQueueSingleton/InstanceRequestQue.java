package com.example.itesco.testaplication.dataAcces.requestQueueSingleton;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class InstanceRequestQue {
    private RequestQueue mRequestQueue;
    private Context context;


    private static  class SingletonHolder{
        private static final InstanceRequestQue INSTANCE=new InstanceRequestQue();
    }

    public static InstanceRequestQue getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }




    public RequestQueue getmRequestQueue(){
        if(mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T>request){
        getmRequestQueue().add(request);
    }

}
