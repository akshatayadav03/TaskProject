package com.nimapinfotech.machinetask.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nimapinfotech.machinetask.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new RecordFragment());

    }

    private void loadFragment(RecordFragment recordFragment) {
        //switching fragment
        if (recordFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, recordFragment)
                    .commit();
        }

    }

}