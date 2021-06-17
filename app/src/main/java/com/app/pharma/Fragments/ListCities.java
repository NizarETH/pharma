package com.app.pharma.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.pharma.Activities.MainActivity;
import com.app.pharma.Activities.SearchCity;
import com.app.pharma.Activities.SplashActivity;
import com.app.pharma.Adapters.CityAdapter;
import com.app.pharma.Beans.Pharmacy;
import com.app.pharma.R;
import com.app.pharma.Utils.CSVFile;
import com.app.pharma.Utils.PrepareData;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ListCities extends Fragment {

    private View v;
    private SearchCity searchCity;
    private List<String> cities;
    private CityAdapter   itemArrayAdapter;
    private ListView listView;
    private ImageView empty_icon ;
    private TextView empty_title ;
    private   EditText searchValue;
    private  ProgressBar progress;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof SearchCity)
            searchCity = (SearchCity) context;

    }

    @Override
    public void onResume() {
        super.onResume();
        if(searchValue != null)
            searchValue.setText("");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.list_cities, container, false);

        listView = (ListView) v.findViewById(R.id.list_cities);
        empty_icon = (ImageView)  v.findViewById(R.id.empty_icon);
        empty_title = (TextView)  v.findViewById(R.id.empty_title);
        searchValue = (EditText)  v.findViewById(R.id.ville);

        progress = (ProgressBar) v.findViewById(R.id.progress);


        loadData( progress, listView);

        searchCityName(searchValue);

        v.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");

                startActivityForResult(intent, 1);
            }
        });

        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            Log.e("",""+data.toString());
            InputStream csv = null;
            try {
                csv = getActivity().getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                Log.e("",""+e.getMessage());
            }

            CSVFile csvFile = new CSVFile(csv);
            List scoreList = csvFile.read();
            Log.e("","==> "+scoreList.size());

             new PrepareData().BuildData(scoreList);


            loadData( progress, listView);

        }
        else {
            AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
            b.setTitle("My pharmacy");

            b.setMessage("Veuillez choisir un fichier EXCEL");
            b.setCancelable(false)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            b.create().dismiss();
                        }
                    });

            b.show();
        }


    }
    private void searchCityName( EditText searchValue) {

        searchValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = searchValue.getText().toString();
                searchCity.value(value);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    //
            }
        });
    }

    private void loadData(ProgressBar progress, ListView listView) {
        Handler h = new Handler();
        h.postDelayed(() -> {
            progress.setVisibility(View.GONE);


            cities =  new PrepareData().Build();
            itemArrayAdapter = new CityAdapter(cities, getActivity());
            listView.setAdapter(itemArrayAdapter);
            listView.setVisibility(View.VISIBLE);
            handleViews(cities);

        }, 600);
    }

    public void getSearchedValue(String name) {

        int textLength = name.length();
        List<String> tempArrayList = new ArrayList<>();

        if (cities != null && !cities.isEmpty()) {
            for (String c : cities) {
                if (textLength <= c.length() && c.toLowerCase().contains(name.toLowerCase())) {
                    tempArrayList.add(c);
                }
            }
        }

        itemArrayAdapter = new CityAdapter(tempArrayList, getActivity());
        listView.setAdapter(itemArrayAdapter);
        handleViews(tempArrayList);
    }

    private void handleViews(List<String> tempArrayList)
    {
        if (tempArrayList.isEmpty())
        {
            empty_icon.setVisibility(View.VISIBLE);
            empty_title.setVisibility(View.VISIBLE);
        }
        else
        {
            empty_icon.setVisibility(View.GONE);
            empty_title.setVisibility(View.GONE);
        }
    }
}
