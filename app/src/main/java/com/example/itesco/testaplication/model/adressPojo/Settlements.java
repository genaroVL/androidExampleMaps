package com.example.itesco.testaplication.model.adressPojo;

public class Settlements {
       String name;
        String zonaType;

    public SettlementType getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(SettlementType settlementType) {
        this.settlementType = settlementType;
    }

    SettlementType settlementType;

    public Settlements() {
        settlementType=new SettlementType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZonaType() {
        return zonaType;
    }

    public void setZonaType(String zonaType) {
        this.zonaType = zonaType;
    }



}

