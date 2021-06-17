package com.app.pharma.Utils;

import com.app.pharma.Beans.Pharmacy;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import io.realm.Realm;

public class PrepareData {

    public List<Pharmacy> BuildData(List scoreList) {
        List<Pharmacy> pharmacies = new ArrayList<>();

        for (int i = 1; i < scoreList.size(); i++) {

            String[] data = (String[]) scoreList.get(i);
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setId(data[0]);
            pharmacy.setCityName(data[1]);
            pharmacy.setName(data[2]);
            pharmacy.setAddress(data[3]);
            pharmacy.setLatitude(data[4]);
            pharmacy.setLongitude(data[5]);
            pharmacy.setPhone(data[6]);
            pharmacy.setOpen(data[7]);
            pharmacies.add(pharmacy);

        }

        Realm r = Realm.getDefaultInstance();

        r.beginTransaction();
        r.deleteAll();

        for (int i = 0; i < pharmacies.size(); i++) {
            r.copyToRealmOrUpdate(pharmacies.get(i));
        }
        r.commitTransaction();

        List<Pharmacy> data = r.where(Pharmacy.class).findAll();

        return data;
    }

    public List<String> Build()
    {
        Realm r = Realm.getDefaultInstance();
        List<Pharmacy> pharmacies = r.where(Pharmacy.class).findAll();
        List<String> cities = new ArrayList<>();

        for (int i = 1; i < pharmacies.size(); i++) {

            cities.add(pharmacies.get(i).getCityName());
        }

        List<String> names = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            names.add(cities.get(i));
        }


        LinkedHashSet<String> hashSet = new LinkedHashSet<>(names);
        ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);


        return listWithoutDuplicates;

    }

}
