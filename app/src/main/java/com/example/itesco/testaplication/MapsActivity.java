package com.example.itesco.testaplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.itesco.testaplication.dataAcces.requestQueueSingleton.requestVolley;
import com.example.itesco.testaplication.model.adressPojo.DataAdress;
import com.example.itesco.testaplication.model.adressPojo.Federal_entity;
import com.example.itesco.testaplication.model.adressPojo.Municipality;
import com.example.itesco.testaplication.model.adressPojo.SettlementType;
import com.example.itesco.testaplication.model.adressPojo.Settlements;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,MapsView {

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

    @BindView(R.id.progres)
    ProgressBar progres;

    requestVolley request;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        showProgress();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
           showError();
        }

        request = new requestVolley(getApplicationContext(),this);
    }

    private void requestAddress() {

        String url = "https://sepomex-wje6f4jeia-uc.a.run.app/api/zip-codes/01210";
        request.requestAdress(url);
    }

    private void requestPoLygon() {
        request.requestPoLygon(mMap);
    }

    @Override
    public void showData(DataAdress dataAdress) {
        textinputCity.setText(dataAdress.getFederal_entity().getCode());
        textinputCode.setText(dataAdress.getCode());
        textinputEntity.setText(dataAdress.getFederal_entity().getName());
        textinputCountry.setText(R.string.common_default_country);
        textinputMunicipality.setText(dataAdress.getSettlements().getSettlementType().getName());
        textinputSuburb.setText(dataAdress.getMunicipality().getName());
    }

    @Override
    public void hideProgress() {
        progres.setVisibility(View.INVISIBLE);
    }

    public void showProgress() {
    progres.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
     Toast.makeText(getApplicationContext(),R.string.common_message_error_request,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        requestPoLygon();
        requestAddress();

    }

}
