package com.example.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mainactivity.ui.jetpack.JetpackFragment;

public class Jetpack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jetpack_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, JetpackFragment.newInstance())
                    .commitNow();
        }
    }


}
