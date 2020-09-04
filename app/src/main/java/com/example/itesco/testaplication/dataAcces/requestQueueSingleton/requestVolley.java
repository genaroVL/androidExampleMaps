package com.example.itesco.testaplication.dataAcces.requestQueueSingleton;

import android.content.Context;
import android.graphics.Color;


import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.itesco.testaplication.MapsActivity;
import com.example.itesco.testaplication.MapsView;
import com.example.itesco.testaplication.R;
import com.example.itesco.testaplication.model.adressPojo.DataAdress;
import com.example.itesco.testaplication.model.adressPojo.Federal_entity;
import com.example.itesco.testaplication.model.adressPojo.Municipality;
import com.example.itesco.testaplication.model.adressPojo.SettlementType;
import com.example.itesco.testaplication.model.adressPojo.Settlements;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.material.textfield.TextInputEditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class requestVolley {
    private String url="https://poligonos-wje6f4jeia-uc.a.run.app/zip-codes/01210";
    private Context context;
    private ArrayList<LatLng> cordenate;
    private CenterPolygon centerPolygon;
    private Polygon polygon=null;


    private MapsView mView;


    public requestVolley(Context context,MapsView mView) {
        this.context=context;
        this.mView=mView;
    }


    public void  requestPoLygon(GoogleMap mMap){
      cordenate=new ArrayList<>();


        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject jsonObject= response.getJSONObject("geometry");
                JSONArray jsonArray=jsonObject.getJSONArray("coordinates");
                JSONArray jsonarrayPrimario=jsonArray.getJSONArray(0);
                JSONArray jsonAuxiliar;

                for(int i=0; i<jsonarrayPrimario.length();i++){
                    jsonAuxiliar=jsonarrayPrimario.getJSONArray(i);
                    cordenate.add(new LatLng(jsonAuxiliar.getDouble(1),jsonAuxiliar.getDouble(0)));
                }
                PolygonOptions polygonOptions=new PolygonOptions().addAll(cordenate).clickable(true);
                polygon=mMap.addPolygon(polygonOptions);
                polygon.setFillColor(Color.GREEN);
                polygon.setStrokeColor(Color.GRAY);

               centerPolygon=new CenterPolygon();
                LatLng center=centerPolygon.getPolygonCenterPoint(cordenate);

                mMap.addMarker(new MarkerOptions()
                        .position(center)
                        .title("prueba")
                );
                CameraUpdate miubicacion = CameraUpdateFactory.newLatLngZoom(cordenate.get(0), 15);
                mMap.moveCamera(miubicacion);

                mView.hideProgress();

            } catch (JSONException e) {
                e.printStackTrace();
                mView.showError();

            }
        }, error -> {
           mView.showError();
        });

        InstanceRequestQue.getInstance().setContext(context);
        InstanceRequestQue.getInstance().addToRequestQueue(request);
    }

    public void  requestAdress(String url){
      this.url=url;

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject jsonFederalEntity=response.getJSONObject("federal_entity");
                JSONObject jsonMunicipality=response.getJSONObject("municipality");
                JSONArray jsonArraySettlements=response.getJSONArray("settlements");
                JSONObject jsonObjectSettlements=jsonArraySettlements.getJSONObject(0);
                JSONObject jsonObjectSettlementsType=jsonObjectSettlements.getJSONObject("settlement_type");

                DataAdress dataAdress=new DataAdress();

                dataAdress.getFederal_entity().setCode(jsonFederalEntity.getString("code"));
                dataAdress.getFederal_entity().setName(jsonFederalEntity.getString("name"));

                dataAdress.getMunicipality().setName(jsonMunicipality.getString("name"));
                dataAdress.getMunicipality().setKey(jsonMunicipality.getString("key"));

                dataAdress.getSettlements().getSettlementType().setName(jsonObjectSettlementsType.getString("name"));
                dataAdress.setCode(response.getString("zip_code"));

                mView.showData(dataAdress);
            } catch (JSONException e) {
                e.printStackTrace();
                mView.showError();

            }
        }, error -> {
           mView.showError();
        });

        InstanceRequestQue.getInstance().setContext(context);
        InstanceRequestQue.getInstance().addToRequestQueue(request);

    }

}
