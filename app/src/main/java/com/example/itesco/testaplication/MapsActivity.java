package com.example.itesco.testaplication;

import android.os.Bundle;
import android.widget.Toast;
import com.example.itesco.testaplication.dataAcces.requestQueueSingleton.requestVolley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.textinput_codeText)
    TextInputEditText textinputCode;

    @BindView(R.id.textinput_countryText)
    TextInputEditText textinputCountry;

    @BindView(R.id.textinput_entityText)
    TextInputEditText textinputEntity;

    @BindView(R.id.textinput_cityText)
    TextInputEditText textinputCity;

    @BindView(R.id.textinput_municipalityText)
    TextInputEditText textinputMunicipality;

    @BindView(R.id.textinput_coloniaText)
    TextInputEditText textinputSuburb;

    private  GoogleMap mMap;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);
            }else{
                Toast.makeText(getApplicationContext(),"Algo ha salido mal",Toast.LENGTH_LONG).show();
            }


        }

    private  void requestAddress(){
        requestVolley request=new requestVolley(getApplicationContext());
        String url="https://sepomex-wje6f4jeia-uc.a.run.app/api/zip-codes/01210";
        request.requestAdress(url,textinputCode,textinputCountry,textinputEntity,textinputCity,textinputMunicipality,textinputSuburb);
    }
    private  void requestPoLygon(){
        requestVolley request=new requestVolley(getApplicationContext());
       request.requestPoLygon(mMap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        requestPoLygon();
        requestAddress();
    }

}
