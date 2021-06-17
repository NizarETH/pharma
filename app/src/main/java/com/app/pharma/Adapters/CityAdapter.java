package com.app.pharma.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import com.app.pharma.Fragments.ListPharmacies;
import com.app.pharma.MyApplication;
import com.app.pharma.R;

import java.util.List;

public class CityAdapter extends BaseAdapter {

    private List<String> cities;
    private Context context;

    public CityAdapter(List<String> cities, Context context) {
        this.cities = cities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public String getItem(int i) {
        return cities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = ((FragmentActivity)context).getLayoutInflater().inflate(R.layout.header_data, null);

        ((TextView) view.findViewById(R.id.city)).setText(cities.get(i));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListPharmacies listPharmacies = new ListPharmacies();
                Bundle b = new Bundle();
                b.putString("name",cities.get(i) );
                listPharmacies.setArguments(b);

                ((MyApplication)((FragmentActivity) context).getApplication()).setFragment((FragmentActivity) context,listPharmacies );
            }
        });
        return view;
    }
}
