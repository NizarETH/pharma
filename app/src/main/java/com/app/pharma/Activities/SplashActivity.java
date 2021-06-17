package com.app.pharma.Activities;

import android.content.Intent;
import android.os.Handler;
 import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.app.pharma.Beans.Pharmacy;
import com.app.pharma.R;
import com.app.pharma.Utils.CSVFile;
import com.app.pharma.Utils.PrepareData;

import java.io.InputStream;
import java.util.List;

import io.realm.Realm;

public class SplashActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Realm r = Realm.getDefaultInstance();
                List<Pharmacy> all = r.where(Pharmacy.class).findAll();

                if(all != null && all.isEmpty()) {
                    InputStream inputStream = getResources().openRawResource(R.raw.pharma);
                    CSVFile csvFile = new CSVFile(inputStream);
                    List scoreList = csvFile.read();

                    List<Pharmacy> pharmacies = new PrepareData().BuildData(scoreList);
                    Log.e("Size = ",""+pharmacies.size());
                }

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 800);
    }

}