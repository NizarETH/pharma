package com.app.pharma.Activities;


import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.app.pharma.Fragments.ListCities;
import com.app.pharma.MyApplication;
import com.app.pharma.R;


public class MainActivity extends FragmentActivity implements SearchCity {


    public String bodyFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication) getApplication()).setFragment(this, new ListCities());

    }

    @Override
    public void onBackPressed() {

        int size = getSupportFragmentManager().getBackStackEntryCount();
        if (size <= 1) {
            finish();

        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void value(String name) {
        ListCities listVilles =  (ListCities) getSupportFragmentManager()
                .findFragmentByTag("ListCities");
        listVilles.getSearchedValue(name);
    }
}