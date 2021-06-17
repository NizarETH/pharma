package com.app.pharma.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.pharma.Adapters.PharmacyAdapter;
import com.app.pharma.Beans.Pharmacy;
import com.app.pharma.R;
import java.util.List;

import io.realm.Realm;



public class ListPharmacies extends Fragment {

    private View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.list_pharmacies, container, false);

        Bundle b = getArguments();
        if(b != null)
        {
            String name = b.getString("name");

        ListView listView = (ListView) v.findViewById(R.id.list);
        ProgressBar progress = (ProgressBar) v.findViewById(R.id.progress);
        ImageView refresh = (ImageView)  v.findViewById(R.id.refresh);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh.setRotation(refresh.getRotation() + 120);

            }
        });

       loadData(progress, name ,listView);


        }
        return v;
    }



    private void loadData(ProgressBar progress, String name, ListView listView) {
        Handler h = new Handler();
        h.postDelayed(() -> {

            progress.setVisibility(View.GONE);
            Realm r = Realm.getDefaultInstance();
            List<Pharmacy> pharmacies =  r.where(Pharmacy.class).equalTo("cityName", name).findAll();
            PharmacyAdapter itemArrayAdapter = new PharmacyAdapter(pharmacies, getActivity());
            listView.setAdapter(itemArrayAdapter);
            listView.setVisibility(View.VISIBLE);

        }, 600);
    }
}
