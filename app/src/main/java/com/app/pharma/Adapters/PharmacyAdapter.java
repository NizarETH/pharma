package com.app.pharma.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.app.pharma.Beans.City;
import com.app.pharma.Beans.Pharmacy;
import com.app.pharma.Fragments.ListPharmacies;
import com.app.pharma.MyApplication;
import com.app.pharma.R;

import java.util.List;

public class PharmacyAdapter extends BaseAdapter {

    private List<Pharmacy> pharmacies;
    private Context context;

    public PharmacyAdapter(List<Pharmacy> pharmacies, Context context) {
        this.pharmacies = pharmacies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pharmacies.size();
    }

    @Override
    public Pharmacy getItem(int i) {
        return pharmacies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        convertView = ((FragmentActivity)context).getLayoutInflater().inflate(R.layout.body_data, null);

        ((TextView) convertView.findViewById(R.id.name)).setText(pharmacies.get(i).getName());
        ((TextView) convertView.findViewById(R.id.address)).setText(pharmacies.get(i).getAddress());
        ((TextView) convertView.findViewById(R.id.phone)).setText(pharmacies.get(i).getPhone());


        if(pharmacies.get(i).getOpen().equalsIgnoreCase("Oui"))
            ((ImageView)  convertView.findViewById(R.id.open)).setColorFilter(ContextCompat.getColor(context, R.color.Green1), android.graphics.PorterDuff.Mode.SRC_IN);

        else
            ((ImageView)  convertView.findViewById(R.id.open)).setColorFilter(ContextCompat.getColor(context, R.color.Red), android.graphics.PorterDuff.Mode.SRC_IN);

        convertView.findViewById(R.id.open_map).setOnClickListener(view -> {

            openMap(pharmacies.get(i).getLatitude(), pharmacies.get(i).getLongitude());
        });
        return convertView;
    }


    private void openMap(String destinationLatitude, String destinationLongitude)
    {
        String uri = "http://maps.google.com/maps?q=loc:" + destinationLatitude + "," + destinationLongitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        context. startActivity(intent);
    }
}
