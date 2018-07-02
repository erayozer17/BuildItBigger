package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.erayo.myandroidlibrary.MainActivityLib;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements Callback {

    public String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

class AsyncCallForJoke extends AsyncTask <Void, Void, String>{
    private Callback callback;

    AsyncCallForJoke(Context context) {
        if (context instanceof Callback) {
            this.callback = (Callback) context;
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        MyApi myApiService = builder.build();

        String joke = null;

        try {
            joke = myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return joke;
    }

    @Override
    protected void onPostExecute(String s) {
        callback.onResponse(s);
    }
}

