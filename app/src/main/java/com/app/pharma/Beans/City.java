package com.app.pharma.Beans;

import java.util.List;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class City extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Pharmacy> pharmacies;

    public City(int id, RealmList<Pharmacy> pharmacies) {
        this.id = id;
        this.pharmacies = pharmacies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(RealmList<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
