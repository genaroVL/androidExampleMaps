package com.example.itesco.testaplication.model.adressPojo;
import com.example.itesco.testaplication.model.adressPojo.Federal_entity;
public class DataAdress {
    String code;
    Federal_entity federal_entity;
    Municipality municipality;
    Settlements settlements;


    public DataAdress() {
        federal_entity=new Federal_entity();
        municipality=new Municipality();
        settlements=new Settlements();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Federal_entity getFederal_entity() {
        return federal_entity;
    }

    public void setFederal_entity(Federal_entity federal_entity) {
        this.federal_entity = federal_entity;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Settlements getSettlements() {
        return settlements;
    }

    public void setSettlements(Settlements settlements) {
        this.settlements = settlements;
    }
}
