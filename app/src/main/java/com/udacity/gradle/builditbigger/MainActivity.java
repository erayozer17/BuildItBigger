package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.erayo.myandroidlibrary.MainActivityLib;


public class MainActivity extends AppCompatActivity implements Callback {

    public String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new AsyncCallForJoke(this).execute();
    }


    @Override
    public void onResponse(String s) {
        response = s;
        Intent i = new Intent(MainActivity.this, MainActivityLib.class);
        i.putExtra(MainActivityLib.GET_SET_JOKE, response);
        startActivity(i);
    }
}

