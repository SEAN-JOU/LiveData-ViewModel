package com.example.mainactivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.mainactivity.ui.jetpack.JetpackViewModel;
import com.example.mainactivity.ui.jetpack.User;

public class MainActivity extends AppCompatActivity {

    private JetpackViewModel mModel;
    public TextView mNameTextView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNameTextView=findViewById(R.id.txv);
        button =findViewById(R.id.btn);
        mModel = ViewModelProviders.of(this).get(JetpackViewModel.class);

        // Create the observer which updates the UI.
        final Observer<User> nameObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User newName) {
                // Update the UI, in this case, a TextView.
                mNameTextView.setText(newName.getFirstName());
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getUserLiveData().observe(this, nameObserver);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User uuu = new User("a","b");
                mModel.getUserLiveData().setValue(uuu);

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Jetpack.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
