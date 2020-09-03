package com.example.itesco.testaplication.dataAcces.requestQueueSingleton;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;

public class CenterPolygon {
    public LatLng getPolygonCenterPoint(ArrayList<LatLng> polygonPointsList){
        LatLng centerLatLng ;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i = 0 ; i < polygonPointsList.size() ; i++)
        {
            builder.include(polygonPointsList.get(i));
        }
        LatLngBounds bounds = builder.build();
        centerLatLng =  bounds.getCenter();

        return centerLatLng;
    }
}
