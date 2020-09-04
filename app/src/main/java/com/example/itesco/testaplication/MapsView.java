package com.example.itesco.testaplication;

import com.example.itesco.testaplication.model.adressPojo.DataAdress;
import com.example.itesco.testaplication.model.adressPojo.Federal_entity;
import com.example.itesco.testaplication.model.adressPojo.Municipality;
import com.example.itesco.testaplication.model.adressPojo.SettlementType;
import com.example.itesco.testaplication.model.adressPojo.Settlements;

public interface MapsView {
    void showData(DataAdress dataAdress);
    void hideProgress();
    void  showProgress();
    void showError();
}
